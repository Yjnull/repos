package com.yjnull.latte_core.delegates.web.event;

import com.yjnull.latte_core.util.log.LatteLogger;

/**
 * Created by Yangya on 2018/7/13
 */
public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
