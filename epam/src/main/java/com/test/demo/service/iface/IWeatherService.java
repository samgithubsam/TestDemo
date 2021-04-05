package com.test.demo.service.iface;

import com.test.demo.dto.AddressDto;
import com.test.demo.model.Result;

/**
 * @version V1.0
 * @Authoer Sam
 * @Since:2021/3/31
 */
public interface IWeatherService {

    //获取温度
    Result getTemperature(AddressDto addressDto);

}
