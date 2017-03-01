package com.example.utsoft.sichendemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.entity.Data;
import com.example.utsoft.sichendemo.interfaces.WebInterface;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_okhttp_mainActivity)
    TextView tvOkhttpMainActivity;
    @BindView(R.id.btn_okhttp_mainActivity)
    Button btnOkhttpMainActivity;
    @BindView(R.id.tv_retrofit_mainActivity)
    TextView tvRetrofitMainActivity;
    @BindView(R.id.btn_retrofit_mainActivity)
    Button btnRetrofitMainActivity;
    @BindView(R.id.btn_flyco_mainActivity)
    Button btnFlycoMainActivity;
    @BindView(R.id.btn_Refresh_mainActivity)
    Button btnRefreshMainActivity;
    @BindView(R.id.btn_rxjava_mainActivity)
    Button btnRxjavaMainActivity;
    @BindView(R.id.iv_show_mainActivity)
    ImageView ivShowMainActivity;
    @BindView(R.id.btn_evenbus_mainActivity)
    Button btnEvenbusMainActivity;

    private String URL = "http://ip.taobao.com/service/getIpInfo.php?ip=63.223.108.42";
    private String URL1 = "http://ip.taobao.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadImageData();
        setListener();
    }

    private void loadImageData() {
        Glide.with(this)
                .load("http://i.imgur.com/DvpvklR.png")
                .placeholder(R.mipmap.ic_launcher)
                .into(ivShowMainActivity);
    }

    private void setListener() {
        btnOkhttpMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(URL)
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        Logger.i(result);
                        Gson gson = new Gson();
                        final Data data = gson.fromJson(result, Data.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvOkhttpMainActivity.setText(data.getData().getCountry_id());
                            }

                        });
                    }
                });
            }
        });

        btnRetrofitMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL1)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                WebInterface api = retrofit.create(WebInterface.class);
                retrofit2.Call<Data> call = api.requestData5("getIpInfo.php", "63.223.108.42");
                call.enqueue(new retrofit2.Callback<Data>() {
                    @Override
                    public void onResponse(retrofit2.Call<Data> call, final retrofit2.Response<Data> response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvRetrofitMainActivity.setText(response.body().getData().getCountry());
                            }

                        });
                    }

                    @Override
                    public void onFailure(retrofit2.Call<Data> call, Throwable t) {

                    }
                });

            }
        });

        btnFlycoMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FlycoActivity.class);
                startActivity(intent);

            }
        });

        btnRefreshMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RefreshActivity.class);
                startActivity(intent);
            }
        });

        btnRxjavaMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestRxJavaActivity.class);
                startActivity(intent);
            }
        });

        btnEvenbusMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestEvenBusFirstActivity.launchActivity(MainActivity.this);
            }
        });
    }


}
