package com.example.utsoft.sichendemo.login.presenter;

import com.example.utsoft.sichendemo.login.model.UserBean;

import java.util.List;

/**
 * Created by 陈思 on 2017/3/21 16:12.
 * Function:相当于媒婆，view层和model层的桥梁。
 * Desc:程序各种逻辑的分发，分发给Model层做具体的操作。
 */

public interface IUserInfoPresenter {
    void save(String name, String city);
    List<UserBean> get();
}
