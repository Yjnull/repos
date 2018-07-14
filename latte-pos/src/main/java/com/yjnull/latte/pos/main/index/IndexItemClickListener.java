package com.yjnull.latte.pos.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.yjnull.latte.pos.detail.GoodsDetailDelegate;
import com.yjnull.latte_core.delegates.LatteDelegate;

/**
 * Created by Yangya on 2018/7/11
 */
public class IndexItemClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;

    private IndexItemClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(LatteDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final GoodsDetailDelegate detailDelegate = GoodsDetailDelegate.create();
        DELEGATE.getSupportDelegate().start(detailDelegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) { }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) { }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) { }
}
