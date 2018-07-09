package com.yjnull.fastagripos.example.generators;

import com.yjnull.latte_annotations.annotations.AppRegisterGenerator;
import com.yjnull.latte_core.wechat.templates.AppRegisterTemplate;

/**
 * Created by Yangya on 2018/7/9
 */

@AppRegisterGenerator(
        packageName = "com.yjnull.fastagripos.example",
        registerTemplete = AppRegisterTemplate.class
)
public interface AppRegister {
}
