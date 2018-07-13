package com.yjnull.latte_core.delegates.web.client;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yjnull.latte_core.delegates.web.IPageLoadListener;
import com.yjnull.latte_core.delegates.web.WebDelegate;
import com.yjnull.latte_core.delegates.web.route.Router;
import com.yjnull.latte_core.ui.loader.LatteLoader;
import com.yjnull.latte_core.util.log.LatteLogger;

/**
 * Created by Yangya on 2018/7/13
 */
public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        //false 由webview处理事件, true 由我们处理事件
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        LatteLoader.stopLoading();
    }
}
