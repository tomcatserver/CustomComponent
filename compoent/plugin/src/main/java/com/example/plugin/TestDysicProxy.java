package com.example.plugin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestDysicProxy {
    private Object target;

    public TestDysicProxy(Object target) {
        this.target = target;
    }

    public <T> T proxy(ClassLoader classLoader, Class<?>[] classes) {
        final Object newProxyInstance = Proxy.newProxyInstance(classLoader, classes, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("startActivity")) {
                    int index = 0;
                    for (int i = 0; i < args.length; i++) {
                        if (args[i] instanceof String) {
                            index = i;
                        }
                    }
                    args[index] += "proxy---test";
                }
                Object result = method.invoke(target, args);
                return result;
            }
        });

        return (T) newProxyInstance;
    }
}
