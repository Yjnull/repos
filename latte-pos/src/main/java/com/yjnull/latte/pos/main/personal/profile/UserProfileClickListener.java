package com.yjnull.latte.pos.main.personal.profile;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.yjnull.latte.pos.R;
import com.yjnull.latte.pos.main.personal.list.ListBean;
import com.yjnull.latte.ui.date.DateDialogUtil;
import com.yjnull.latte_core.delegates.LatteDelegate;
import com.yjnull.latte_core.net.RestClient;
import com.yjnull.latte_core.net.callback.ISuccess;
import com.yjnull.latte_core.util.callback.CallbackManager;
import com.yjnull.latte_core.util.callback.CallbackType;
import com.yjnull.latte_core.util.callback.IGlobalCallback;
import com.yjnull.latte_core.util.log.LatteLogger;

/**
 * Created by yangya on 2018/7/15.
 */

public class UserProfileClickListener extends SimpleClickListener {

    private final UserProfileDelegate DELEGATE;

    private String[] mGenders = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(UserProfileDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {
        final ListBean bean = (ListBean) adapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
                //开始照相或选择图片
                CallbackManager.getInstance()
                        .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                            @Override
                            public void executeCallback(@Nullable Uri args) {
                                LatteLogger.d("ONC_CROP", args);
                                final ImageView avatar = view.findViewById(R.id.img_arrow_avatar);
                                Glide.with(DELEGATE)
                                        .load(args)
                                        .into(avatar);

                                assert args != null;
                                RestClient.builder()
                                        .url("")
                                        .loader(DELEGATE.getContext())
                                        .file(args.getPath())
                                        .success(new ISuccess() {
                                            @Override
                                            public void onSuccess(String response) {
                                                LatteLogger.d(response);
                                                //通知服务器更新信息
                                                /*final String path = JSON.parseObject(response).getJSONObject("result")
                                                        .getString("path");
                                                RestClient.builder()
                                                        .url("user_profile.php")
                                                        .params("avatar", path)
                                                        .loader(DELEGATE.getContext())
                                                        .success(new ISuccess() {
                                                            @Override
                                                            public void onSuccess(String response) {
                                                                //获取更新后的用户信息，然后更新本地数据库
                                                                //没有本地数据的APP，每次打开APP都请求API，获取信息
                                                            }
                                                        })
                                                        .build()
                                                        .post();*/
                                            }
                                        })
                                        .build()
                                        .upload();
                            }
                        });
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                final LatteDelegate delegate = bean.getDelegate();
                if (delegate != null)
                    DELEGATE.getSupportDelegate().start(delegate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
            default:
                break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        if (DELEGATE.getContext() != null) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
            builder.setSingleChoiceItems(mGenders, 0, listener);
            builder.show();
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
