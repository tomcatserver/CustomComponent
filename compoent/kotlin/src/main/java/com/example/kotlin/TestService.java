package com.example.kotlin;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.service.IKotlinService;
@Route(path = "/kotlinService/Test",name = "kotlin测试服务")
public class TestService implements IKotlinService {
    @Override
    public String outContent(String content) {
        Log.e("tag", "outContent:------IKotlinService----content=" + content);
        return "this is a kotlinService content =" + content;
    }

    @Override
    public void init(Context context) {
        Log.e("tag", "init: ------IKotlinService--context=" + context);
    }
}
