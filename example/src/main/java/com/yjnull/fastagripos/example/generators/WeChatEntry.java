package com.yjnull.fastagripos.example.generators;

import com.yjnull.latte_annotations.annotations.EntryGenerator;
import com.yjnull.latte_core.wechat.templates.WXEntryTemplate;

/**
 * Created by Yangya on 2018/7/9
 */

@EntryGenerator(
        packageName = "com.yjnull.fastagripos.example",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {
}
