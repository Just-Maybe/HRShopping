package com.example.helloworld.huaruanshopping.presenter;

import android.util.Log;

import com.example.helloworld.huaruanshopping.api.HttpMethods;
import com.example.helloworld.huaruanshopping.bean.CartBean;
import com.example.helloworld.huaruanshopping.bean.Response;
import com.example.helloworld.huaruanshopping.presenter.biz.IFragmentCartBiz;
import com.example.helloworld.huaruanshopping.presenter.implView.IFragmentCart;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by helloworld on 2017/3/13.
 */

public class FragmentCartPresenter implements IFragmentCartBiz {
    IFragmentCart iFragmentCart;
    private final static String TAG = "111";
    String jsonString = "{'name':'sysho','address':'清风阁','remark':'加两双筷子','phone':'123124325235','cart':[{'id':1,'number':43,'protype':{'id':4,'name':'老坛酸菜','pic':'4.jpg','inventory':100,'product':{'id':2,'name':'方便面','price':23}}},{'id':6,'number':3,'protype':{'id':4,'name':'烧烤味','pic':'4.jpg','inventory':50,'product':{'id':2,'name':'薯片','price':23}}}]}";
    private String token="532a8a18b75079da0c48414014600600d64737f36e330997";

    public FragmentCartPresenter(IFragmentCart iFragmentCart) {
        this.iFragmentCart = iFragmentCart;
    }

    @Override
    public void getData(int id, String token) {
        Log.d(TAG, "getData: " + id + "  " + token);
        Observable<CartBean> observable = HttpMethods.getInstance().getCartService().getListCart(id, token);
//        Log.d(TAG, "getData: " + observable.toString());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CartBean cartBean) {
//                        listCart.getCart();
                        Log.d(TAG, "onNext: " + cartBean.getData().toString());
                        Log.d(TAG, "onNext: " + cartBean.getData().get(0).getProtype().getProduct().getPrice());
                        iFragmentCart.getCartList(cartBean.getData());
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
    public void isEnoughProduct(List<Integer> mPtids, List<Integer> mNumbers, int userid, String token) {
        String ptids = transformPtids(mPtids);
        String numbers = transformNumbers(mNumbers);
        Log.d(TAG, "OrderImmediately: " + ptids + "    " + numbers);
        Observable<Response> observable = HttpMethods.getInstance().getCartService().orderImmediately(ptids, numbers, userid, token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Log.d(TAG, "onNext: order " + response.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        iFragmentCart.onOrderSuccess();
//                        iFragmentCart.orderNow();
                    }
                });

    }

//    @Override
//    public void OrderProducts(int id,String json,String token) {
////        Log.d(TAG, "OrderProducts: "+jsonString);
//        Observable<Response> observable = HttpMethods.getInstance().getCartService().order(1,jsonString, token);
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Response>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//
//                    @Override
//                    public void onNext(Response response) {
//                        Log.d(TAG, "onNext: " + response);
//                        if (response == null) {
//                            Log.d(TAG, "onNext: null");
//                        }
//                        Log.d(TAG, "onNext: " + response.getMsg());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        iFragmentCart.onOrderSuccess();
//                    }
//                });
//    }


    public String transformPtids(List<Integer> mPtids) {
        String ptids = "";
        for (int i = 0; i < mPtids.size(); i++) {
            ptids = ptids + mPtids.get(i) + ",";
        }
        ptids = ptids.substring(0, ptids.length() - 1);
        return ptids;
    }

    public String transformNumbers(List<Integer> mPumbers) {
        String numbers = "";
        for (int i = 0; i < mPumbers.size(); i++) {
            numbers = numbers + mPumbers.get(i) + ",";
        }
        numbers = numbers.substring(0, numbers.length() - 1);
        return numbers;
    }
}
