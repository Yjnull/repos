package com.yjnull.latte_core.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.yjnull.latte_core.delegates.LatteDelegate;
import com.yjnull.latte_core.delegates.web.WebDelegate;

/**
 * Created by Yangya on 2018/7/13
 */
public abstract class Event implements IEvent {

    private Context mContent = null;
    private String mAction = null;
    private WebDelegate mDelegate = null;
    private String mUrl = null;
    private WebView mWebView = null;

    public WebView getWebView(){
        return mDelegate.getWebView();
    }

    public Context getContext() {
        return mContent;
    }

    public void setContext(Context mContent) {
        this.mContent = mContent;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public LatteDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
