package com.example.helloworld.huaruanshopping.presenter;

import android.util.Log;

import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.ProductBean;
import com.example.helloworld.huaruanshopping.presenter.biz.IFragmentBusinessBiz;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentBusiness;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by helloworld on 2017/5/4.
 */

public class FragmentBusinessPresenter implements IFragmentBusinessBiz {
    private String TAG = "111";
    private IFragmentBusiness iFragmentBusiness;

    public FragmentBusinessPresenter(IFragmentBusiness iFragmentBusiness) {
        this.iFragmentBusiness = iFragmentBusiness;
    }

    @Override
    public void getBusinessNewProduct(int id, int pageNum) {
        Observable<ProductBean> observable = HttpMethods.getInstance().getProductDescribeService().getBusinessNewProducts(id, pageNum);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductBean productBean) {
                        Log.d(TAG, "onNext: " + productBean.getData().get(0).getName());
                        iFragmentBusiness.showData(productBean.getData());
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
    public void getBusinessAllProduct(int id, int pageNum) {
        Observable<ProductBean> observable = HttpMethods.getInstance().getProductDescribeService().getBusinessAllProducts(id, pageNum);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductBean productBean) {
                        Log.d(TAG, "onNext: " + productBean.getData().get(0).getName());
                        iFragmentBusiness.showData(productBean.getData());
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
