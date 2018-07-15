package com.yjnull.latte_core.ui.camera;

import android.net.Uri;

/**
 * Created by yangya on 2018/7/15.
 * 存储一些中间值
 */

public final class CameraImageBean {

    private Uri mPath = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }

}
