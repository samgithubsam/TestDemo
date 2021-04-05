package com.test.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.fastinfoset.stax.events.Util;
import com.test.demo.dto.AddressDto;
import com.test.demo.model.Result;
import com.test.demo.model.WeatherInfo;
import com.test.demo.service.iface.IWeatherService;
import com.test.demo.util.WeatherUtils;
import org.springframework.retry.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @version V1.0
 * @Authoer Sam
 * @Since:2021/3/31
 */
@Service
public class WeatherServiceImpl implements IWeatherService {

    private static String weatherUrl = "http://www.weather.com.cn/data/sk/";
    private static String provinceUrl = "http://www.weather.com.cn/data/city3jdata/china.html";
    private static String cityUrlpreifx = "http://www.weather.com.cn/data/city3jdata/provshi/";
    private static String countyUrlpreifx = "http://www.weather.com.cn/data/city3jdata/station/";

    @Override
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(value = 1000))
    public Result getTemperature(AddressDto addressDto) {
        System.out.println("******getTemperature******");
        //检查参数是否超出范围
        String errorStr = checkAddress(addressDto);
        if (!Util.isEmptyString(errorStr)) {
            return Result.wrapErrorResult(0, errorStr);
        }
//        //模拟出现问题
//        int a=1/0;
        //封装url
        String url = weatherUrl + addressDto.getProvinceCode() + addressDto.getCityCode() + addressDto.getCountyCode() + ".html";
        //发送请求获取天气信息
        String weatherInfo = WeatherUtils.getWeatherInfo(url);
        //解析返回json
        if (!Util.isEmptyString(weatherInfo)) {
            JSONObject dataOfJson = JSONObject.parseObject(weatherInfo);
            String weatherData = dataOfJson.getString("weatherinfo");
            JSONObject w = JSONObject.parseObject(weatherData);
            WeatherInfo weather = w.toJavaObject(WeatherInfo.class);
            return Result.wrapSuccessfulResult(weather.getTemp());
        }
        return Result.wrapSuccessfulResult();
    }



    @Recover
    public void recover(RetryException e) {
        System.out.println("******recover******");
        System.out.println(e.getMessage());
    }

    /***
     * checkAddress
     * @param
     * @return
     */
    public String checkAddress(AddressDto addressDto) {
        String provinceStr = WeatherUtils.getPlaceCode(provinceUrl);
        if (!provinceStr.contains(addressDto.getProvinceCode())) {
            return "省代码不正确";
        }
        String cityUrl = cityUrlpreifx + addressDto.getProvinceCode() + ".html";
        String cityStr = WeatherUtils.getPlaceCode(cityUrl);
        if (!cityStr.contains(addressDto.getCityCode())) {
            return "市代码不正确";
        }
        String countyUrl = countyUrlpreifx + addressDto.getProvinceCode() + addressDto.getCityCode() + ".html";
        String countyStr = WeatherUtils.getPlaceCode(countyUrl);
        if (!countyStr.contains(addressDto.getCountyCode())) {
            return "县代码不正确";
        }
        return null;
    }
}
