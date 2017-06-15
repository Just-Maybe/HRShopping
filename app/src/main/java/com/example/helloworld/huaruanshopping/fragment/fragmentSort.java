package com.example.helloworld.huaruanshopping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.adapter.TabLayoutAdapter;
import com.example.helloworld.huaruanshopping.bean.ProductBean;
import com.example.helloworld.huaruanshopping.bean.ProductListBean;
import com.example.helloworld.huaruanshopping.presenter.FragmentSortPresenter;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentSort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by helloworld on 2017/1/15.
 */

public class fragmentSort extends Fragment implements IFragmentSort {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private View view;
    private List<String> mList = new ArrayList<>();
    private TabLayoutAdapter mTabLayoutAdapter;
    private FragmentManager fm;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private FragmentSortPresenter presenter = new FragmentSortPresenter(this);
    private final static String TAG = "111";

    public static fragmentSort newInstance() {

        Bundle args = new Bundle();

        fragmentSort fragment = new fragmentSort();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getChildFragmentManager();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.from(getContext()).inflate(R.layout.fragment_sort, null, false);
//        initData();
//        initSortName();
        initView();
        presenter.getCategory();
        return view;
    }


    public List<String> getmList() {
        return mList;
    }

    public void setmList(List<String> mList) {
        this.mList = mList;
    }

    private void initView() {
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.sortViewPager);
        mViewPager.setOffscreenPageLimit(9);
        mTabLayoutAdapter = new TabLayoutAdapter(fm, mFragmentList, mList);
        for (int i = 0; i < mList.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mList.get(i)));
        }
        mViewPager.setAdapter(mTabLayoutAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public void setCategoryText(List list) {
        if (!(mList.equals(list))) {
            mList = list;
            mFragmentList.clear();
            for (int i = 0; i < mList.size(); i++) {
                mFragmentList.add(new fragmentPageSortList().newInstance(mList.get(i), (i + 1)));
            }
        }
        mTabLayoutAdapter = new TabLayoutAdapter(fm, mFragmentList, mList);
        mTabLayoutAdapter.notifyDataSetChanged();
        mViewPager.setAdapter(mTabLayoutAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFragmentList != null) {
            mFragmentList.clear();
        }
        if (mList != null) {
            mList.clear();
        }
    }
}
