package com.example.helloworld.huaruanshopping.presenter;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.helloworld.huaruanshopping.acitiviy.FindAllOrderActivity;
import com.example.helloworld.huaruanshopping.acitiviy.OrderActivity;
import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.OrderJsonBean;
import com.example.helloworld.huaruanshopping.bean.Response;
import com.example.helloworld.huaruanshopping.bean.orderList;
import com.example.helloworld.huaruanshopping.presenter.biz.IActivityOrderBiz;
import com.example.helloworld.huaruanshopping.presenter.implView.IActivityOrder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import c.b.BP;
import c.b.PListener;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by helloworld on 2017/4/15.
 */

public class ActivityOrderPresenter implements IActivityOrderBiz {
    IActivityOrder iActivityOrder;
    int PLUGINVERSION = 7;
    private static final int REQUESTPERMISSION = 101;
    private OrderActivity mContext;
    String TAG = "111";
    String fId;

    public ActivityOrderPresenter(OrderActivity context, IActivityOrder iActivityOrder) {
        this.iActivityOrder = iActivityOrder;
        mContext = context;
    }

    public void transformJson(orderList.DataBean jsonBean, OrderJsonBean orderJson) {
//        Log.d(TAG, "transformJson: "+jsonBean.getPhone()+jsonBean.getAddress()+jsonBean.getName());
        List<OrderJsonBean.CartBean> list = new ArrayList<>();
        OrderJsonBean.CartBean bean = new OrderJsonBean.CartBean();
        OrderJsonBean.CartBean.ProtypeBean protype = new OrderJsonBean.CartBean.ProtypeBean();
        OrderJsonBean.CartBean.ProtypeBean.ProductBean product = new OrderJsonBean.CartBean.ProtypeBean.ProductBean();
//        CartBean.DataBean.ProtypeBean.ProductBean productBean = new CartBean.DataBean.ProtypeBean.ProductBean();
        for (int i = 0; i < jsonBean.getSorderSet().size(); i++) {
            product.setName(jsonBean.getSorderSet().get(i).getProtype().getProduct().getName());
            product.setId(jsonBean.getSorderSet().get(i).getProtype().getProduct().getId());
            product.setPrice(jsonBean.getSorderSet().get(i).getProtype().getProduct().getPrice());

            protype.setPic(jsonBean.getSorderSet().get(i).getProtype().getPic());
            protype.setId(jsonBean.getSorderSet().get(i).getProtype().getId());
            protype.setName(jsonBean.getSorderSet().get(i).getProtype().getName());
            protype.setInventory(jsonBean.getSorderSet().get(i).getProtype().getInventory());

            bean.setId(jsonBean.getSorderSet().get(i).getId());
            bean.setNumber(jsonBean.getSorderSet().get(i).getNumber());

            protype.setProduct(product);
            bean.setProtype(protype);
            list.add(bean);
            bean = new OrderJsonBean.CartBean();
            protype = new OrderJsonBean.CartBean.ProtypeBean();
            product = new OrderJsonBean.CartBean.ProtypeBean.ProductBean();
        }
        orderJson.setPhone(jsonBean.getPhone());
        orderJson.setAddress(jsonBean.getAddress());
        orderJson.setRemark(jsonBean.getRemark());
        orderJson.setName(jsonBean.getName());
        orderJson.setCart(list);
    }

