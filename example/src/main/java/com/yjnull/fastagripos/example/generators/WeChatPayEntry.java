package com.yjnull.fastagripos.example.generators;

import com.yjnull.latte_annotations.annotations.PayEntryGenerator;
import com.yjnull.latte_core.wechat.templates.WXPayEntryTemplate;

/**
 * Created by Yangya on 2018/7/9
 */

@PayEntryGenerator(
        packageName = "com.yjnull.fastagripos.example",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
