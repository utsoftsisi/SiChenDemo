package com.example.utsoft.sichendemo.login.model;

import com.example.utsoft.sichendemo.interfaces.OnListener;

import java.util.List;

/**
 * Created by 陈思 on 2017/3/21 16:08.
 * Function:保存用户输入的信息并进行读取。
 * Desc:Model层主要是对数据的存取，处理（业务逻辑：主要是数据读写，或者与后台通信（其实也是读写数据））。
 */

public interface IUser {
    //取数据
    List<UserBean> getUserBeanList();
    //存数据
    //增加一个回调接口onListener通知存数据成功（回调给媒婆）
    void setInfo(String name, String city, OnListener onListener);
}
