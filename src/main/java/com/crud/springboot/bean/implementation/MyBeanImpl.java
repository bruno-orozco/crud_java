package com.crud.springboot.bean.implementation;

import com.crud.springboot.bean.MyBean;

public class MyBeanImpl implements MyBean {

    @Override
    public String hello() {
        return "Hello from my BeanImpl";
    }
}
