package com.fuicuiedu.idedemo.interview_demo.network;

import com.fuicuiedu.idedemo.interview_demo.result.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/11/14 0014.
 */

public interface MyApi {

//    city=城市名称&key=您申请的APPKEY值

    @GET("environment/air/cityair")
    Call<SearchResult> searchAir(@Query("city") String city, @Query("key") String key);
}
