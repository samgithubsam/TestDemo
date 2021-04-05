package com.test.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.test.demo.model.WeatherInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * @version V1.0
 * @Authoer Sam
 * @Since:2021/3/31
 */
public class WeatherUtils {

    private static String weatherUrl = "http://www.weather.com.cn/data/sk/101190401.html";
    private static String provinceUrl = "http://www.weather.com.cn/data/city3jdata/china.html";
    private static String cityUrl = "http://www.weather.com.cn/data/city3jdata/provshi/10119.html";
    private static String countyUrl = "http://www.weather.com.cn/data/city3jdata/station/1011904.html";

    /**
     * 通过城市名称获取该城市的天气信息
     */
    public static String getWeatherInfo(String urlPath){
        InputStreamReader inReader=null;
        BufferedReader bufferedReader=null;
        //拼接接收的信息
        StringBuffer info=new StringBuffer();
        //读取每行的数据
        String inputline="";
        try {
            //实例化URL对象
            URL url= new URL(urlPath);
            //获取应用程序和 URL 之间的通信链接
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            // 请求方法
            conn.setRequestMethod("GET");
            //获取url的资源输入流
            inReader=new InputStreamReader(conn.getInputStream(),"utf-8");
            //获取缓冲字符输入流
            bufferedReader=new BufferedReader(inReader);
            //读取每行数据（同时赋值，判断是否为空）
            while((inputline=bufferedReader.readLine())!=null){
                //添加信息
                info.append(inputline);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return info.toString();
    }



    /**
     * 通过城市名称获取该城市的天气信息
     */
    public static String getPlaceCode(String urlPath){
        InputStreamReader inReader=null;
        BufferedReader bufferedReader=null;
        //拼接接收的信息
        StringBuffer info=new StringBuffer();
        //读取每行的数据
        String inputline="";
        try {
            //实例化URL对象
            URL url= new URL(urlPath);
            //获取应用程序和 URL 之间的通信链接
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            // 请求方法
            conn.setRequestMethod("GET");
            //获取url的资源输入流
            inReader=new InputStreamReader(conn.getInputStream(),"utf-8");
            //获取缓冲字符输入流
            bufferedReader=new BufferedReader(inReader);
            //读取每行数据（同时赋值，判断是否为空）
            while((inputline=bufferedReader.readLine())!=null){
                //添加信息
            info.append(inputline);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return info.toString();
    }

    public static void main(String[] args){
        String winfo = WeatherUtils.getWeatherInfo(weatherUrl);
        System.out.println("1.预测结果：" + winfo);
        JSONObject dataOfJson=JSONObject.parseObject(winfo);
        String temp=dataOfJson.getString("weatherinfo");
        JSONObject w=JSONObject.parseObject(temp);
        WeatherInfo weather=w.toJavaObject(WeatherInfo.class);
        System.out.println("temp:"+weather.getTemp());

        String info = WeatherUtils.getPlaceCode(provinceUrl);
        System.out.println("1.预测结果：" + info);
        Map<String,Object> map = JSONObject.parseObject(info);

        String info2 = WeatherUtils.getPlaceCode(cityUrl);
        System.out.println("2.预测结果：" + info2);
        Map<String,Object> map2 = JSONObject.parseObject(info2);

        String info3 = WeatherUtils.getPlaceCode(countyUrl);
        System.out.println("3.预测结果：" + info3);
        Map<String,Object> map3 = JSONObject.parseObject(info3);

    }


}