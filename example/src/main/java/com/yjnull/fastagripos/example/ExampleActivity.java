package com.yjnull.fastagripos.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.yjnull.latte.pos.launcher.LauncherDelegate;
import com.yjnull.latte.pos.launcher.LauncherScrollDelegate;
import com.yjnull.latte_core.activities.ProxyActivity;
import com.yjnull.latte_core.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
