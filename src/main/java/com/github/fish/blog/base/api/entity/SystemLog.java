package com.github.fish.blog.base.api.entity;


import com.github.fish.blog.common.entity.AbstractBaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "system_log")
public class SystemLog extends AbstractBaseEntity {
    /**
     * 方法描述
     */
    private String description;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 方法参数
     */
    private String params;

    /**
     * 注解类型[0:控制器方法;1:service方法]
     */
    private String type;

    /**
     * 模块名称
     */
    private String module;

    /**
     * 真实IP
     */
    @Column(name = "real_ip")
    private String realIp;

    /**
     * 错误码
     */
    @Column(name = "error_code")
    private String errorCode;

    /**
     * 操作者ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 异常详情
     */
    @Column(name = "error_detail")
    private String errorDetail;

    /**
     * 获取方法描述
     *
     * @return description - 方法描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置方法描述
     *
     * @param description 方法描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取方法名称
     *
     * @return method - 方法名称
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置方法名称
     *
     * @param method 方法名称
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * 获取方法参数
     *
     * @return params - 方法参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置方法参数
     *
     * @param params 方法参数
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * 获取注解类型[0:控制器方法;1:service方法]
     *
     * @return type - 注解类型[0:控制器方法;1:service方法]
     */
    public String getType() {
        return type;
    }

    /**
     * 设置注解类型[0:控制器方法;1:service方法]
     *
     * @param type 注解类型[0:控制器方法;1:service方法]
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取模块名称
     *
     * @return module - 模块名称
     */
    public String getModule() {
        return module;
    }

    /**
     * 设置模块名称
     *
     * @param module 模块名称
     */
    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    /**
     * 获取真实IP
     *
     * @return real_ip - 真实IP
     */
    public String getRealIp() {
        return realIp;
    }

    /**
     * 设置真实IP
     *
     * @param realIp 真实IP
     */
    public void setRealIp(String realIp) {
        this.realIp = realIp == null ? null : realIp.trim();
    }

    /**
     * 获取错误码
     *
     * @return error_code - 错误码
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 设置错误码
     *
     * @param errorCode 错误码
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }

    /**
     * 获取操作者ID
     *
     * @return user_id - 操作者ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置操作者ID
     *
     * @param userId 操作者ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取异常详情
     *
     * @return error_detail - 异常详情
     */
    public String getErrorDetail() {
        return errorDetail;
    }

    /**
     * 设置异常详情
     *
     * @param errorDetail 异常详情
     */
    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail == null ? null : errorDetail.trim();
    }
}