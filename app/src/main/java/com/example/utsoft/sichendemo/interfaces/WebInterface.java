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
 *
 •WebInterface其实是对Rest API的一个映射关系，在实际开发中，我们可以定义：public interface ClientService，里面包含post ,get 方法。
 •接口中的方法使用了Retrofit的注解，Retrofit这个库给了我们很多注解。
 •requestData()这个方法表示：一个get请求获取给定URL的Data(或者List<Data>，此处是Data)。
 •requestData()传入的参数为我们需要get的url的动态部分。
 •这里的Data为我们自己定义的java bean的类：Data.class用于封装获取的Jason数据。
 注意：此处Retrofit又帮我们省掉了很多工作，只需要我们自己定义业务对应的实体类，而Jason数据的转换和封装则帮我们封装好了,只需我们调用。

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
