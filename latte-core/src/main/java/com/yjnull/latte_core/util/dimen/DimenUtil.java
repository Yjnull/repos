package com.yjnull.latte_core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.yjnull.latte_core.app.Latte;

/**
 * Created by Yangya on 2018/7/4
 */
public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
