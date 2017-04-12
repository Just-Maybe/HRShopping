package com.example.helloworld.huaruanshopping.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.bean.ListAddress;
import com.example.helloworld.huaruanshopping.bean.address;
import com.example.helloworld.huaruanshopping.util.SharedPreferencesUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by helloworld on 2017/3/22.
 */

public class fragmentaddAddress extends Fragment {

    @BindView(R.id.nameEdit)
    EditText nameEdit;
    @BindView(R.id.phoneEdit)
    EditText phoneEdit;
    @BindView(R.id.addressEdit)
    EditText addressEdit;
    @BindView(R.id.saveInfo)
    TextView saveInfo;
    private View view;
    private address address;
    private int position;
    private List<address> addressList = new ArrayList<>();
    private ListAddress listAddress = new ListAddress();
    private Gson gson = new Gson();
    String TAG = "111";

    public static fragmentaddAddress newInstance(address address, int position) {

        Bundle args = new Bundle();
        args.putParcelable("address", address);
        args.putInt("position", position);
        fragmentaddAddress fragment = new fragmentaddAddress();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        this.address = bundle.getParcelable("address");
        this.position = bundle.getInt("position");
        Log.d(TAG, "onCreate: " + this.address);
        Log.d(TAG, "onCreate: " + position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_add_address, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        if (address != null) {
            nameEdit.setText(address.getName());
            phoneEdit.setText(address.getPhone());
            addressEdit.setText(address.getAddress());
        }
        if (getAddressList() != null) {
            addressList = getAddressList();
        }
    }


    @OnClick(R.id.saveInfo)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveInfo:
                if (addressEdit.getText().toString().equals("") || nameEdit.getText().toString().equals("") || phoneEdit.getText().toString().length() < 8) {
                    Snackbar.make(getView(), "信息不符合要求!", Snackbar.LENGTH_SHORT).show();
                    break;
                }
                if (address == null) {
                    addAddress();
                } else {
                    updateAddress();
                }

                getActivity().finish();
                break;
        }
    }

    private boolean isPhoneNum(String phoneNum) {
        Pattern pattern = Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\\\d{8}$");
        Matcher matcher = pattern.matcher(phoneNum);
        return matcher.matches();
    }

    private void addAddress() {
        address = new address();
        address.setName(nameEdit.getText().toString());
        address.setPhone(phoneEdit.getText().toString());
        address.setAddress(addressEdit.getText().toString());

        addTheAddress(address);
    }

    private void updateAddress() {
        address.setName(nameEdit.getText().toString());
        address.setPhone(phoneEdit.getText().toString());
        address.setAddress(addressEdit.getText().toString());
        addressList.remove(position);

        addressList.add(position, address);
        listAddress.setAddressList(addressList);
        String addressListGson = gson.toJson(listAddress);
        SharedPreferencesUtils.setParam(getActivity(), "addressList", addressListGson);
    }

    public void addTheAddress(address address) {
        addressList.add(address);
        listAddress.setAddressList(addressList);
        String addressListGson = gson.toJson(listAddress);
        SharedPreferencesUtils.setParam(getActivity(), "addressList", addressListGson);
    }

    public ArrayList getAddressList() {
        String addressListGson = (String) SharedPreferencesUtils.getParam(getActivity(), "addressList", "");
        if (addressListGson.equals("")) {
            return null;
        }
        listAddress = gson.fromJson(addressListGson, ListAddress.class);
        return (ArrayList) listAddress.getAddressList();
    }
}
