package com.example.utsoft.sichendemo.login.presenter;

import com.example.utsoft.sichendemo.interfaces.OnListener;
import com.example.utsoft.sichendemo.login.model.IUser;
import com.example.utsoft.sichendemo.login.model.UserBean;
import com.example.utsoft.sichendemo.login.model.UserModel;
import com.example.utsoft.sichendemo.login.view.IUserInfoView;

import java.util.List;

/**
 * Created by 陈思 on 2017/3/22 11:08.
 * Function:Model层与View层之间的交互由Presenter完成。
 * Desc:Presenter与View之间的交互也是通过接口完成的，
 * view可以把所有的ui逻辑交给presenter处理，view只负责显示。
 * 如：
 * iUserInfoView.saveResult(),
 * iUserInfoView.setName(allName.toString())，
 * iUserInfoView.setCity(allCity.toString())。
 */

public class UserInfoPresenterImp implements IUserInfoPresenter {
    private IUserInfoView iUserInfoView;
    private IUser iUser = new UserModel();

    //iUserInfoView必须要传，否则会报Null
    public UserInfoPresenterImp(IUserInfoView iUserInfoView) {
        this.iUserInfoView = iUserInfoView;
    }


    @Override
    public void save(String name, String city) {
        iUser.setInfo(name, city, new OnListener() {
            @Override
            public void onSuccess() {
                iUserInfoView.saveResult();
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void get() {
        List<UserBean> userBeanList = iUser.getUserBeanList();
        StringBuffer allName = new StringBuffer();
        StringBuffer allCity = new StringBuffer();
        for (UserBean userBean : userBeanList) {
            allName.append(userBean.getName().toString());
            allCity.append(userBean.getCity().toString());
        }
        //通过调用IUserInfoView的方法来更新ui显示
        iUserInfoView.setName(allName.toString());
        iUserInfoView.setCity(allCity.toString());
    }
}
