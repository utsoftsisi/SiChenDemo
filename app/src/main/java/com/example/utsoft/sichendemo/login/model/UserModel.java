package com.example.utsoft.sichendemo.login.model;

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

    @Override
    public void setInfo(String name, String city) {
        this.userBean = new UserBean(name, city);
        userBeanList.add(userBean);
    }

}
