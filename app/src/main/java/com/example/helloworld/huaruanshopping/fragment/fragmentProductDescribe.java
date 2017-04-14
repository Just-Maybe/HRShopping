package com.example.helloworld.huaruanshopping.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.acitiviy.PhotoViewActivity;
import com.example.helloworld.huaruanshopping.acitiviy.ProductDescribeActivity;
import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.ProductBean;
import com.example.helloworld.huaruanshopping.presenter.ActivityProductDescribePresenter;
import com.example.helloworld.huaruanshopping.presenter.implView.IActivityProductDescribe;
import com.example.helloworld.huaruanshopping.view.GlideImageLoader;
import com.example.helloworld.huaruanshopping.view.RadioGroupEx;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by helloworld on 2017/1/22.
 */

public class fragmentProductDescribe extends Fragment implements IActivityProductDescribe {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.describeName)
    TextView describeName;
    @BindView(R.id.describePrice)
    TextView describePrice;
    @BindView(R.id.sortGroup)
    RadioGroupEx sortGroup;
    @BindView(R.id.addBtn)
    ImageButton addBtn;
    @BindView(R.id.carItemNum)
    TextView carItemNum;
    @BindView(R.id.reduceBtn)
    ImageButton reduceBtn;
    @BindView(R.id.describeRemark)
    TextView describeRemark;
    private ArrayList<String> mpicList = new ArrayList<>();
    private int defaultnumber = 2;
    ActivityProductDescribePresenter presenter;
    int pid = 0;
    private final static String TAG = "111";
    private float y;
    private int ptid = 1;
    private int id = 1;
    private String token = "532a8a18b75079da0c48414014600600d64737f36e330997";
    private QBadgeView qBadgeView;
    private View view;
    private Handler mHandler;

    public static fragmentProductDescribe newInstance(int pid) {

        Bundle args = new Bundle();
        args.putInt("pid", pid);
        fragmentProductDescribe fragment = new fragmentProductDescribe();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        pid = bundle.getInt("pid");
        presenter = new ActivityProductDescribePresenter(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProductDescribeActivity) {
            mHandler = ((ProductDescribeActivity) context).mHandler;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.from(getContext()).inflate(R.layout.fragment_product_describe, container, false);
        ButterKnife.bind(this, view);
        presenter.getProductDescribe(pid);
        return view;
    }

    @OnClick({R.id.addBtn, R.id.reduceBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addBtn:
                addNum();
                break;
            case R.id.reduceBtn:
                reduceNum();
                break;
        }
    }

    private void addNum() {
        carItemNum.setText("" + (++defaultnumber));
        Message msg = mHandler.obtainMessage();
        msg.what = 100;
        msg.arg1 = defaultnumber;
        mHandler.sendMessage(msg);
        reduceBtn.setClickable(true);

        if (defaultnumber == 20) {
            addBtn.setClickable(false);
        }
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(addBtn, "ScaleX", 1.0f, 1.4f, 1.0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(addBtn, "ScaleY", 1.0f, 1.4f, 1.0f);
        AnimatorSet set = new AnimatorSet();
        set.play(animator1).with(animator2);
        set.setDuration(400).start();
    }

    private void reduceNum() {
        carItemNum.setText("" + (--defaultnumber));
        Message msg = mHandler.obtainMessage();
        msg.what = 100;
        msg.arg1 = defaultnumber;
        mHandler.sendMessage(msg);
        addBtn.setClickable(true);
        if (defaultnumber == 1) {
            reduceBtn.setClickable(false);
        }
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(reduceBtn, "ScaleX", 1.0f, 1.4f, 1.0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(reduceBtn, "ScaleY", 1.0f, 1.4f, 1.0f);
        AnimatorSet set = new AnimatorSet();
        set.play(animator1).with(animator2);
        set.setDuration(400).start();
    }


    @Override
    public void getProduceDescribeData(ProductBean.DataBean productBean) {
        describeName.setText(productBean.getName());
        describePrice.setText("￥" + productBean.getPrice());
        initSort(productBean.getProtypeSet());
        setBannerPics(productBean.getProtypeSet());
        describeRemark.setText(productBean.getRemark());
    }

    private void initSort(final List<ProductBean.DataBean.ProtypeSetBean> ListSort) {
        //颜色状态选择器
        ColorStateList csl = getResources().getColorStateList(R.color.describe_color_selector);
        for (int i = 0; i < ListSort.size(); i++) {
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.rightMargin = 30;
            params.bottomMargin = 20;
            RadioButton radioButton = new RadioButton(getActivity());
            radioButton.setBackgroundResource(R.drawable.describe_sort_selector);
            radioButton.setTextColor(csl);
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
            radioButton.setText(ListSort.get(i).getName());
            radioButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
            radioButton.setLayoutParams(params);

            final int finalI = i;
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Log.d(TAG, "onCheckedChanged: " + ListSort.get(finalI).getId());
                        ptid = ListSort.get(finalI).getId();
                        Message msg = mHandler.obtainMessage();
                        msg.what = 101;
                        msg.arg1 = ptid;
                        mHandler.sendMessage(msg);
                    }
                }
            });
            sortGroup.addView(radioButton);
            if (i == 0) {
                radioButton.setChecked(true);
                ptid = ListSort.get(i).getId();
            }
        }

    }

    private void setBannerPics(List<ProductBean.DataBean.ProtypeSetBean> ListSort) {
//        mpicList.clear();
        for (int i = 0; i < ListSort.size(); i++) {
            Log.d(TAG, "setBannerPics: " + ListSort.get(i).getPic());
            mpicList.add(HttpMethods.BASE_URL + ListSort.get(i).getPic());
        }
        initBanner();
    }

    private void initBanner() {
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(mpicList);
        banner.isAutoPlay(false);
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
                intent.putStringArrayListExtra("imageUrlsList", mpicList);
                intent.putExtra("ListPosition", position-1);
                startActivity(intent);
            }
        });
        banner.start();
    }

    @Override
    public void OnSuccessAddToCar() {

    }

    @Override
    public void OnFailedAddToCar() {

    }

}
