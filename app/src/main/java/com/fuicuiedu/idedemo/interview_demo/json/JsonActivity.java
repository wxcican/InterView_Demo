package com.fuicuiedu.idedemo.interview_demo.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fuicuiedu.idedemo.interview_demo.R;
import com.fuicuiedu.idedemo.interview_demo.network.MyClient;
import com.fuicuiedu.idedemo.interview_demo.result.SearchResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JsonActivity extends AppCompatActivity {

    private static String JSON = "{\n" +
            "    \"resultcode\": \"200\",\n" +
            "    \"reason\": \"SUCCESSED!\",\n" +
            "    \"result\": [\n" +
            "        {\n" +
            "            \"city\": \"苏州\",  /*城市*/\n" +
            "            \"PM2.5\": \"73\",  /*PM2.5指数*/\n" +
            "            \"AQI\": \"98\",    /*空气质量指数*/\n" +
            "            \"quality\": \"良\", /*空气质量*/\n" +
            "            \"PM10\": \"50\",/*PM10*/\n" +
            "            \"CO\": \"0.79\",  /*一氧化碳*/\n" +
            "            \"NO2\": \"65\",  /*二氧化氮*/\n" +
            "            \"O3\": \"28\",    /*臭氧*/\n" +
            "            \"SO2\": \"41\",  /*二氧化硫*/\n" +
            "            \"time\": \"2014-12-26 11:48:40\"/*更新时间*/  \n" +
            "        }\n" +
            "    ],\n" +
            "    \"error_code\": 0\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        ButterKnife.bind(this);


        MyClient.getInstances().searchAir("shanghai","ff49b2a6cee5196226f2696813e266bd").enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                Log.e("aaa",response.body().toString());
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {

            }
        });


    }

    @OnClick(R.id.json_btn)
    public void onClick(){
        try {
            JSONObject jsonObject = new JSONObject(JSON);

            String resultcode = jsonObject.getString("resultcode");
            Log.e("json","resultcode = " + resultcode);

            String reason = jsonObject.getString("reason");
            Log.e("json","reason = " + reason);


            JSONArray result = jsonObject.getJSONArray("result");
            for (int i = 0;i<result.length();i++){
                JSONObject object = result.getJSONObject(i);
                Log.e("json",object.toString());
            }


            String error_code = jsonObject.getString("error_code");
            Log.e("json","error_code = " + error_code);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
