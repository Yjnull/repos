package com.yjnull.latte.pos.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yjnull.latte.pos.R;
import com.yjnull.latte.pos.R2;
import com.yjnull.latte.pos.main.sort.SortDelegate;
import com.yjnull.latte.ui.recycler.MultipleItemEntity;
import com.yjnull.latte_core.delegates.LatteDelegate;
import com.yjnull.latte_core.net.RestClient;
import com.yjnull.latte_core.net.callback.ISuccess;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Yangya on 2018/7/12
 */
public class VerticalListDelegate extends LatteDelegate {

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("project/tree/json")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final List<MultipleItemEntity> data =
                                new VerticalListDataConverter()
                                        .setJsonData(response)
                                        .convert();

                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
