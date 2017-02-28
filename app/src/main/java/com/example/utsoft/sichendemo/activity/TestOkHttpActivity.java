package com.example.utsoft.sichendemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.utsoft.sichendemo.R;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestOkHttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ok_http);

        getResponse();

    }

    public void getResponse() {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Logger.i(headers.name(i) + ": " + headers.value(i));
                }
                Logger.i(response.body().string());
            }
        });
    }
}
