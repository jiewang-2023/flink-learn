package com.imooc.flink.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GaodeApp {

    public static void main(String[] args) {
        String ip = "113.87.128.212";


        String province = "-";
        String city = "-";

        String key = "0e59dff213571da1d35e15a3c77ad4ff";
        String url = "https://restapi.amap.com/v3/ip?ip="+ip+"&output=json&key="+ key;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        CloseableHttpResponse response = null;

        try {
            HttpGet httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == 200) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "UTF-8");


                JSONObject jsonObject = JSON.parseObject(result);
                province = jsonObject.getString("province");
                city = jsonObject.getString("city");

                System.out.println(province + "\t" + city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != response) try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}