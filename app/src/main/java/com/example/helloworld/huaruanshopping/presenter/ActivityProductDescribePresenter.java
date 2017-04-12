package com.example.helloworld.huaruanshopping.presenter;

import android.util.Log;

import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.Comment;
import com.example.helloworld.huaruanshopping.bean.ProductBean;
import com.example.helloworld.huaruanshopping.bean.Response;
import com.example.helloworld.huaruanshopping.bean.productDescribe;
import com.example.helloworld.huaruanshopping.presenter.biz.IActivityProductDescribeBiz;
import com.example.helloworld.huaruanshopping.presenter.implView.IActivityProductDescribe;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by helloworld on 2017/3/11.
 */

public class ActivityProductDescribePresenter implements IActivityProductDescribeBiz {
    IActivityProductDescribe implView;
    private final static String TAG = "111";

    public ActivityProductDescribePresenter(IActivityProductDescribe implView) {
        this.implView = implView;
    }

    @Override
    public void getProductDescribe(int pid) {
        Observable<ProductBean> observable = HttpMethods.getInstance().getProductDescribeService().getProductDescribe(pid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductBean product) {
                        Log.d(TAG, "onNext: " + product.getData().get(0).getName());
                        implView.getProduceDescribeData(product.getData().get(0));
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
    public void addToCar(int ptid, int number, int id, String token) {
        Log.d(TAG, "addToCar: " + ptid + " " + number + "   " + id + "   " + token);
        Observable<Response> observable = HttpMethods.getInstance().getPostProductService().getPostRespnose(ptid, number, id, token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Log.d(TAG, "onNext: " + response.getMsg());
                        Log.d(TAG, "onNext: " + response);
                        implView.OnSuccessAddToCar();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
