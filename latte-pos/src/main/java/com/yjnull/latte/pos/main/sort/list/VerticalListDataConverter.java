package com.yjnull.latte.pos.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yjnull.latte.ui.recycler.DataConverter;
import com.yjnull.latte.ui.recycler.ItemType;
import com.yjnull.latte.ui.recycler.MultipleFields;
import com.yjnull.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by Yangya on 2018/7/12
 */
public class VerticalListDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON
                .parseObject(getJsonData())
                .getJSONArray("data");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, name)
                    .setField(MultipleFields.TAG, false)  //该tag 用于被选中的状态 true是选中
                    .build();

            ENTITIES.add(entity);

        }
        //设置第一个被选中
        ENTITIES.get(0).setField(MultipleFields.TAG, true);

        return ENTITIES;
    }
}
