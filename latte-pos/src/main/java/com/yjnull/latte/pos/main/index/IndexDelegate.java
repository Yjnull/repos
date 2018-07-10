package com.yjnull.latte.pos.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yjnull.latte.pos.R;
import com.yjnull.latte_core.delegates.bottom.BottomItemDelegate;

/**
 * Created by Yangya on 2018/7/10
 */
public class IndexDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
