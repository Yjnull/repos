package com.yjnull.latte.pos.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.yjnull.latte.pos.R;
import com.yjnull.latte_core.app.Latte;
import com.yjnull.latte_core.delegates.LatteDelegate;
import com.yjnull.latte_core.ui.launcher.LauncherHolderCreator;
import com.yjnull.latte_core.ui.launcher.ScrollLauncherTag;
import com.yjnull.latte_core.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * Created by Yangya on 2018/7/5
 * 滚动轮播 启动页
 */
public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener{

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS_IMG = new ArrayList<>();

    private void initBanner() {
        INTEGERS_IMG.add(R.mipmap.launcher_01);
        INTEGERS_IMG.add(R.mipmap.launcher_02);
        INTEGERS_IMG.add(R.mipmap.launcher_03);
        INTEGERS_IMG.add(R.mipmap.launcher_04);
        INTEGERS_IMG.add(R.mipmap.launcher_05);

        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS_IMG)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);

    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS_IMG.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //TODO check 用户是否已经登录,暂未处理
        }
    }
}
