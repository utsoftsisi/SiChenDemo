package com.example.utsoft.sichendemo.login.model;

import java.util.List;

/**
 * Created by 陈思 on 2017/3/21 16:08.
 * Function:保存用户输入的信息并进行读取。
 * Desc:Model层主要是对数据的存取，处理。
 */

public interface IUser {
    //取数据
    List<UserBean> getUserBeanList();
    //存数据
    void setInfo(String name, String city);
}
