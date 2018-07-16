package com.yjnull.latte_core.util.callback;

import android.support.annotation.Nullable;

/**
 * Created by Yangya on 2018/7/16
 */
public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