    @Override
    public void orderProducts(int id, String json, String token, final boolean alipayOrWechatPay) {
        Log.d(TAG, "OrderProducts: " + json);
        Observable<Response> observable = HttpMethods.getInstance().getCartService().order(1, json, token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Response response) {
                        Log.d(TAG, "onNext: " + response.getData());
                        if (!response.getData().equals("")) {
                            fId = response.getData();
                            pay(alipayOrWechatPay);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void orderResult(String bombId, String fid) {
        Observable<Response> orderResult = HttpMethods.getInstance().getCartService().orderResult(bombId, fid);
        orderResult.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Log.d(TAG, "onNext: " + response.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void pay(boolean alipayOrWechatPay) {
        if (alipayOrWechatPay) {
            if (!checkPackageInstalled("com.eg.android.AlipayGphone",
                    "https://www.alipay.com")) { // 支付宝支付要求用户已经安装支付宝客户端
                iActivityOrder.installZhifubaoTips();
                return;
            }
        } else {
            if (checkPackageInstalled("com.tencent.mm", "http://weixin.qq.com")) {// 需要用微信支付时，要安装微信客户端，然后需要插件
                // 有微信客户端，看看有无微信支付插件
                int pluginVersion = BP.getPluginVersion(mContext);
                if (pluginVersion < PLUGINVERSION) {// 为0说明未安装支付插件,
                    // 否则就是支付插件的版本低于官方最新版
                    Toast.makeText(
                            mContext.getApplicationContext(),
                            pluginVersion == 0 ? "监测到本机尚未安装支付插件,无法进行支付,请先安装插件(无流量消耗)"
                                    : "监测到本机的支付插件不是最新版,最好进行更新,请先更新插件(无流量消耗)",
                            Toast.LENGTH_SHORT).show();
//                    installBmobPayPlugin("bp.db");

                    installApk("bp.db");
                    return;
                }
            } else {// 没有安装微信
                Toast.makeText(mContext.getApplicationContext(), "请安装微信客户端", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        iActivityOrder.showDialog("正在获取订单...\nSDK版本号:" + BP.getPaySdkVersion());
//        final String name = getName();

        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName("com.bmob.app.sport",
                    "com.bmob.app.sport.wxapi.BmobActivity");
            intent.setComponent(cn);
            mContext.startActivity(intent);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        BP.pay("", "", 0.01, alipayOrWechatPay, new PListener() {

            // 因为网络等原因,支付结果未知(小概率事件),出于保险起见稍后手动查询
            @Override
            public void unknow() {
                Toast.makeText(mContext.getApplicationContext(), "支付结果未知,请稍后手动查询", Toast.LENGTH_SHORT)
                        .show();
//                tv.append(name + "'s pay status is unknow\n\n");
                iActivityOrder.hideDialog();
            }

            // 支付成功,如果金额较大请手动查询确认
            @Override
            public void succeed() {
                Toast.makeText(mContext.getApplicationContext(), "支付成功!", Toast.LENGTH_SHORT).show();
//                log.d(name + "'s pay status is success\n\n");
                Log.d(TAG, "succeed: \"'s pay status is success\\n\\n\"");
                iActivityOrder.hideDialog();
            }

            // 无论成功与否,返回订单号
            @Override
            public void orderId(String orderId) {
                // 此处应该保存订单号,比如保存进数据库等,以便以后查询
//                order.setText(orderId);
//                tv.append(name + "'s orderid is " + orderId + "\n\n");
//                orderResult(orderId, fId);
                iActivityOrder.showDialog("获取订单成功!请等待跳转到支付页面~");
            }

            // 支付失败,原因可能是用户中断支付操作,也可能是网络原因
            @Override
            public void fail(int code, String reason) {

                // 当code为-2,意味着用户中断了操作
                // code为-3意味着没有安装BmobPlugin插件
                if (code == -3) {
                    Toast.makeText(
                            mContext.getApplicationContext(),
                            "监测到你尚未安装支付插件,无法进行支付,请先安装插件(已打包在本地,无流量消耗),安装结束后重新支付",
                            Toast.LENGTH_SHORT).show();
//                    installBmobPayPlugin("bp.db");
                    installApk("bp.db");
                } else {
                    Toast.makeText(mContext.getApplicationContext(), "支付中断!", Toast.LENGTH_SHORT)
                            .show();
//                    Intent intent = new Intent(mContext, FindAllOrderActivity.class);
//                    mContext.startActivity(intent);
                }
                Log.d(TAG, "fail: " + "'s pay status is fail, error code is \n"
                        + code + " ,reason is " + reason + "\n\n");
                iActivityOrder.hideDialog();

            }
        });
    }

    /**
     * 检查某包名应用是否已经安装
     *
     * @param packageName 包名
     * @param browserUrl  如果没有应用市场，去官网下载
     * @return
     */
    private boolean checkPackageInstalled(String packageName, String browserUrl) {
        try {
            // 检查是否有支付宝客户端
            mContext.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            // 没有安装支付宝，跳转到应用市场
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=" + packageName));
                mContext.startActivity(intent);
            } catch (Exception ee) {// 连应用市场都没有，用浏览器去支付宝官网下载
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(browserUrl));
                    mContext.startActivity(intent);
                } catch (Exception eee) {
                    Toast.makeText(mContext.getApplicationContext(),
                            "您的手机上没有没有应用市场也没有浏览器，我也是醉了，你去想办法安装支付宝/微信吧",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
        return false;
    }

    private void installApk(String s) {
        if (ContextCompat.checkSelfPermission(mContext.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请权限
            ActivityCompat.requestPermissions(mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTPERMISSION);
        } else {
            installBmobPayPlugin(s);
        }
    }

    /**
     * 安装assets里的apk文件
     *
     * @param fileName
     */
    void installBmobPayPlugin(String fileName) {
        try {
            InputStream is = mContext.getAssets().open(fileName);
            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + fileName + ".apk");
            if (file.exists())
                file.delete();
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.parse("file://" + file),
                    "application/vnd.android.package-archive");
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
