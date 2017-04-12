package com.example.helloworld.huaruanshopping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.adapter.ConfirmationAdapter;
import com.example.helloworld.huaruanshopping.bean.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by helloworld on 2017/1/22.
 */

public class fragmentConfirm extends Fragment {
    private RecyclerView confirmRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ConfirmationAdapter adapter;
    private List<Product> mList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Product product = new Product();
        mList.add(new Product());
       mList.add(product);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getContext()).inflate(R.layout.fragment_confirm, null, false);
        confirmRecyclerView = (RecyclerView) view.findViewById(R.id.confirmRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
//        adapter = new ConfirmationAdapter(mList, getContext());
//        confirmRecyclerView.setLayoutManager(layoutManager);
//        confirmRecyclerView.setAdapter(adapter);
        return view;
    }
}
