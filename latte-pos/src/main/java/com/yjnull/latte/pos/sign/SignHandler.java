package com.yjnull.latte.pos.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yjnull.latte.pos.database.DatabaseManager;
import com.yjnull.latte.pos.database.UserProfile;
import com.yjnull.latte_core.app.AccountManager;

/**
 * Created by yangya on 2018/7/8.
 */

public class SignHandler {

    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        if (profileJson == null) return;
        final long userId = profileJson.getLong("id");
        final String name = profileJson.getString("username");
        final String avatar = profileJson.getString("icon");
        final String gender = profileJson.getString("type");
        final String address = profileJson.getString("email");

        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getUserProfileDao().insert(userProfile);

        //登录成功
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }

    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        if (profileJson == null) return;
        final long userId = profileJson.getLong("id");
        final String name = profileJson.getString("username");
        final String avatar = profileJson.getString("icon");
        final String gender = profileJson.getString("type");
        final String address = profileJson.getString("email");

        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getUserProfileDao().insert(userProfile);

        //已经注册并登录成功
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }

}
