package com.yjnull.latte_core.ui.camera;

import android.net.Uri;

import com.yjnull.latte_core.delegates.PermissionCheckerDelegate;
import com.yjnull.latte_core.util.file.FileUtil;

/**
 * Created by yangya on 2018/7/15.
 * 相机调用类
 */

public class LatteCamera {

    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }

}
