package com.example.common.service;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface IKotlinService extends IProvider {
    String outContent(String content);
}
