package com.test.demo.controller;

import com.test.demo.code.ErrorEnum;
import com.test.demo.dto.AddressDto;
import com.test.demo.model.Result;
import com.test.demo.service.iface.IWeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @version V1.0
 * @Authoer Sam
 * @Since:2021/3/31
 */

@Api(tags = "天气")
@RestController
@RequestMapping("/weather")
public class WeatherController {


    @Autowired
    IWeatherService weatherService;


    /**
     * 根据地址获取天气
     */
    @PostMapping("/getTemperature")
    @ApiOperation("获取温度")
    public Result getTemperature(@RequestBody @Valid AddressDto addressDto) {
        Result result =null;
        try {
            result=weatherService.getTemperature(addressDto);
        }catch (Exception e)
        {
            result=Result.wrapErrorResult(ErrorEnum.COMMON_SYS_ERROR);
        }
        return result;
    }
}
