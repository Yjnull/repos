package com.yjnull.latte_core.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by Yangya on 2018/7/2
 */

public final class Latte {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static WeakHashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

}
