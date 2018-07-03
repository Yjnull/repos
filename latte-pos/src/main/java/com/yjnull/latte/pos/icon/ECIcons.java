package com.yjnull.latte.pos.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Yangya on 2018/7/3
 */
public enum ECIcons implements Icon {
    icon_scan('\ue636'),
    icon_alipay('\ue610');

    private char charater;

    ECIcons(char charater) {
        this.charater = charater;
    }

    @Override
    public String key() {
        return this.name().replace('_', '-');
    }

    @Override
    public char character() {
        return this.charater;
    }
}
