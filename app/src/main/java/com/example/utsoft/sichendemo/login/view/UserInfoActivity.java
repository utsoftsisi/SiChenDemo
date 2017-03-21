package com.example.utsoft.sichendemo.login.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.login.model.UserBean;
import com.example.utsoft.sichendemo.login.presenter.IUserInfoPresenter;
import com.example.utsoft.sichendemo.login.presenter.UserInfoPresenterImp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoActivity extends AppCompatActivity implements IUserInfoView {

    public static void launchActivity(Context context) {
        context.startActivity(new Intent(context, UserInfoActivity.class));
    }

    @BindView(R.id.et_name_userInfoActivity)
    EditText etNameUserInfoActivity;
    @BindView(R.id.et_city_userInfoActivity)
    EditText etCityUserInfoActivity;
    @BindView(R.id.btn_save_userInfoActivity)
    Button btnSaveUserInfoActivity;
    @BindView(R.id.btn_get_userInfoActivity)
    Button btnGetUserInfoActivity;
    @BindView(R.id.et_get_name_userInfoActivity)
    EditText etGetNameUserInfoActivity;
    @BindView(R.id.et_get_city_userInfoActivity)
    EditText etGetCityUserInfoActivity;

    private IUserInfoPresenter iUserInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        iUserInfoPresenter = new UserInfoPresenterImp(this);
        setListener();
    }

    private void setListener() {
        btnSaveUserInfoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etNameUserInfoActivity.getText().toString().trim();
                String city = etCityUserInfoActivity.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(city)) {
                    iUserInfoPresenter.save(name, city);
                    etNameUserInfoActivity.setText("");
                    etCityUserInfoActivity.setText("");
                }
            }
        });

        btnGetUserInfoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<UserBean> userBeanList = iUserInfoPresenter.get();
                StringBuffer allName = new StringBuffer();
                StringBuffer allCity = new StringBuffer();
                for (UserBean userBean : userBeanList) {
                    allName.append(userBean.getName().toString());
                    allCity.append(userBean.getCity().toString());
                }
                etGetNameUserInfoActivity.setText(allName);
                etGetCityUserInfoActivity.setText(allCity);

            }
        });
    }

    @Override
    public void saveResult() {
        Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show();
    }
}
