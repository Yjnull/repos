package com.yjnull.latte.pos.sign;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.yjnull.latte.pos.R;
import com.yjnull.latte.pos.R2;
import com.yjnull.latte_core.delegates.LatteDelegate;
import com.yjnull.latte_core.net.RestClient;
import com.yjnull.latte_core.net.callback.IError;
import com.yjnull.latte_core.net.callback.ISuccess;
import com.yjnull.latte_core.util.log.LatteLogger;
import com.yjnull.latte_core.wechat.LatteWeChat;
import com.yjnull.latte_core.wechat.callbacks.IWeChatSignInCallback;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yangya on 2018/7/7.
 * 登录页
 */

public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_in_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ISignListener) {
            mISignListener = (ISignListener) context;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {
            RestClient.builder()
                    .url("user/login")
                    .params("username", mName.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Toast.makeText(getContext(), code + msg, Toast.LENGTH_LONG).show();
                        }
                    })
                    .loader(getContext())
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat() {
        LatteWeChat
                .getInstance()
                .onSignSuccess(new IWeChatSignInCallback() {
                    @Override
                    public void onSignInSuccess(String userInfo) {
                        LatteLogger.d("userInfo------>", userInfo);
                        Toast.makeText(getContext(), userInfo, Toast.LENGTH_LONG).show();
                    }
                })
                .signIn();
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    private boolean checkForm() {
        final String email = mName.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (TextUtils.isEmpty(email)) {
            mName.setError("用户名不能为空");
            isPass = false;
        } else mName.setError(null);

        if (TextUtils.isEmpty(password) || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else mPassword.setError(null);

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
