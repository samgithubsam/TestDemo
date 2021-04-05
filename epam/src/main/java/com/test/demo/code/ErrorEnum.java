package com.test.demo.code;

/**
 * @version V1.0
 * @Authoer Sam
 * @Since:2021/3/31
 */

public enum ErrorEnum {

    //登录
    LOGIN_FAIL(9000,9001,"登录失败"),
    //基础操作
    COMMON_ID_NULL(1000, 1001, "主键为空"),
    CRUD_DEL_OPT(1000, 1002, "删除失败"),
    CRUD_UPD_OPT(1000, 1003, "修改失败"),
    CRUD_SAVE_OPT(1000, 1004, "保存失败"),
    COMMON_SYS_ERROR(1000, 1005, "系统错误"),
    //校验
    CHECK_COMPONENTPROPERTIES_COLUMN(1000, 1006, "配置列已存在"),
    VALID_MODEL_FIELD(1000, 1008, "校验字段"),
    //文件
    COMMON_UPLOAD_ERROR(1000, 1007, "文件上传失败"),
    //异常操作
    CRON_EXPRESSION(2000, 2001, "cron表达式格式不正确"),
    JOB_EXISTS(2000, 2002, "任务已存在"),
    ADD_JOB_ERROR(2000, 2003, "添加任务失败"),
    SYNC_DATA_ERROR(3000, 3001, "同步数据失败");
    private final int errorCode;
    private final int parentCode;
    private final String errorMessage;

    ErrorEnum(int parentCode, int errorCode, String errorMessage) {
        this.parentCode = parentCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getParentCode() {
        if (String.valueOf(errorCode).startsWith("1")) {
            return errorCode;
        }
        return parentCode;
    }

    public com.test.demo.code.ErrorEnum getOutError() {
        return getErrorByCode(getParentCode());
    }

    public static com.test.demo.code.ErrorEnum getErrorByCode(int code) {
        for (com.test.demo.code.ErrorEnum errorEnum : values()) {
            if (errorEnum.getErrorCode() == code) {
                return errorEnum;
            }
        }
        return null;
    }
}
