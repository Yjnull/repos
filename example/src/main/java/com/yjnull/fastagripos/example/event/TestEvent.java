package com.yjnull.fastagripos.example.event;

import android.widget.Toast;

import com.yjnull.latte_core.delegates.web.event.Event;

/**
 * Created by Yangya on 2018/7/13
 */
public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), params, Toast.LENGTH_LONG).show();
        return null;
    }
}
