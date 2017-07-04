package com.robin.myapplication;

import android.content.Context;
import android.util.Log;

import retrofit2.Retrofit;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by robinluo on 2017/7/3.
 *
 */

public class ApiService {

    private String requestUrl = "http://api.map.baidu.com/telematics/v3/weather?location=上海&output=json&ak=D6607d3e2d2408a7a98ee44ec519f8cc";
    private Api mApi;
    private Context mContext;
    private static ApiService mInstance;

    private ApiService(Context context){
        this.mContext = context;
        mApi = RetrofitClient.getClient(context).create(Api.class);
    }

    public static ApiService getInstance(Context context){
        if(mInstance == null){
            mInstance = new ApiService(context);
        }
        return mInstance;
    }

    /**
     *  获取验证码
     */
    public void getCode(HttpResultListener<Boolean> listener, final String tel){
        mApi.getCode(tel)
                .map(new HttpResultFuncNoList())
                .map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                       if (s.equals("ok")){
                           return true;
                       }else {
                           return false;
                       }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultSubscriber<Boolean>(listener));

    }

    private class HttpResultFuncNoList implements Func1<ResponseModel_nolist, String>{
        @Override
        public String call(ResponseModel_nolist responseModel_nolist) {
            if (responseModel_nolist.getCode() == NetworkException.REQUEST_OK){
                Log.i("lin", "---lin--->  目前没发生错误：  " + responseModel_nolist.getCode());
                return responseModel_nolist.getMsg();
            }else {
                Log.i("lin", "---lin--->  错误代码：  " + responseModel_nolist.getCode());
                throw new NetworkException(responseModel_nolist.getCode());
            }
        }
    }

    private static class HttpResultSubscriber<T> extends Subscriber<T>{
        private HttpResultListener<T> mListener;

        public HttpResultSubscriber(HttpResultListener<T> listener){
            mListener = listener;
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            if (mListener != null){
                mListener.onError(e);
            }
        }

        @Override
        public void onNext(T t) {
            if (mListener != null){
                mListener.onSuccess(t);
            }
        }
    }

    public static void main(String args[]) {
        System.out.println("Hello World!");


    }
}
