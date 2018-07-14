package com.yjnull.latte.ui.recycler;

import java.util.ArrayList;

/**
 * Created by Yangya on 2018/7/11
 */
public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String jsonData) {
        this.mJsonData = jsonData;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL ---> getJsonData()");
        }
        return mJsonData;
    }

}
