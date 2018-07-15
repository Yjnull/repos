package com.yjnull.latte.pos.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yjnull.latte.pos.R;
import com.yjnull.latte_core.delegates.LatteDelegate;

/**
 * Created by yangya on 2018/7/15.
 */

public class NameDelegate extends LatteDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
