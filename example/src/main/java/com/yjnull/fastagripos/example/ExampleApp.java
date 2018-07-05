package com.yjnull.fastagripos.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.yjnull.latte.pos.icon.FontECModule;
import com.yjnull.latte_core.app.Latte;
import com.yjnull.latte_core.net.interceptors.DebugInterceptor;

/**
 * Created by Yangya on 2018/7/2
 */
public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontECModule())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
    }
}
