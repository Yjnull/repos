package com.yjnull.fastagripos.example;

import com.yjnull.latte_core.activities.ProxyActivity;
import com.yjnull.latte_core.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
