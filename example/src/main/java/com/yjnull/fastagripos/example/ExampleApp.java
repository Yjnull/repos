package com.yjnull.fastagripos.example;

import android.app.Application;

import com.yjnull.latte_core.app.Latte;

/**
 * Created by Yangya on 2018/7/2
 */
public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1/")
                .configure();
    }
}
