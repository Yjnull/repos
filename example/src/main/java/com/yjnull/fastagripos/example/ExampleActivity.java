package com.yjnull.fastagripos.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.yjnull.latte.pos.launcher.LauncherDelegate;
import com.yjnull.latte.pos.main.PosBottomDelegate;
import com.yjnull.latte.pos.sign.ISignListener;
import com.yjnull.latte.pos.sign.SignInDelegate;
import com.yjnull.latte.ui.launcher.ILauncherListener;
import com.yjnull.latte.ui.launcher.OnLauncherFinishTag;
import com.yjnull.latte_core.activities.ProxyActivity;
import com.yjnull.latte_core.app.Latte;
import com.yjnull.latte_core.delegates.LatteDelegate;

import qiu.niorgai.StatusBarCompat;

public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        getSupportDelegate().startWithPop(new PosBottomDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
        getSupportDelegate().startWithPop(new ExampleDelegate());
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                //用户已经登录,进主页
                //Toast.makeText(this, "launcher success USER LOGINED!", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new PosBottomDelegate());
                break;
            case NOT_SIGNED:
                //用户未登录,进登录页面
                Toast.makeText(this, "launcher success USER UN_LOGINED!", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
