package com.example.utsoft.sichendemo.interfaces;

import com.example.utsoft.sichendemo.entity.Data;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by chensi on 2017/2/24.
 */

public interface WebInterface {
    //http://ip.taobao.com/service/getIpInfo.php?ip=63.223.108.42
    //1.
    @GET("service/getIpInfo.php?ip=63.223.108.42")
    Call<Data> requestData();
    //2.完全和基地址无关 ，会按照传过来的地址去发送网络请求
    @GET
    Call<Data> requestData2(@Url String url);
    //3.?可加上
    @GET("service/getIpInfo.php")
    Call<Data> requestData3(@Query("ip") String ip);
    //4.动态替换路径
    @GET("service/{path}?ip=63.223.108.42")
    Call<Data> requestData4(@Path("path") String path);
    //5.动态替换路径和参数 ,?可加上
    @GET("service/{path}")
    Call<Data> requestData5(@Path("path") String path, @Query("ip") String ip);

    //Retrofit和RxJava结合起来
    @GET("service/{path}")
    Observable<Data> requestData6(@Path("path") String path, @Query("ip") String ip);

}
