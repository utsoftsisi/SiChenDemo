package com.example.utsoft.sichendemo.login.view;

/**
 * Created by 陈思 on 2017/3/20 17:09.
 * Function:负责显示数据，提供友好界面和用户交互（的方法）
 * Desc:列出需要操作当前view的方法（列出当前view需要的方法）
 */

public interface IUserInfoView {

    void saveResult();

    String getName();

    String getCity();

    void setName(String name);

    void setCity(String city);
}
