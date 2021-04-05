package com.test.demo;

import com.test.demo.dto.AddressDto;
import com.test.demo.model.Result;
import com.test.demo.service.iface.IWeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EpamApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    IWeatherService weatherService;


    //正常参数测试
    @Test
    void getTemperature()
    {
        AddressDto addressDto=new AddressDto();
        addressDto.setCityCode("04");
        addressDto.setCountyCode("01");
        addressDto.setProvinceCode("10119");
        Result result=weatherService.getTemperature(addressDto);
        assert(result.isSuccess());

    }
    //省代码参数
    @Test
    void getTemperatureProvinceError()
    {
        AddressDto addressDto=new AddressDto();
        addressDto.setCityCode("04");
        addressDto.setCountyCode("01");
        addressDto.setProvinceCode("90111");
        Result result=weatherService.getTemperature(addressDto);
        System.out.println(result.getMessage());
        assert(!result.isSuccess());

    }

    //市代码参数
    @Test
    void getTemperatureCityError()
    {
        AddressDto addressDto=new AddressDto();
        addressDto.setCityCode("99");
        addressDto.setCountyCode("01");
        addressDto.setProvinceCode("10119");
        Result result=weatherService.getTemperature(addressDto);
        System.out.println(result.getMessage());
        assert(!result.isSuccess());

    }

    //县代码参数
    @Test
    void getTemperatureCountryError()
    {
        AddressDto addressDto=new AddressDto();
        addressDto.setCityCode("04");
        addressDto.setCountyCode("99");
        addressDto.setProvinceCode("10119");
        Result result=weatherService.getTemperature(addressDto);
        System.out.println(result.getMessage());
        assert(!result.isSuccess());

    }
}
