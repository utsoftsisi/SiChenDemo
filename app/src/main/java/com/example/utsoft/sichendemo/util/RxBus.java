package com.example.utsoft.sichendemo.util;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by 陈思 on 2017/3/17 16:48.
 * Function:用RxJava实现EvenBus的功能
 * Desc:1、首先创建一个可同时充当Observer和Observable的Subject，PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者

        2、在需要接收事件的地方，订阅该Subject（此时Subject是作为Observable），在这之后，一旦Subject接收到事件，立即发射给该订阅者；

        3、在我们需要发送事件的地方，将事件post至Subject，此时Subject作为Observer接收到事件（onNext），然后会发射给所有订阅该Subject的订阅者。

 */

public class RxBus {

    private static volatile RxBus defaultInstance;
    private final Subject bus;

    public RxBus() {
        //Subject 不是线程安全的，所以把它转化为SerializedSubject
        bus = PublishSubject.create().toSerialized();
    }

    // 单例RxBus
    public static RxBus getDefaultInstance() {
        RxBus rxBus = defaultInstance;
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                rxBus = defaultInstance;
                if (defaultInstance == null) {
                    rxBus = new RxBus();
                    defaultInstance = rxBus;
                }
            }
        }
        return rxBus;
    }

    // 发出一个新的事件
    public void post(Object o) {
        bus.onNext(o);
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }


}
