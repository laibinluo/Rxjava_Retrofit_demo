package com.robin.myapplication;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by robinluo on 2017/7/3.
 */

public interface Api {
    @FormUrlEncoded
    @POST("getCode")
    Observable<ResponseModel_nolist> getCode(@Field("tel") String tel);



    @FormUrlEncoded
    @POST("register")
    Observable<ResponseModel_nolist> register(@Field("tel") String tel,
                                              @Field("passwd") String passwd,
                                              @Field("code") String code);


}
