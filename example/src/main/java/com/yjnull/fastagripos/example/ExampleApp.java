package com.yjnull.fastagripos.example;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.yjnull.latte.pos.database.DatabaseManager;
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
                .withLoaderDelayed(100)
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .configure();
        initStetho();
        DatabaseManager.getInstance().init(this);
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
