package com.github.fish.common.constant;


/**
 * 常量
 */
public class IConstInfo {

    private IConstInfo() {}

    public static final  String 	DEFAULT_KEY = "rc4key";
    public static final  String 	SECRET_KEY = "rc4fb1234";
    public static final  String 	DES_KEY = "DES_KEY";

    public static final  String 	ZERO = "0";                             // 状态0
    public static final  String 	ONE = "1";                              // 状态1
    public static final  String 	TWO = "2";                              // 状态2

    public static final  String 	ISABLE = "0";                           // 状态OK
    public static final  String 	DISABLE = "1";                          // 状态不可用
    public static final  String 	ISDELETE = "1";                         // 删除状态
    public static final  Integer 	LOGIN_MAX_TIME = 10;                    // 频繁登陆次数限定


    public static final  Integer    EXCEPTION_TYPE_GENERAL = -1;            // 普通异常，如增删除改查（区别于业务异常）
    public static final  Integer    EXCEPTION_TYPE_BUSINESS = 1;            // 业务异常 ....
    public static final  Integer    EXCEPTION_TYPE_FILE_UPLOAD = 4;         // 文件上传异常

    public static final  String 	CURRENT_USER = "systemUser";           // 删除状态



}