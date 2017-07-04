package com.robin.myapplication.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.Excluder;
import com.robin.myapplication.RxBus;
import com.robin.myapplication.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by robinluo on 2017/7/4.
 *
 */

public class TestGson {
    public static String requestUrl = "http://api.map.baidu.com/telematics/v3/weather?location=上海&output=json&ak=D6607d3e2d2408a7a98ee44ec519f8cc";
    public static String baseUrl="http://api.map.baidu.com/telematics/v3/";
    public interface WeatherServer{
        @GET
        Observable<Result<WeatherInfo>> getWeatherInfo(@Url String url);

        @GET("weather?location=上海&output=json&ak=D6607d3e2d2408a7a98ee44ec519f8cc")
        Observable<Result<WeatherInfo>> getWeatherInfo();
        //Call<Result<WeatherInfo>> getWeatherInfo();

        @GET("weather?location=上海&output=json&ak=D6607d3e2d2408a7a98ee44ec519f8cc")
        Call<Result<WeatherInfo>> getinfo();

        @GET("weather?location=上海&output=json&ak=D6607d3e2d2408a7a98ee44ec519f8cc")
        Call<ResponseBody> getBaiduInfo();
    }

    public static void main(String args[]) {
        System.out.println("TestGson main");
//        User user = new User("robin", "sfsdf", 23, "xx@qq.com");
//
//        Gson gson = new Gson();
//        String jsonObject = gson.toJson(user);
//
//        // 生成json对象
//        System.out.println(jsonObject);
//
//        // 解析json
//        //Gson gson =  new Gson();
//        User user2 = gson.fromJson(jsonObject, User.class);
//        System.out.println(user2.toString());

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy=MM=dd")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TestGson.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        WeatherServer weatherServer =  retrofit.create(WeatherServer.class);
        // retrofit
//        Call<Result<WeatherInfo>> call  = weatherServer.getWeatherInfo();
//
//        call.enqueue(new Callback<Result<WeatherInfo>>() {
//            @Override
//            public void onResponse(Call<Result<WeatherInfo>> call, Response<Result<WeatherInfo>> response) {
//                System.out.println("================== body : ");
//                System.out.println(response.body());
//                Result<WeatherInfo> result = response.body();
//                System.out.println("================== reuslt : ");
//                System.out.println(result.toString());
//            }
//
//            @Override
//            public void onFailure(Call<Result<WeatherInfo>> call, Throwable t) {
//                System.out.println("================== t : " + t);
//            }
//        });

        // gson + retrofit
//        Call<Result<WeatherInfo>> call = weatherServer.getinfo();
//        call.enqueue(new Callback<Result<WeatherInfo>>() {
//            @Override
//            public void onResponse(Call<Result<WeatherInfo>> call, Response<Result<WeatherInfo>> response) {
//                System.out.println("gson + retrofit body : ");
//                System.out.println(response.body().toString());
//                Result<WeatherInfo> result =  response.body();
//                System.out.println("======================result : ");
//                System.out.println(result.toString());
//            }
//
//            @Override
//            public void onFailure(Call<Result<WeatherInfo>> call, Throwable t) {
//                System.out.println("t: "  + t);
//            }
//        });

        weatherServer.getWeatherInfo(requestUrl)
                //.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Result<WeatherInfo>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString());
                    }

                    @Override
                    public void onNext(Result<WeatherInfo> weatherInfoResult) {
                        System.out.println(weatherInfoResult.toString());
                    }
                });
    }
}
