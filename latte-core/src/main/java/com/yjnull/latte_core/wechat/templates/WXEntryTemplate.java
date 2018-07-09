package com.yjnull.latte_core.wechat.templates;

import com.yjnull.latte_core.wechat.BaseWXEntryActivity;
import com.yjnull.latte_core.wechat.LatteWeChat;

/**
 * Created by Yangya on 2018/7/9
 */
public class WXEntryTemplate extends BaseWXEntryActivity{

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getmSignInCallback().onSignInSuccess(userInfo);
    }
}
