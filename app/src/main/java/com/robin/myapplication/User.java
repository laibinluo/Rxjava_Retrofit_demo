package com.robin.myapplication;

import android.util.Log;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by robinluo on 2017/6/26.
 *
 */

public class User {
    private String username;
    private String password;
    private int age;
    @SerializedName("email_address")
    private String emailAddress;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, int age ,String emailAddress) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        //return super.toString();
        return "username : " + username + ", password : " + password ;
    }



    public static void main(String args[]) {
        System.out.println("Hello World!");

        User user =  new User("robin", "123456");
        RxBus.getDefaultInstance().post(user);
        RxBus.getDefaultInstance().toObservable(User.class).subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
               // Log.d("TEST", user.toString());
                System.out.println("!!!!!!!!!!!!!!!");
                System.out.println(user.toString());
            }
        });

//        RxBus.getDefaultInstance().post(user);
    }

    public static Subscription test_main(final TextView tv) {
        System.out.println("Hello World!");

        User user =  new User("robin", "123456");
        RxBus.getDefaultInstance().post(user);

        Subscription  rxSubscription= RxBus.getDefaultInstance().toObservable(User.class).subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
               // Log.d("TEST", user.toString());
                System.out.println("!!!!!!!!!!!!!!!");
                System.out.println("subscribe : " + user.toString());
                tv.setText(user.toString());
            }
        });

        return rxSubscription;
    }
}
