package com.example.helloworld.huaruanshopping.presenter;

import android.util.Log;

import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.Category;
import com.example.helloworld.huaruanshopping.bean.categoryList;
import com.example.helloworld.huaruanshopping.listener.OnBaseListener;
import com.example.helloworld.huaruanshopping.presenter.biz.IFragmentSortBiz;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentSort;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by helloworld on 2017/2/25.
 */

public class FragmentSortPresenter implements IFragmentSortBiz {
    IFragmentSort iFragmentSort;

    public FragmentSortPresenter(IFragmentSort iFragmentSort) {
        this.iFragmentSort = iFragmentSort;
    }

    public void getCategory() {
        Observable<Category> observable = HttpMethods.getInstance().getCategoryService().getCategory();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Category>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Category category) {
//                        Log.d("11", "onNext: " + categoryList.getCategoryList().get(0).getType());

                        iFragmentSort.setCategoryText(setCategoryText(category.getData()));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private List<String> setCategoryText(List<Category.DataBean> categories) {
        List<String> categoryText = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            categoryText.add(categories.get(i).getType());
        }
        return categoryText;
    }
}
