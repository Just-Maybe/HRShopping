package com.example.helloworld.huaruanshopping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.adapter.HomeRecylcerViewAdaper;
import com.example.helloworld.huaruanshopping.bean.ProductBean;
import com.example.helloworld.huaruanshopping.bean.ProductListBean;
import com.example.helloworld.huaruanshopping.presenter.FragmentHomePresenter;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentBaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by helloworld on 2017/1/15.
 */

public class fragmentHome extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IFragmentBaseView {
    private RecyclerView mRecyclerView;
    private List<ProductBean.DataBean> mProductList = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    private static SwipeRefreshLayout mRefreshLayout;
    private View view;
    private boolean loading = false;
    private FragmentHomePresenter mFragmentHomePresenter;
    private HomeRecylcerViewAdaper adaper;
    private int pageNum = 2;
    boolean isLoadMore = false;

    public static fragmentHome newInstance() {

        Bundle args = new Bundle();
        fragmentHome fragment = new fragmentHome();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentHomePresenter = new FragmentHomePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.from(getContext()).inflate(R.layout.fragment_home, null, false);
        initRecyclerView();
        initRefreshLayout();
        if (mProductList.size() == 0) {
            mRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mRefreshLayout.setRefreshing(true);
                    onRefresh();
                }
            });
        }
        return view;
    }


    private void initRefreshLayout() {
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryFont));
//        mRefreshLayout.setRefreshing(true);
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycleview);
        mLayoutManager = new LinearLayoutManager(getActivity());
        adaper = new HomeRecylcerViewAdaper(getActivity(), mProductList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adaper);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (dy > 10) {
                    if (!loading && totalItemCount < (lastVisibleItem + 2)) {
                        Log.d("111", "onScrolled: " + totalItemCount);
                        Log.d("111", "onScrolled: " + lastVisibleItem);
                        loading = true;
                        recyclerView.post(new Runnable() {
                            @Override
                            public void run() {
                                addMoreData();
                                adaper.setLoadStatus("加载中...");
                            }
                        });
                        Log.d("111", "onScrolled: LoadMOre" + pageNum);
//                    pageNum++;
                    }
                }

            }
        });
    }

    public void addMoreData() {
        loading = false;
        mFragmentHomePresenter.getHomePrudoct(2, pageNum, true);
        pageNum = pageNum + 1;
    }

    @Override
    public void onRefresh() {
//            mHandler.sendEmptyMessageDelayed(0x111, 2000);
        adaper.setLoadStatus("");
        mFragmentHomePresenter.getHomePrudoct(2, 1, false);
        pageNum = 2;
    }

    @Override
    public void showLoading() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
//        Toast.makeText(getContext(), "加载成功!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
//        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
        adaper.setLoadStatus("没有更多商品了");
    }

    @Override
    public void showData(List<ProductBean.DataBean> list, boolean isLoadMore) {
        if (list != null) {
            if (!isLoadMore) {
                mProductList.clear();
            }
            adaper.addMoreData(list);

        }
    }
}
