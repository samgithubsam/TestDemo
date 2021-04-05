package com.test.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @version V1.0
 * @Authoer Sam
 * @Since:2021/3/31
 */
@Data
public class AddressDto {

    @ApiModelProperty(value = "省代码")
    @NotEmpty
    @Length(min=5, max=5,message = "省代码长度应该是5位！")
    private String provinceCode;

    @ApiModelProperty(value = "市代码")
    @NotEmpty
    @Length(min=2, max=2,message = "市代码长度应该是2位！")
    private String cityCode;

    @ApiModelProperty(value = "县代码")
    @NotEmpty
    @Length(min=2, max=2,message = "县代码长度应该是2位！")
    private String countyCode;

}
