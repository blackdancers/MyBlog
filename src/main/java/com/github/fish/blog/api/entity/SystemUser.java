package com.github.fish.blog.api.entity;

import com.github.fish.common.entity.AbstractBaseEntity;
import com.github.fish.common.utils.CoderUtil;

import java.util.Date;
import javax.persistence.*;

@Table(name = "system_user")
public class SystemUser extends AbstractBaseEntity {
    /**
     * 用户姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户账号
     */
    @Column(name = "user_account")
    private String userAccount;

    /**
     * 用户头像
     */
    @Column(name = "user_avatar")
    private String userAvatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别[0:保密,1:男,2:女]
     */
    private String sex;

    /**
     * 联系电话
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 邮箱
     */
    @Column(name = "user_email")
    private String userEmail;

    /**
     * 类型
     */
    @Column(name = "user_type")
    private String userType;

    /**
     * 状态[0:正常;1:禁用;2:删除]
     */
    @Column(name = "user_status")
    private String userStatus;

    /**
     * 盐值(加密)
     */
    @Column(name = "salt_value")
    private String saltValue;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 获取用户姓名
     *
     * @return user_name - 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     *
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取用户账号
     *
     * @return user_account - 用户账号
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * 设置用户账号
     *
     * @param userAccount 用户账号
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    /**
     * 获取用户头像
     *
     * @return user_avatar - 用户头像
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * 设置用户头像
     *
     * @param userAvatar 用户头像
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取性别[0:保密,1:男,2:女]
     *
     * @return sex - 性别[0:保密,1:男,2:女]
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别[0:保密,1:男,2:女]
     *
     * @param sex 性别[0:保密,1:男,2:女]
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 获取联系电话
     *
     * @return mobile_phone - 联系电话
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置联系电话
     *
     * @param mobilePhone 联系电话
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * 获取邮箱
     *
     * @return user_email - 邮箱
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置邮箱
     *
     * @param userEmail 邮箱
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    /**
     * 获取类型
     *
     * @return user_type - 类型
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 设置类型
     *
     * @param userType 类型
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     * 获取状态[0:正常;1:禁用;2:删除]
     *
     * @return user_status - 状态[0:正常;1:禁用;2:删除]
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * 设置状态[0:正常;1:禁用;2:删除]
     *
     * @param userStatus 状态[0:正常;1:禁用;2:删除]
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    /**
     * 获取盐值(加密)
     *
     * @return salt_value - 盐值(加密)
     */
    public String getSaltValue() {
        return saltValue;
    }

    /**
     * 设置盐值(加密)
     *
     * @param saltValue 盐值(加密)
     */
    public void setSaltValue(String saltValue) {
        this.saltValue = saltValue == null ? null : saltValue.trim();
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
     * 获取更新时间
     *
     * @return update_date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 加密
     */
    public void encrypt() {
        this.mobilePhone = CoderUtil.encrypt(this.mobilePhone);
    }

    /**
     * 解密
     */
    public void decrypt() {
        this.mobilePhone = CoderUtil.decrypt(this.mobilePhone);
    }
}