package com.example.helloworld.huaruanshopping.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.Response;
import com.example.helloworld.huaruanshopping.fragment.fragmentInfo;
import com.example.helloworld.huaruanshopping.presenter.biz.IFragmentInfoBiz;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentUpdateInfo;

import org.reactivestreams.Subscriber;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * Created by helloworld on 2017/3/22.
 */

public class FragmentInfoPresenter implements IFragmentInfoBiz {
    IFragmentUpdateInfo fragmentUpdateInfo;
    public static final String TAG = "111";

    public FragmentInfoPresenter(IFragmentUpdateInfo fragmentInfo) {
        this.fragmentUpdateInfo = fragmentInfo;
    }

    public void updateUserName(int id, String userName) {
        Observable<Response> observable = HttpMethods.getInstance().getUpdateInfoService().updateUserName(id, userName);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Log.d(TAG, "onNext: " + response.getMsg());
                        fragmentUpdateInfo.onSuccessUpdate();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void uploadHeadIcon(int id, String filePath) {
        File file = new File(filePath);
        Log.d(TAG, "uploadHeadIcon: " + file.getAbsolutePath());
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
//        List<MultipartBody.Part> bodyList = new ArrayList<>();
//        bodyList.add(body);
        Observable<Response> observable = HttpMethods.getInstance().getUpdateInfoService().uploadHeanIcon(id, body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Log.d(TAG, "onNext: headIcon" + response.getMsg());
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
