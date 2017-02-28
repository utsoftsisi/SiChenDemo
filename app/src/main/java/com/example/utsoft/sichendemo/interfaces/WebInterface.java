package com.example.utsoft.sichendemo.interfaces;

import com.example.utsoft.sichendemo.entity.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chensi on 2017/2/24.
 */

public interface WebInterface {
    @GET("/service/getIpInfo.php")
    Call<Data> requestData(@Query("ip") String ip);

}
