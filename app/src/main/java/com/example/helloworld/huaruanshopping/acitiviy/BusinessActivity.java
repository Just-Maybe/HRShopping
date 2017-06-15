package com.example.helloworld.huaruanshopping.acitiviy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.adapter.TabLayoutAdapter;
import com.example.helloworld.huaruanshopping.fragment.fragmentBusinessAllProducts;
import com.example.helloworld.huaruanshopping.fragment.fragmentBusinessNewProducts;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by helloworld on 2017/5/4.
 */

public class BusinessActivity extends AppCompatActivity {
    @BindView(R.id.businessImg)
    ImageView businessImg;
    @BindView(R.id.fansNum)
    TextView fansNum;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private TabLayoutAdapter adapter;
    private FragmentManager fm;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
//    private int[] bgs = {R.drawable.business1, R.drawable.business2, R.drawable.business3, R.drawable.business4, R.drawable.business5, R.drawable.business6};
    private String businessImgUrl;
    private int fansNum1;
    private String TAG = "111";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        ButterKnife.bind(this);
        businessImgUrl = getIntent().getStringExtra("imgUrl");
        Log.d(TAG, "onCreate: "+businessImgUrl);
        fansNum1 = getIntent().getIntExtra("fansNum", 0);
        initView();
    }

    private void initView() {
//        toolbar.setBackgroundResource(businessImgUrl);
        Glide.with(getApplicationContext()).load(businessImgUrl).into(businessImg);
        fansNum.setText(fansNum1 + "");
        fragmentList.add(new fragmentBusinessNewProducts());
        fragmentList.add(new fragmentBusinessAllProducts());
        titleList.add(new String("上新"));
        titleList.add(new String("全部"));
        fm = getSupportFragmentManager();

        viewpager.setOffscreenPageLimit(2);
        adapter = new TabLayoutAdapter(fm, fragmentList, titleList);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }
}
