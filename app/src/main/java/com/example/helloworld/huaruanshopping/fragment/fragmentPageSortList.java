package com.example.helloworld.huaruanshopping.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.adapter.SortListRecylcerViewAdapter;
import com.example.helloworld.huaruanshopping.bean.Product;
import com.example.helloworld.huaruanshopping.bean.ProductBean;
import com.example.helloworld.huaruanshopping.bean.ProductListBean;
import com.example.helloworld.huaruanshopping.presenter.FragmentPageSortListPresenter;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentBaseView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by helloworld on 2017/1/15.
 */

public class fragmentPageSortList extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IFragmentBaseView {
    private RecyclerView mRecyclerView;
    private View view;
    private ArrayList<ProductBean.DataBean> mProductList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private SortListRecylcerViewAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private String category = "";
    private int sortId;
    private final static String TAG = "111";
    private FragmentPageSortListPresenter presenter;
    private boolean isVisibile;
    private boolean isPrepare;
    private boolean isFirst = true;


    public static fragmentPageSortList newInstance(String category, int sortId) {
        Bundle args = new Bundle();
        args.putString("category", category);
        args.putInt("sortId", sortId);
//        Log.d(TAG, "newInstance:id " + sortId);
        fragmentPageSortList fragment = new fragmentPageSortList();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            category = bundle.getString("category");
            sortId = bundle.getInt("sortId");
        }
        presenter = new FragmentPageSortListPresenter(this);
//        initData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        Log.d(TAG, "setUserVisibleHint: "+getUserVisibleHint()+sortId);
        if (getUserVisibleHint() && isPrepare && mProductList.size() == 0) {
            isVisibile = true;
            refreshLayout.post(new Runnable() {
                @Override
                public void run() {
//                    mProductList.clear();
                    refreshLayout.setRefreshing(true);
                    onRefresh();
                }
            });
        } else {
            isVisibile = false;
        }
//        Log.d(TAG, "setUserVisibleHint: "+isFirst);
//        isFirst=false;
    }

//    private void initData() {
//        ProductListBean good = new ProductListBean();
//        for (int i = 0; i < 24; i++) {
//            good.setName("零食" + i);
//            good.setPrice(i);
////            good.setPic_url("http://pic.qiantucdn.com/58pic/18/20/08/90W58PICtuP_1024.jpg");
//            mProductList.add(good);
////            good = new Product();
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.from(getContext()).inflate(R.layout.fragment_sort_list, null, false);
        initView();
        isPrepare = true;
        if (getUserVisibleHint() && isVisibile == false && mProductList.size() == 0) {

            refreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(true);
                    onRefresh();
                }
            });
        }

        return view;
    }

    private void initView() {
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sortListRefreshLayout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryFont));
        mRecyclerView = (RecyclerView) view.findViewById(R.id.sorListRecycleview);
        adapter = new SortListRecylcerViewAdapter(getContext(), mProductList);
        layoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
//            mHandler.sendEmptyMessageDelayed(0x111, 2000);
        presenter.getPageSortListProduct(sortId, 1);
    }

    @Override
    public void showLoading() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showFailedError() {

    }

    @Override
    public void showData(ArrayList<ProductBean.DataBean> list,boolean isLoadMore) {
        mProductList.clear();
        if (list != null) {
            if (!(mProductList.equals(list))) {
                adapter.addMoreData(list);
            }
        }
    }
}
