package com.yjnull.latte_core.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by Yangya on 2018/7/2
 */

public final class Latte {

    public static Configurator init(Context context) {
        getConfigurator()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }
}
