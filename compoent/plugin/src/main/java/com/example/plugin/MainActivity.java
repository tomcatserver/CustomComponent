package com.example.plugin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class MainActivity extends AppCompatActivity {
    private String path = "file:/Users/bj-m-206333a/Desktop/CustomComponent/compoent/java/build/outputs/apk/debug/java-debug.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        path = getApplicationContext().getCacheDir().getAbsolutePath();
        path = getExternalCacheDir() + "/java-debug.apk";
        findViewById(R.id.tv_merge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
                loadCompont();
            }
        });
        findViewById(R.id.tv_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.example.compoent", "com.example.compoent.MainActivity");
                startActivity(intent);


//                TestDysicProxy testDysicProxy = new TestDysicProxy(new TestProxy());
//                ClassLoader loader = IProxyInterface.class.getClassLoader();
//                IProxyInterface proxy = testDysicProxy.proxy(loader, new Class[]{IProxyInterface.class});
//                Log.e("tag", "onClick: -----proxy===" + proxy);
//                proxy.startActivity("上课斯科拉里");
//                proxy.startActivity("sskksks");
            }
        });
//     loadDex();

//        loadApk();
    }

    private void loadCompont() {
        try {
            final Object mInstance = getInstances();
            // 获取IActivityManager的Class
            final Class<?> iActivityManagerClass = Class.forName("android.app.IActivityManager");
            // 代理IActivityManager
            Object proxyObj = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{iActivityManagerClass}, new InvocationHandler() {
                // 每执行一个方法，都会调用到这里

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                    if (args == null || args.length <= 0) {
//                        Log.e("tag", "args==null || args.length<=0");
//                        return null;
//                    }
//                    Log.e("tag", "old args === " + args.length);
                    // 如果是startActivity方法的话
                    if (method.getName().equals("startActivity")) {
                        // startActivity方法有多个参数
                        // 这里是索引，记录Intent参数的索引
                        int index = 0;
                        // 遍历所有的参数，获取Intent参数的索引
                        for (int i = 0; i < args.length; i++) {
                            if (args[i] instanceof Intent) {
                                index = i;
                                break;
                            }
                        }
                        // 重新创建一个Intent
                        Intent proxyIntent = new Intent();
                        proxyIntent.setClassName("com.example.plugin", "com.example.plugin.ProxyActivity");
                        // 并将原来的intent记录下来
                        proxyIntent.putExtra("oldIntent", (Intent) args[index]);
                        // 给Intent重新赋值，让它变成我们的代理Activity，这样验证就通过了
                        args[index] = proxyIntent;

                    }
//                    Log.e("tag", "change args === " + args.length);
                    // 在这里还需要继续执行这个方法

                    return method.invoke(mInstance, args);
                }
            });
            Log.e("tag", "loadCompont: -----newproxyObj=" + proxyObj + ",singleTon对象=" + singleTon + "，OldInstance=" + mInstance);

            try {
                mInstanceField.set(singleTon, proxyObj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    Field mInstanceField;
    Object singleTon;

    private Object getInstances() {
        try {
            // 获取ActivityTaskManager的Class
            Class<?> activityManagerClass = Class.forName("android.app.ActivityTaskManager");
            // 根据Class获取IActivityTaskManagerSingleton私有字段

            Field iActivityManagerSingletonField = activityManagerClass.getDeclaredField("IActivityTaskManagerSingleton");
            iActivityManagerSingletonField.setAccessible(true);
            // 根据iActivityManagerSingletonField字段获取Singleton对象
            singleTon = iActivityManagerSingletonField.get(null);

            Class<?> singleTonClass = Class.forName("android.util.Singleton");
            mInstanceField = singleTonClass.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            // 获取到真正的IActivityManger的实例对象
            Object mInstance = mInstanceField.get(singleTon);
            return mInstance;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void loadDex() {
//        Log.e("tag", "onCreate: --getCacheDir=" +getApplicationContext().getCacheDir().getAbsolutePath()+",sdcrad="+getExternalCacheDir()+",getExternalStorageDirectory="+paths);
        DexClassLoader classLoader = new DexClassLoader(this.path, null, null, getClassLoader());

        try {
            Class<?> clazz = classLoader.loadClass("com.example.java.JavaService");
            Method method = clazz.getMethod("outContent", String.class);
            method.invoke(clazz.newInstance(), "测试plugin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadApk() {

        try {
            Class<?> clazz = Class.forName("com.example.java.JavaService");
            Method method = clazz.getMethod("outContent", String.class);
            method.invoke(clazz.newInstance(), "测试plugin----------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load() {
        try {
            Class<?> systemClassLoader = Class.forName("dalvik.system.BaseDexClassLoader");
            Field pathListField = systemClassLoader.getDeclaredField("pathList");
            pathListField.setAccessible(true);

            Class<?> dexPathListClass = Class.forName("dalvik.system.DexPathList");
            Field dexElementsField = dexPathListClass.getDeclaredField("dexElements");
            dexElementsField.setAccessible(true);


            ClassLoader hostClassLoader = getClassLoader();
            Object hostPathList = pathListField.get(hostClassLoader);
            Object[] hostElements = (Object[]) dexElementsField.get(hostPathList);


            PathClassLoader pluginClassLoader = new PathClassLoader(path, getClassLoader());
            Object pluginPathList = pathListField.get(pluginClassLoader);
            Object[] pluginElements = (Object[]) dexElementsField.get(pluginPathList);
            Object[] newElements = (Object[]) Array.newInstance(Objects.requireNonNull(pluginElements.getClass().getComponentType()), hostElements.length + pluginElements.length);
            System.arraycopy(hostElements, 0, newElements, 0, hostElements.length);
            System.arraycopy(pluginElements, 0, newElements, hostElements.length, pluginElements.length);
            dexElementsField.set(hostPathList, newElements);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 判断手机SDCard是否可用
     *
     * @return true可用;false 不可用
     */
    private static boolean externalMemoryAvailable() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }
}
