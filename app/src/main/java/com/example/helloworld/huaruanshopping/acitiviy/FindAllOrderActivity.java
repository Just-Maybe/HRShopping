package com.example.helloworld.huaruanshopping.acitiviy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.adapter.FindAllOrderAdapter;
import com.example.helloworld.huaruanshopping.bean.orderList.DataBean;
import com.example.helloworld.huaruanshopping.presenter.ActivityFindAllOrderPresenter;
import com.example.helloworld.huaruanshopping.presenter.implView.IActivityFindAllOrder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by helloworld on 2017/3/18.
 */

public class FindAllOrderActivity extends AppCompatActivity implements IActivityFindAllOrder {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.allOrderRecyclerview)
    RecyclerView allOrderRecyclerview;
    FindAllOrderAdapter adapter;
    List<DataBean> orderItemList = new ArrayList<>();
    ActivityFindAllOrderPresenter presenter;
    @BindView(R.id.carLoadingTv)
    TextView carLoadingTv;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_order_activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //透明导航栏
        ButterKnife.bind(this);
        presenter = new ActivityFindAllOrderPresenter(this);
        setSupportActionBar(toolbar);
        presenter.getAllOrder(1, 1, "532a8a18b75079da0c48414014600600d64737f36e330997", "init");

    }

    private void initRecyclerView() {
        adapter = new FindAllOrderAdapter(getApplicationContext(), orderItemList);
        adapter.setOnCancelItemListener(new FindAllOrderAdapter.cancelItemListen() {
            @Override
            public void afterCancelListen() {
                presenter.getAllOrder(1, 1, "532a8a18b75079da0c48414014600600d64737f36e330997", "cancel");

            }
        });
        allOrderRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        allOrderRecyclerview.setAdapter(adapter);
    }

    @Override
    public void getAllOrder(List<DataBean> orderList) {
        orderItemList = orderList;
        initRecyclerView();
    }

    @Override
    public void onSuccessCancelOrder() {
        Snackbar.make(allOrderRecyclerview, "取消成功", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessGetAllOrder() {
        carLoadingTv.setVisibility(View.GONE);
    }

    @OnClick({R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
        }
    }
}
