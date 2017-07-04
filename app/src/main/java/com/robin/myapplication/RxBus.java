package com.robin.myapplication;





import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by robinluo on 2017/6/26.
 *
 */

public class RxBus {
    private static volatile RxBus defaultInstance;
    private final Subject<Object, Object> bus;

    public RxBus(){
        bus = new SerializedSubject<>(ReplaySubject.create());
    }

    public static RxBus getDefaultInstance(){
        if (defaultInstance == null){
            synchronized (RxBus.class){
                if (defaultInstance == null){
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance;
    }

    // 发送一个新的事件
    public void post(Object o){
        System.out.println("post  ==== ");
//        Log.d("TEST", "post");
        bus.onNext(o);
    }

    // 根据传递的eventType 类型返回特定类型(eventType)的观察者
    public <T> Observable<T> toObservable(Class<T> eventType){
        return  bus.ofType(eventType);
    }

}
