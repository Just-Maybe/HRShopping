package com.example.helloworld.huaruanshopping.presenter;

import android.util.Log;

import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.Comment;
import com.example.helloworld.huaruanshopping.presenter.biz.IFragmentCommentBiz;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentComment;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by helloworld on 2017/4/6.
 */

public class FragmentCommentPresenter implements IFragmentCommentBiz {
    private final static String TAG = "111";
    IFragmentComment iFragmentComment;

    public FragmentCommentPresenter(IFragmentComment iFragmentComment) {
        this.iFragmentComment = iFragmentComment;
    }

    @Override
    public void getComment(int pid, int pageNum) {
        Observable<Comment> Observable = HttpMethods.getInstance().getProductDescribeService().getComment(pid, pageNum);
        Observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Comment>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Comment comment) {
//                        Log.d(TAG, "onNext: " + comment.getData().get(0).getComment());
                        if (comment.getData().size() > 0) {
                            iFragmentComment.getComment(comment.getData());
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
}
