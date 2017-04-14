package com.example.helloworld.huaruanshopping.presenter;

import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.ProductBean;
import com.example.helloworld.huaruanshopping.presenter.biz.IFragmentHomeBiz;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentBaseView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by helloworld on 2017/2/21.
 */

public class FragmentHomePresenter implements IFragmentHomeBiz {
    IFragmentBaseView iFragmentHome;
    private static final String TAG = "111";

    public FragmentHomePresenter(IFragmentBaseView iFragmentHome) {
        this.iFragmentHome = iFragmentHome;
    }

    public void getHomePrudoct(int flag, int pageNum, final boolean isLoadMore) {
        Observable<ProductBean> observable = HttpMethods.getInstance().getHomeProductApi().getHomeListProduct(flag, pageNum);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductBean productBean) {
//                        Log.d(TAG, "onNext: " + listProduct.getStatus());
//                        Log.d(TAG, "onNext: " + listProduct.getProductList().size());
                        if (productBean.getData() != null) {
                            if (productBean.getData().size() % 4 == 0) {
                                iFragmentHome.showData(productBean.getData(),isLoadMore);
                            }
                        }
                        iFragmentHome.showFailedError();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        iFragmentHome.hideLoading();
                    }
                });
    }

    @Override
    public void getMoreHomePrudoctData(int pageNum) {

    }
}
