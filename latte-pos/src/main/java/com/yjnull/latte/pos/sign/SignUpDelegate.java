package com.yjnull.latte.pos.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yjnull.latte.pos.R;
import com.yjnull.latte_core.delegates.LatteDelegate;

/**
 * Created by yangya on 2018/7/5.
 */

public class SignUpDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
