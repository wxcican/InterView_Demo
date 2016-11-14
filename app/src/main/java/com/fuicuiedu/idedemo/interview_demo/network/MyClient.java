package com.fuicuiedu.idedemo.interview_demo.network;

import com.fuicuiedu.idedemo.interview_demo.result.SearchResult;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/11/14 0014.
 */

public class MyClient implements MyApi{
    private static MyClient myClient;
    private MyApi myApi;

    public static MyClient getInstances(){
        if (myClient == null){
            myClient = new MyClient();
        }
        return myClient;
    }


    private MyClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://web.juhe.cn:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myApi = retrofit.create(MyApi.class);
    }

    @Override
    public Call<SearchResult> searchAir(@Query("city") String city, @Query("key") String key) {
        return myApi.searchAir(city, key);
    }
}
