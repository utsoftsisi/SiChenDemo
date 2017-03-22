package com.example.utsoft.sichendemo.login.model;

import com.example.utsoft.sichendemo.interfaces.OnListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chensi on 2017/3/20.
 */

public class UserModel implements IUser {

    private UserBean userBean;
    private List<UserBean> userBeanList = new ArrayList<>();

    @Override
    public List<UserBean> getUserBeanList() {
        return userBeanList;
    }

    //增加一个回调接口onListener通知存数据成功
    @Override
    public void setInfo(String name, String city, OnListener onListener) {
        this.userBean = new UserBean(name, city);
        userBeanList.add(userBean);
        onListener.onSuccess();
    }

}
