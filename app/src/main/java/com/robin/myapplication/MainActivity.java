package com.robin.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

//import com.robin.mylibrary.TestLib;

import junit.framework.Test;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.Subject;

public class MainActivity extends AppCompatActivity {

    private String TAG = "TEST";
    private Subscription rx;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TestLib.Test1();
//        TestLib.Test2();
        IntentFilter intent = new IntentFilter();
        intent.addAction(Intent.ACTION_MEDIA_BUTTON);

       // registerReceiver(new TestMedia(), intent);
        tv = (TextView) findViewById(R.id.test);

    }

    private class TestMedia extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "onReceive action : " + action);
            KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
            Log.i(TAG, "Action ---->" + action + "  KeyEvent----->"+ keyEvent.toString());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //rx = User.test_main(tv);
        Log.d(TAG, "onStart");
        final User user =  new User("robin", "123456");

        //tv.setText("t ------");
        rx= RxBus.getDefaultInstance()
                .toObservable(User.class)
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        Log.d("TEST", " subscribe call");
                        System.out.println("!!!!!!!!!!!!!!!");
                        System.out.println("subscribe : " + user.toString());
                        tv.setText(user.toString());
                    }
                } );

        RxBus.getDefaultInstance().post(user);
//        Observable observable = Observable.create(new Observable.OnSubscribe<User>(){
//            @Override
//            public void call(Subscriber<? super User> subscriber) {
//                User user =  new User("robin", "123456");
//                subscriber.onNext(user);
//            }
//        });
//        observable.subscribe(subscriber);
    }

    private Subscriber<User> subscriber = new Subscriber<User>() {
        @Override
        public void onCompleted() {
            Log.d("TEST", "onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.d("TEST", "onError");
        }

        @Override
        public void onNext(User user) {
            Log.d("TEST", "onNext");
            System.out.println("!!!!!!!!!!!!!!!");
            System.out.println("subscribe : " + user.toString());
            tv.setText(user.toString());
            tv.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (rx.isUnsubscribed()){
            rx.unsubscribe();
        }
    }
}
