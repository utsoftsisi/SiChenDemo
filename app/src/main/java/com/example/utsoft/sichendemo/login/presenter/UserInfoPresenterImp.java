package com.example.utsoft.sichendemo.login.presenter;

import com.example.utsoft.sichendemo.interfaces.OnListener;
import com.example.utsoft.sichendemo.login.model.IUser;
import com.example.utsoft.sichendemo.login.model.UserBean;
import com.example.utsoft.sichendemo.login.model.UserModel;
import com.example.utsoft.sichendemo.login.view.IUserInfoView;

import java.util.List;

/**
 * Created by 陈思 on 2017/3/22 11:08.
 * Function:
 * Desc:Model层与View层之间的交互由Presenter完成，
 * Presenter与View之间的交互也是通过接口完成的，如 iUserInfoView.saveResult();。
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
    public List<UserBean> get() {
        return iUser.getUserBeanList();
    }
}
