package com.test.demo.model;

import com.test.demo.code.ErrorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @version V1.0
 * @Authoer Sam
 * @Since:2021/3/31
 */
@ApiModel(value = "返回说明")
public class Result<D> implements Serializable {

    private static final long serialVersionUID = -7647570604845078925L;
    private static final int SUCCESS_CODE = 0;
    private D data;
    private boolean success;
    @ApiModelProperty(value = "返回状态码")
    private int code;
    @ApiModelProperty(value = "描述信息")
    private String message;

    public static <D> com.test.demo.model.Result<D> wrapSuccessfulResult(D data) {
        com.test.demo.model.Result<D> result = new com.test.demo.model.Result<>();
        result.data = data;
        result.success = true;
        result.code = SUCCESS_CODE;
        return result;

    }

    public static <D> com.test.demo.model.Result<D> wrapSuccessfulResult() {
        com.test.demo.model.Result<D> result = new com.test.demo.model.Result<>();
        result.success = true;
        result.code = SUCCESS_CODE;
        return result;

    }

    public static <T> com.test.demo.model.Result<T> wrapSuccessfulResult(String message, T data) {
        com.test.demo.model.Result<T> result = new com.test.demo.model.Result<>();
        result.data = data;
        result.success = true;
        result.code = SUCCESS_CODE;
        result.message = message;
        return result;
    }

    public static <D> com.test.demo.model.Result<D> wrapErrorResult(ErrorEnum errorEnum) {
        com.test.demo.model.Result<D> result = new com.test.demo.model.Result<>();
        result.success = false;
        result.code = errorEnum.getErrorCode();
        result.message = errorEnum.getErrorMessage();
        return result;
    }

    public static <D> com.test.demo.model.Result<D> wrapErrorResult(ErrorEnum errorEnum, D data) {
        com.test.demo.model.Result<D> result = new com.test.demo.model.Result<>();
        result.data = data;
        result.success = false;
        result.code = errorEnum.getErrorCode();
        result.message = errorEnum.getErrorMessage();
        return result;
    }

    public static <D> com.test.demo.model.Result<D> wrapErrorResult(int code, String message) {
        com.test.demo.model.Result<D> result = new com.test.demo.model.Result<>();
        result.success = false;
        result.code = code;
        result.message = message;
        return result;
    }

    public static <D> com.test.demo.model.Result<D> wrapErrorResult(int code, String message, D data) {
        com.test.demo.model.Result<D> result = new com.test.demo.model.Result<>();
        result.data = data;
        result.success = false;
        result.code = code;
        result.message = message;
        return result;
    }

    public D getData() {
        return data;
    }

    public com.test.demo.model.Result<D> setData(D data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public com.test.demo.model.Result<D> setSuccess(boolean success) {
        this.success = success;
        return this;

    }

    public int getCode() {
        return code;
    }

    public com.test.demo.model.Result<D> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public com.test.demo.model.Result<D> setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        sb.append("success=");
        sb.append(success);

        sb.append(",");

        sb.append("code=");
        sb.append(code);
        sb.append(",");

        sb.append("message=");
        sb.append(message);
        sb.append(",");

        sb.append("data=");
        sb.append(data);

        sb.append("}");

        return sb.toString();
    }
}
