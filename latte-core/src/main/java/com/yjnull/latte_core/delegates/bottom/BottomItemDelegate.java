package com.yjnull.latte_core.delegates.bottom;

import android.widget.Toast;

import com.yjnull.latte_core.R;
import com.yjnull.latte_core.app.Latte;
import com.yjnull.latte_core.delegates.LatteDelegate;

/**
 * Created by Yangya on 2018/7/10
 * 每个底部导航栏对应的页面抽象
 */
public abstract class BottomItemDelegate extends LatteDelegate {
    // 再点一次退出程序时间设置
    private static final long EXIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < EXIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Latte.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
