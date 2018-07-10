package com.yjnull.latte.pos.main;

import android.graphics.Color;

import com.yjnull.latte.pos.main.index.IndexDelegate;
import com.yjnull.latte.pos.main.sort.SortDelegate;
import com.yjnull.latte_core.delegates.bottom.BaseBottomDelegate;
import com.yjnull.latte_core.delegates.bottom.BottomItemDelegate;
import com.yjnull.latte_core.delegates.bottom.BottomTabBean;
import com.yjnull.latte_core.delegates.bottom.ItemBuilder;

import java.util.LinkedHashMap;

/**
 * Created by Yangya on 2018/7/10
 */
public class PosBottomDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
