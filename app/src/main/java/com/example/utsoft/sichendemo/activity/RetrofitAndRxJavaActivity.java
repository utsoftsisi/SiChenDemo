package com.example.utsoft.sichendemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.entity.Data;
import com.example.utsoft.sichendemo.interfaces.WebInterface;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAndRxJavaActivity extends AppCompatActivity {


    public static void launchActivity(Context context) {
        context.startActivity(new Intent(context, RetrofitAndRxJavaActivity.class));
    }

    @BindView(R.id.tv_show_RetrofitAndRxJavaActivity)
    TextView tvShowRetrofitAndRxJavaActivity;
    private Disposable d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_and_rxjava);
        ButterKnife.bind(this);
        requestData();
    }

    private void requestData() {
        String url = "http://ip.taobao.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        WebInterface api = retrofit.create(WebInterface.class);
        api.requestData6("getIpInfo.php", "63.223.108.42")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Data>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        RetrofitAndRxJavaActivity.this.d = d;
                    }

                    @Override
                    public void onNext(Data data) {
                        Logger.i(data.getData().getIp());
                        tvShowRetrofitAndRxJavaActivity.setText(data.getData().getIp());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!d.isDisposed()) {
            d.dispose();
        }
    }
}
