package com.yjnull.latte.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by Yangya on 2018/7/11
 */
public class MultipleViewHolder extends BaseViewHolder {

    private MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
