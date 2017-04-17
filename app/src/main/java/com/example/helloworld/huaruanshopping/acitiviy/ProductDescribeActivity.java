package com.example.helloworld.huaruanshopping.acitiviy;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.adapter.TabLayoutAdapter;
import com.example.helloworld.huaruanshopping.bean.ProductBean;
import com.example.helloworld.huaruanshopping.fragment.fragmentProductComment;
import com.example.helloworld.huaruanshopping.fragment.fragmentProductDescribe;
import com.example.helloworld.huaruanshopping.presenter.ActivityProductDescribePresenter;
import com.example.helloworld.huaruanshopping.presenter.implView.IActivityProductDescribe;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by helloworld on 2017/1/19.
 */

public class ProductDescribeActivity extends FragmentActivity implements IActivityProductDescribe {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.contentLayout)
    ViewPager contentLayout;
    @BindView(R.id.carBtn)
    Button carBtn;
    @BindView(R.id.back)
    ImageView back;
    private TextView addToCar;
    private int defaultCarNum = 5;
    private static int defaultnumber = 2;
    ActivityProductDescribePresenter presenter;
    int pid = 0;
    private final static String TAG = "111";
    private float y;
    private static int ptid = 1;
    private int id = 1;
    private String token = "532a8a18b75079da0c48414014600600d64737f36e330997";
    private QBadgeView qBadgeView;
    private FragmentManager fm;
    TabLayoutAdapter adapter;
    private fragmentProductComment fragmentComment = new fragmentProductComment();
    private fragmentProductDescribe fragmentDescribe;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();


    private static class MyHandler extends Handler {
        private final WeakReference<ProductDescribeActivity> mActivity;

        private MyHandler(ProductDescribeActivity mActivity) {
            this.mActivity = new WeakReference<>(mActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            ProductDescribeActivity activity = mActivity.get();
            super.handleMessage(msg);
            if (activity != null) {
                switch (msg.what) {
                    case 100:
                        defaultnumber = msg.arg1;
                        Log.d(TAG, "handleMessage: " + defaultnumber);
                        break;
                    case 101:
                        ptid = msg.arg1;
                        Log.d(TAG, "handleMessage: " + ptid);
                        break;
                }
            }
        }
    }

    public final MyHandler mHandler = new MyHandler(this);

//    public Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 100:
//                    defaultnumber = msg.arg1;
//                    Log.d(TAG, "handleMessage: " + defaultnumber);
//                    break;
//                case 101:
//                    ptid = msg.arg1;
//                    Log.d(TAG, "handleMessage: " + ptid);
//                    break;
//            }
//
//        }
//    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_describe);
        ButterKnife.bind(this);
        presenter = new ActivityProductDescribePresenter(this);
        fm = getSupportFragmentManager();
        initData();
        initView();
    }

    @OnClick({R.id.back, R.id.carBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.carBtn:
                Intent intent = new Intent(ProductDescribeActivity.this, MainActivity.class);
                intent.putExtra("pagePosition", 2);
                startActivity(intent);
        }
    }

    private void initData() {
        Intent intent = getIntent();
        pid = intent.getIntExtra("id", 1);
    }

    private void initView() {
        titleList.add("详情");
        titleList.add("评论");
        fragmentDescribe = fragmentProductDescribe.newInstance(pid);
        fragmentList.add(fragmentDescribe);
        fragmentList.add(fragmentComment);
        adapter = new TabLayoutAdapter(fm, fragmentList, titleList);
        contentLayout.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("详情"));
        tabLayout.addTab(tabLayout.newTab().setText("评论"));
        tabLayout.setupWithViewPager(contentLayout);
        qBadgeView = (QBadgeView) new QBadgeView(getApplicationContext()).bindTarget(carBtn).setBadgeNumber(defaultCarNum);
        y = qBadgeView.getTranslationY();
        addToCar = (TextView) findViewById(R.id.addToCar);
        addToCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addToCar(ptid, defaultnumber, id, token);
            }
        });
    }


    @Override
    public void getProduceDescribeData(ProductBean.DataBean productBean) {

    }

    @Override
    public void OnSuccessAddToCar() {
//        Snackbar.make(, "成功添加至购物车", Snackbar.LENGTH_SHORT).show();
        qBadgeView.setBadgeNumber(++defaultCarNum);
        addToCarAnimation(qBadgeView);
    }

    @Override
    public void OnFailedAddToCar() {

    }

    public void addToCarAnimation(View view) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "ScaleX", 1.0f, 1.3f, 1.0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "ScaleY", 1.0f, 1.3f, 1.0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(view, "translationY", y, y - 30, y);
        AnimatorSet set = new AnimatorSet();
        set.play(animator1).with(animator2).with(animator3);
        set.setDuration(1500).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        defaultnumber = 0;
        ptid = 0;
        mHandler.removeCallbacksAndMessages(null);
    }
}
