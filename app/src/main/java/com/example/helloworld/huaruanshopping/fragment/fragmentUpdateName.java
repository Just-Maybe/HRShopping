package com.example.helloworld.huaruanshopping.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.presenter.FragmentInfoPresenter;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentUpdateInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by helloworld on 2017/3/22.
 */

public class fragmentUpdateName extends Fragment implements IFragmentUpdateInfo {
    @BindView(R.id.saveInfo)
    TextView saveInfo;
    @BindView(R.id.userNameEdit)
    EditText userNameEdit;
    FragmentInfoPresenter presenter;
    private String userName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_update_name, container, false);
        ButterKnife.bind(this, view);
        presenter = new FragmentInfoPresenter(this);
        return view;
    }

    @OnClick(R.id.saveInfo)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveInfo:
                userName = userNameEdit.getText().toString();
                updateName(userName);
                break;
        }
    }

    private void updateName(String userName) {
        Pattern pattern = Pattern.compile("^[\\\\u4e00-\\\\u9fa5]{3,5}$|^[\\\\dA-Za-z_]{3,10}$");
        Matcher matcher = pattern.matcher(userName);
        if (matcher.matches()) {
            presenter.updateUserName(1, userName);
        } else {
            Snackbar.make(getView(), "昵称不符合要求", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccessUpdate() {
        Intent intent = new Intent();
        intent.putExtra("username", userName);
        this.getActivity().setResult(3, intent);
        this.getActivity().finish();
    }

    @Override
    public void onFailedUpdate() {
        Snackbar.make(getView(), "网络出现问题!", Snackbar.LENGTH_SHORT).show();
    }

}
