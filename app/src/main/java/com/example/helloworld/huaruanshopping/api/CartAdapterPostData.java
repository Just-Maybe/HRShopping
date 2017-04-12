package com.example.helloworld.huaruanshopping.api;

import android.util.Log;

import com.example.helloworld.huaruanshopping.bean.Response;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by helloworld on 2017/3/13.
 */

public class CartAdapterPostData {
    private final static String TAG = "111";
    private static updateListener listener;

    public void setOnUpdateListen(updateListener listener) {
        this.listener = listener;
    }

    public interface updateListener {
        void onFailed();

        void onSuccess();
    }

    public static void updateCartData(int id, int number, final updateListener listener) {
        Observable<Response> observable = HttpMethods.getInstance().getCartService().updateCart(id, number);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Log.d(TAG, "onNext: " + response.getMsg());
                        listener.onSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void deleteItem(int id) {
        Observable<Response> observable = HttpMethods.getInstance().getCartService().deleteCartItem(id);
        {
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
}
