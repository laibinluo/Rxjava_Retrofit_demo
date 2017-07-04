package com.robin.myapplication;

/**
 * Created by robinluo on 2017/7/3.
 */

public interface HttpResultListener<T> {
    void onSuccess(T t);
    void onError(Throwable throwable);
}
