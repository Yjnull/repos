package com.yjnull.latte.pos.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.yjnull.latte.pos.R;
import com.yjnull.latte.pos.R2;
import com.yjnull.latte_core.app.Latte;
import com.yjnull.latte_core.delegates.bottom.BottomItemDelegate;
import com.yjnull.latte_core.net.RestClient;
import com.yjnull.latte_core.net.callback.ISuccess;
import com.yjnull.latte_core.ui.recycler.MultipleFields;
import com.yjnull.latte_core.ui.recycler.MultipleItemEntity;
import com.yjnull.latte_core.ui.refresh.RefreshHandler;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Yangya on 2018/7/10
 */
public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_index_search)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;

    @OnClick(R2.id.icon_index_scan)
    void onClickScanQrCode() {

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler =  RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());

    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("test");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }
}
