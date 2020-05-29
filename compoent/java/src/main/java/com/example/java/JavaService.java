package com.example.java;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.service.IJavaTestService;

@Route(path = "/com/example/java/JavaService", name = "java测试服务")
public class JavaService implements IJavaTestService {
    @Override
    public String outContent(String content) {
        Log.e("tag", "outContent: ---------/com/example/java/JavaService" );
        return "this is a test JavaService + " + content;
    }

    @Override
    public void init(Context context) {
        Log.e("tag", "init: ---------JavaService=" + context);
    }
}
