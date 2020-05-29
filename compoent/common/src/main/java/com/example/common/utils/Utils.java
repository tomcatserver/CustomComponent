package com.example.common.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;

public class Utils {
    /**
     * 2      * 判断当前应用是否是debug状态
     * 3
     */
    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }
}
