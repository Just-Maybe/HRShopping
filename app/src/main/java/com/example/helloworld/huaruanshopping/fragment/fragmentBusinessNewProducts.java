package com.example.helloworld.huaruanshopping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.adapter.SortListRecylcerViewAdapter;
import com.example.helloworld.huaruanshopping.bean.ProductBean;
import com.example.helloworld.huaruanshopping.presenter.FragmentBusinessPresenter;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentBusiness;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by helloworld on 2017/5/4.
 */

public class fragmentBusinessNewProducts extends Fragment implements IFragmentBusiness {

    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    Unbinder unbinder;
    private SortListRecylcerViewAdapter adapter;
    private ArrayList<ProductBean.DataBean> mProductList = new ArrayList<>();
    GridLayoutManager gm;
    FragmentBusinessPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FragmentBusinessPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_business_newproducts, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter.getBusinessNewProduct(1, 1);
        initView();
        return view;
    }

    private void initView() {
        gm = new GridLayoutManager(getActivity(), 2);
        adapter = new SortListRecylcerViewAdapter(getActivity(), mProductList);
        recycleview.setLayoutManager(gm);
        recycleview.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showData(ArrayList<ProductBean.DataBean> data) {
        mProductList.clear();
        if (data.size() > 0) {
            adapter.addMoreData(data);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mProductList != null) {
            mProductList.clear();
        }
    }
}
