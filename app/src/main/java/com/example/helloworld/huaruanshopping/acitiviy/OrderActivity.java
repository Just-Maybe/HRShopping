package com.example.helloworld.huaruanshopping.acitiviy;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.adapter.ConfirmationAdapter;
import com.example.helloworld.huaruanshopping.bean.CartBean;
import com.example.helloworld.huaruanshopping.bean.ListAddress;
import com.example.helloworld.huaruanshopping.bean.OrderJsonBean;
import com.example.helloworld.huaruanshopping.bean.Product;
import com.example.helloworld.huaruanshopping.bean.address;
import com.example.helloworld.huaruanshopping.bean.orderList;
import com.example.helloworld.huaruanshopping.presenter.ActivityOrderPresenter;
import com.example.helloworld.huaruanshopping.presenter.implView.IActivityOrder;
import com.example.helloworld.huaruanshopping.util.SharedPreferencesUtils;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import c.b.BP;
import c.b.PListener;

/**
 * Created by helloworld on 2017/1/22.
 */

public class OrderActivity extends AppCompatActivity implements IActivityOrder {
    @BindView(R.id.continueLayout)
    LinearLayout continueLayout;
    @BindView(R.id.back)
    ImageView back;
    private Toolbar toolbar;
    private RecyclerView confirmRecyclerView;
    LinearLayoutManager layoutManager;
    ConfirmationAdapter adapter;
    Gson gson = new Gson();
    private orderList.DataBean jsonBean;
    private OrderJsonBean orderJson = new OrderJsonBean();
    private address address;
    private String addressStr;
    private String nameStr;
    private String phoneStr;
    private ListAddress listAddress = new ListAddress();
    String TAG = "111";
    ProgressDialog dialog;
    private ActivityOrderPresenter presenter;
    String jsonString = "{'name': 'sysho','address':'清风阁','remark':'加两双筷子','phone': '123124325235','cart':[{'id':1,'number':43,'protype':{'id': 4,'name':'老坛酸菜','pic':'4.jpg','inventory':100,'product':{'id':2,'name':'方便面','price': 23.00}}},{'id': 6,'number': 3,'protype':{'id': 3,'name':'烧烤味','pic':'4.jpg','inventory':50,'product':{'id': 2,'name':'薯片','price': 23.00}}}]}";

    private String token = "532a8a18b75079da0c48414014600600d64737f36e330997";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //透明导航栏
        presenter = new ActivityOrderPresenter(this, this);
        initToolbar();
        initData();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.d(TAG, "onResume: " + getAddressList().size());
        if (getAddressList() != null && getAddressList().size() > 0) {
            address = (address) getAddressList().get(0);
            nameStr = address.getName();
            addressStr = address.getAddress();
            phoneStr = address.getPhone();
            jsonBean.setAddress(addressStr);
            jsonBean.setName(nameStr);
            jsonBean.setPhone(phoneStr);
        } else {
            jsonBean.setAddress("");
            jsonBean.setName("");
            jsonBean.setPhone("先添加一个地址");
        }
        jsonBean.setRemark(" ");
        adapter.notifyDataSetChanged();
    }

    public ArrayList getAddressList() {
        String addressListGson = (String) SharedPreferencesUtils.getParam(this, "addressList", "");
        if (addressListGson.equals("")) {
            return null;
        }
        listAddress = gson.fromJson(addressListGson, ListAddress.class);
        return (ArrayList) listAddress.getAddressList();
    }

    private void initData() {
        Intent intent = getIntent();
        jsonBean = intent.getParcelableExtra("jsonBean");
    }

    private void initView() {
        confirmRecyclerView = (RecyclerView) findViewById(R.id.confirmRecyclerView);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        adapter = new ConfirmationAdapter(jsonBean, getApplicationContext());
        confirmRecyclerView.setLayoutManager(layoutManager);
        confirmRecyclerView.setAdapter(adapter);
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @OnClick({R.id.continueLayout, R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.continueLayout:
                if (isInfoEmpty()) {
                    Snackbar.make(continueLayout, "还没有添加地址", Snackbar.LENGTH_SHORT).show();
                } else {
//                    orderJson = jsonBean;
                    presenter.transformJson(jsonBean, orderJson);
                    presenter.orderProducts(1, gson.toJson(orderJson, OrderJsonBean.class), token, adapter.isZhifubao());
                }
//                presenter.pay(adapter.isZhifubao());
                break;
            case R.id.back:
                this.finish();
                break;
        }
    }


    @Override
    public void showDialog(String message) {
        try {
            if (dialog == null) {
                dialog = new ProgressDialog(this);
                dialog.setCancelable(true);
            }
            dialog.setMessage(message);
            dialog.show();
        } catch (Exception e) {
            // 在其他线程调用dialog会报错
        }
    }

    @Override
    public void hideDialog() {
        if (dialog != null && dialog.isShowing())
            try {
                dialog.dismiss();
            } catch (Exception e) {
            }
    }

    @Override
    public void installZhifubaoTips() {
        Toast.makeText(this, "请安装支付宝客户端", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void orderResult(int result) {

    }

    boolean isInfoEmpty() {
        return jsonBean.getAddress().equals("") || jsonBean.getPhone().equals("") || jsonBean.getName().equals("");
    }

    @Override
    protected void onStop() {
        super.onStop();
        jsonBean=null;
        orderJson=null;
    }
}
