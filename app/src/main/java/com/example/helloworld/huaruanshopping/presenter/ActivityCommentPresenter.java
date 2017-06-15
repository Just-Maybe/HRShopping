package com.example.helloworld.huaruanshopping.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.CommentProduct;
import com.example.helloworld.huaruanshopping.bean.Response;
import com.example.helloworld.huaruanshopping.presenter.biz.IActivityCommentBiz;
import com.example.helloworld.huaruanshopping.presenter.implView.IActivityComment;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by helloworld on 2017/4/6.
 */

public class ActivityCommentPresenter implements IActivityCommentBiz {
    private final static String TAG = "111";
    private IActivityComment iActivityComment;

    public ActivityCommentPresenter(IActivityComment iActivityComment) {
        this.iActivityComment = iActivityComment;
    }

    @Override
    public void postComment(int pid, int uid, int star, String comment, List<String> imgList) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        RequestBody requestFile;
        MultipartBody.Part body;
        if (imgList.size() > 0) {
            for (int i = 0; i < imgList.size(); i++) {
                File file = new File(imgList.get(i).toString());
                Log.d(TAG, "uploadHeadIcon: " + file.getAbsolutePath());
                requestFile = RequestBody.create(MediaType.parse("image/*"), file);
                body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                builder.addPart(body);
            }
        }
        List<MultipartBody.Part> parts = builder.build().parts();

        Observable<Response> observable = HttpMethods.getInstance().getProductDescribeService().postComment(pid, uid, star, comment, parts);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Log.d(TAG, "onNext: " + response.getMsg());
//                        Toast.makeText(, "Success", Toast.LENGTH_SHORT).show();
                        iActivityComment.onSuccessPostComment();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void postAppendComment(int comment_id, int pid, int uid, String append_comment, List<String> imgList) {
//        Log.d(TAG, "onNext: "+comment_id+pid+uid+append_comment);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        RequestBody requestFile;
        MultipartBody.Part body;
        if (imgList.size() > 0) {
            for (int i = 0; i < imgList.size(); i++) {
                File file = new File(imgList.get(i).toString());
                Log.d(TAG, "uploadHeadIcon: " + file.getAbsolutePath());
                requestFile = RequestBody.create(MediaType.parse("image/*"), file);
                body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                builder.addPart(body);
            }
        }
        List<MultipartBody.Part> parts = builder.build().parts();

        Observable<Response> observable = HttpMethods.getInstance().getProductDescribeService().postAppendComment(comment_id, pid, uid, append_comment, parts);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Log.d(TAG, "onNext: " + response.getMsg());
                        iActivityComment.onSuccessPostComment();
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
    public void getCommentProduct(int pid, int uid) {
        Observable<CommentProduct> observable = HttpMethods.getInstance().getProductDescribeService().getCommentProduct(pid, uid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentProduct>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommentProduct commentProduct) {
//                        Log.d(TAG, "onNext: "+commentProduct.getData().getComment().getComment());
                        iActivityComment.getCommentProduct(commentProduct.getData());
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