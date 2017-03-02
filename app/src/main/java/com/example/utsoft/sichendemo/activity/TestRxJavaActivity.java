package com.example.utsoft.sichendemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.utsoft.sichendemo.R;
import com.example.utsoft.sichendemo.entity.Course;
import com.example.utsoft.sichendemo.entity.Student;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

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

        testStudent();

    }

    private void testStudent() {
        List<Course> courseList = new ArrayList<>();
        for (int i=0;i<5;i++) {
            courseList.add(new Course("math"+i));
        }
        List<Student> studentList = new ArrayList<>();
        for (int i=0;i<5;i++) {
            studentList.add(new Student("sisi"+i, courseList));
        }

        Observable.from(studentList)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourseList());
                    }
                })
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        Logger.i(course.getCourseName());
                    }
                });
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
