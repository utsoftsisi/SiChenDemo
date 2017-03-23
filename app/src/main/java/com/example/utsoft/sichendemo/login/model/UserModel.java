package com.example.utsoft.sichendemo.login.model;

import com.example.utsoft.sichendemo.interfaces.OnListener;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by 陈思 on 2017/3/23 9:56.
 * Function:
 * Desc:
 * View与Model并不直接交互，而是使用Presenter作为View与Model之间的桥梁。
 * 其中Presenter中同时持有Viwe层以及Model层的Interface的引用，而View层持有Presenter层Interface的引用。
 * 当View层某个界面需要展示某些数据的时候，首先会调用Presenter层的某个接口，
 * 然后Presenter层会调用Model层请求数据，当Model层数据加载成功之后会调用Presenter层的回调方法通知Presenter层数据加载完毕，
 * 最后Presenter层再调用View层的接口将加载后的数据展示给用户，这就是MVP模式的整个核心过程。
 * 这样分层的好处就是大大减少了Model与View层之间的耦合度，
 * 一方面可以使得View层和Model层单独开发与测试，互不依赖，另一方面Model层可以封装复用，可以极大的减少代码量。
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
