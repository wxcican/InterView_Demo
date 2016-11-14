package com.fuicuiedu.idedemo.interview_demo.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fuicuiedu.idedemo.interview_demo.R;

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
    }
}
