package com.robin.myapplication.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by robinluo on 2017/7/4.
 *
 */

public class Result<T> {
    //@SerializedName("error")
    public int error;
    public String status;
    public String date;
    public List<T> results;

    @Override
    public String toString() {
        return "error : " + error + ", status :" + status +", date : " + date +", results: " + results.toString();
    }
}
