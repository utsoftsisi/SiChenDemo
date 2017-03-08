package com.example.utsoft.sichendemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.dao.GreenDaoManager;
import com.example.utsoft.sichendemo.entity.User;
import com.example.utsoft.sichendemo.gen.UserDao;

import java.util.ArrayList;
import java.util.List;

public class TestGreenDaoActivity extends AppCompatActivity {

    private UserDao userDao = GreenDaoManager.getInstance().getSession().getUserDao();

    public static void launchActivity(Context context) {
        context.startActivity(new Intent(context, TestGreenDaoActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_green_dao);
        loadData();
        query();
        update();
        delete();
    }

    private void delete() {
        User findUser = userDao.queryBuilder().where(UserDao.Properties.Name.eq("xiaoming")).build().unique();
        if (findUser != null) {
            userDao.deleteByKey(findUser.getId());
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "用户不存在，删除失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void update() {
        User findUser = userDao.queryBuilder().where(UserDao.Properties.Id.eq(0l)).build().unique();
        if (findUser != null) {
            findUser.setName("xiaoming");
            userDao.update(findUser);
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "用户不存在", Toast.LENGTH_SHORT).show();
        }
    }

    private void query() {
        List<User> userList = userDao.queryBuilder()
                .limit(3)
                .build().list();
        Toast.makeText(this, userList.get(2).getAge() + "", Toast.LENGTH_LONG).show();
    }

    private void loadData() {
        List<User> list = new ArrayList<>();
        list.add(new User(0L, "sisi", 24));
        list.add(new User(1L, "sisi", 25));
        list.add(new User(2L, "sisi", 26));
        list.add(new User(3L, "sisi", 27));
        insertOrReplace(list);
    }

    public void insertOrReplace(List<User> list) {
        for (User user : list) {
            userDao.insertOrReplace(user);
        }

    }


}
