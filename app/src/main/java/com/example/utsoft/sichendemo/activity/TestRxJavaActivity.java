package com.example.utsoft.sichendemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.utsoft.sichendemo.R;
import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class TestRxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rx_java);

        detailObservable();

        lazyObservable();

    }

    private void lazyObservable() {
        Observable.just("lazy")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        //Action1是一个单纯的人畜无害的接口，和Observer没有啥关系，只不过它可以当做观察者来使，专门处理onNext事件
                        Logger.i(s);
                    }
                });
    }

    private void detailObservable() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello Rx");
                subscriber.onCompleted();
            }
        }).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s+"Java";
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                Toast.makeText(TestRxJavaActivity.this, s, Toast.LENGTH_LONG).show();
            }
        };
        observable.subscribe(subscriber);
    }
}
