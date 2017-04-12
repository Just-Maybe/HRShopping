package com.example.helloworld.huaruanshopping.acitiviy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.bean.address;
import com.example.helloworld.huaruanshopping.fragment.fragmentManageAddress;
import com.example.helloworld.huaruanshopping.fragment.fragmentUpdateName;
import com.example.helloworld.huaruanshopping.fragment.fragmentUpdatePassword;
import com.example.helloworld.huaruanshopping.fragment.fragmentaddAddress;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by helloworld on 2017/3/21.
 */

public class updateInfoActivity extends AppCompatActivity {

    @BindView(R.id.backIcon)
    ImageView backIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.updateInfoLayout)
    FrameLayout updateInfoLayout;
    private String titleTv;
    FragmentManager fm;
    address address = new address();
    int position;
    String TAG = "111";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //透明导航栏
        ButterKnife.bind(this);
        fm = getSupportFragmentManager();
        setSupportActionBar(toolbar);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();


        titleTv = intent.getStringExtra("info");
        if (titleTv.equals("name")) {
            title.setText("设置昵称");
            fm.beginTransaction().add(R.id.updateInfoLayout, new fragmentUpdateName()).commit();
        } else if (titleTv.equals("password")) {
            title.setText("修改密码");
            fm.beginTransaction().add(R.id.updateInfoLayout, new fragmentUpdatePassword()).commit();
        } else if (titleTv.equals("managerAddress")) {
            title.setText("收货地址");
            fm.beginTransaction().add(R.id.updateInfoLayout, new fragmentManageAddress()).commit();
        } else if (titleTv.equals("addAddress")) {
            address = intent.getParcelableExtra("address");
            position = intent.getIntExtra("position", 0);
            Log.d(TAG, "initData: " + position);
            if (address != null) {
                title.setText("修改收货地址");
            } else {
                title.setText("新建收货地址");
            }
            fm.beginTransaction().replace(R.id.updateInfoLayout, fragmentaddAddress.newInstance(address, position)).commit();
        }
    }

    @OnClick({R.id.backIcon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backIcon:
                this.finish();
                break;
        }
    }
}
