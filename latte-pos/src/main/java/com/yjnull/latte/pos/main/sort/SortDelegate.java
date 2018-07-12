package com.yjnull.latte.pos.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yjnull.latte.pos.R;
import com.yjnull.latte.pos.main.sort.content.ContentDelegate;
import com.yjnull.latte.pos.main.sort.list.VerticalListDelegate;
import com.yjnull.latte_core.delegates.bottom.BottomItemDelegate;

/**
 * Created by Yangya on 2018/7/10
 */
public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
