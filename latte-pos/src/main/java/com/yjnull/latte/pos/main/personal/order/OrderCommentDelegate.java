package com.yjnull.latte.pos.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.yjnull.latte.pos.R;
import com.yjnull.latte.pos.R2;
import com.yjnull.latte.ui.widget.AutoPhotoLayout;
import com.yjnull.latte.ui.widget.StarLayout;
import com.yjnull.latte_core.delegates.LatteDelegate;
import com.yjnull.latte_core.util.callback.CallbackManager;
import com.yjnull.latte_core.util.callback.CallbackType;
import com.yjnull.latte_core.util.callback.IGlobalCallback;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yangya on 2018/7/15.
 */

public class OrderCommentDelegate extends LatteDelegate {

    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout = null;
    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout = null;

    @OnClick(R2.id.top_tv_comment_commit)
    void onClickSubmit() {
        Toast.makeText(getContext(), "评分: " + mStarLayout.getStarCount(), Toast.LENGTH_LONG).show();
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
            @Override
            public void executeCallback(@Nullable Uri args) {
                mAutoPhotoLayout.onCropTarget(args);
            }
        });
    }
}
