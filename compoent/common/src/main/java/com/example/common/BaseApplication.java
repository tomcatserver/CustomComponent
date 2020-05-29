package com.example.common;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.utils.Utils;

public class BaseApplication extends Application {
    public Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        //初始化ARouter框架
        if (Utils.isApkInDebug(getApplicationContext())) {
            ARouter.openLog();
            // 开启调试模式（如果InstantRun模式下运行，必须开启调试模式！）
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
