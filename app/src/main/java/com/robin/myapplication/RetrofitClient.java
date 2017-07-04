package com.robin.myapplication;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by robinluo on 2017/7/3.
 *
 */

public class RetrofitClient {
    public static Retrofit getClient(Context context){
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())  //Gson 适配器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // RxJava 适配器
                .build();
    }
}
