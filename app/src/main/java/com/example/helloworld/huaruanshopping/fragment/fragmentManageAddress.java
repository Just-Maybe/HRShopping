package com.example.helloworld.huaruanshopping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.adapter.ManagerAddressAdapter;
import com.example.helloworld.huaruanshopping.bean.ListAddress;
import com.example.helloworld.huaruanshopping.bean.address;
import com.example.helloworld.huaruanshopping.util.SharedPreferencesUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.helloworld.huaruanshopping.util.SharedPreferencesUtils.getParam;

/**
 * Created by helloworld on 2017/3/22.
 */

public class fragmentManageAddress extends Fragment {

    @BindView(R.id.addressRecylerView)
    RecyclerView addressRecylerView;
    private View view;
    private List<address> lists = new ArrayList<>();
    private ManagerAddressAdapter adapter;
    private LinearLayoutManager layoutManager;
    private final static String TAG = "111";
    private ListAddress listAddres = new ListAddress();
    private Gson gson;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_manager_address, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        Log.d(TAG, "onResume: ");
    }

    private void initData() {

        address address = new address();
        address.setAddress("asdasd");
        address.setName("asdasd");
        address.setPhone("1113123");
        adapter.addTheAddress(address);
    }

    private void initView() {
        if (getAddressList() != null) {
            lists = getAddressList();
        }
        adapter = new ManagerAddressAdapter(getActivity(), lists);
        layoutManager = new LinearLayoutManager(getActivity());
        addressRecylerView.setLayoutManager(layoutManager);
        addressRecylerView.setAdapter(adapter);
//        initData();
    }

    public ArrayList getAddressList() {
        String addressListGson = (String) SharedPreferencesUtils.getParam(getActivity(), "addressList", "");
        Log.d(TAG, "getAddressList: " + addressListGson);
        if (addressListGson.equals("")) {
            return null;
        }
        gson = new Gson();
        listAddres = gson.fromJson(addressListGson, ListAddress.class);
        return (ArrayList) listAddres.getAddressList();
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }
}
