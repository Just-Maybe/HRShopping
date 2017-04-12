package com.example.helloworld.huaruanshopping.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.Response;
import com.example.helloworld.huaruanshopping.bean.orderList;
import com.example.helloworld.huaruanshopping.presenter.biz.IActivityFindAllOrderBiz;
import com.example.helloworld.huaruanshopping.presenter.implView.IActivityFindAllOrder;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by helloworld on 2017/3/18.
 */

public class ActivityFindAllOrderPresenter implements IActivityFindAllOrderBiz {
    IActivityFindAllOrder iActivityFindAllOrder;
    String TAG = "11";

    public ActivityFindAllOrderPresenter() {
    }

    public ActivityFindAllOrderPresenter(IActivityFindAllOrder iActivityFindAllOrder) {
        this.iActivityFindAllOrder = iActivityFindAllOrder;
    }

    @Override
    public void getAllOrder(int userid, int pageNum, String token, final String flag) {
        Observable<orderList> observable = HttpMethods.getInstance().getFindAllOrderService().getAllOrder(userid, pageNum, token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<orderList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(orderList orderList) {
                        Log.d("111", "onNext: " + orderList.getData().get(0).getSorderSet().get(0).getPrice());
                        iActivityFindAllOrder.getAllOrder(orderList.getData());
                        if (flag == "cancel") {
                            iActivityFindAllOrder.onSuccessCancelOrder();
                        } else {
                            iActivityFindAllOrder.onSuccessGetAllOrder();
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
    public void deleteOrder(String fid,int id,String token) {
        Observable<Response> observable = HttpMethods.getInstance().findAllOrderService.deleteOrder(fid,id,token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Log.d(TAG, "onNext: " + response.getMsg());
//                        iActivityFindAllOrder.onSuccessDelete();
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
    public void cancelOrder(String fid,int id,String token) {
        Observable<Response> observable = HttpMethods.getInstance().getFindAllOrderService().cancelOrder(fid,id,token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Log.d(TAG, "onNext: " + response.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
