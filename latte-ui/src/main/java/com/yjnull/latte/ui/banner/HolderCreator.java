package com.yjnull.latte.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by Yangya on 2018/7/11
 */
public class HolderCreator implements CBViewHolderCreator<ImageHolder> {
    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
