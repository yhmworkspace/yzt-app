package com.yzt.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "yzt_registor")
public class YztRegistor {
    @Id
    private String id;

    /**
     * 手机号
     */
    @Column(name = "is_mobile")
    private String isMobile;
    
    /**
     * 密码
     */
    @Column(name = "is_password")
    private String isPassword;

    public String getIsPassword() {
		return isPassword;
	}

	public void setIsPassword(String isPassword) {
		this.isPassword = isPassword;
	}

	/**
     * 发送用户的手机验证码
     */
    @Column(name = "is_verify_code")
    private String isVerifyCode;

    /**
     * 短信通道返回的编号
     */
    @Column(name = "sms_id")
    private String smsId;

    /**
     * 发送时间
     */
    @Column(name = "send_time")
    private Date sendTime;

    /**
     * 过期时间
     */
    @Column(name = "overdue_time")
    private Date overdueTime;

    /**
     * 注册成功时间
     */
    @Column(name = "regist_time")
    private Date registTime;

    /**
     * 请求次数，n次后禁止触发短信通道。并添加至黑名单
     */
    @Column(name = "request_count")
    private Integer requestCount;

    /**
     * 是否注册成功,1-成功
     */
    private Integer status;

    private Date createtime;

    private Integer isdelete;

    private Date modfiytime;

    @Column(name = "create_userid")
    private String createUserid;

    @Column(name = "modify_userid")
    private String modifyUserid;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取手机号
     *
     * @return is_mobile - 手机号
     */
    public String getIsMobile() {
        return isMobile;
    }

    /**
     * 设置手机号
     *
     * @param isMobile 手机号
     */
    public void setIsMobile(String isMobile) {
        this.isMobile = isMobile;
    }

    /**
     * 获取发送用户的手机验证码
     *
     * @return is_verify_code - 发送用户的手机验证码
     */
    public String getIsVerifyCode() {
        return isVerifyCode;
    }

    /**
     * 设置发送用户的手机验证码
     *
     * @param isVerifyCode 发送用户的手机验证码
     */
    public void setIsVerifyCode(String isVerifyCode) {
        this.isVerifyCode = isVerifyCode;
    }

    /**
     * 获取短信通道返回的编号
     *
     * @return sms_id - 短信通道返回的编号
     */
    public String getSmsId() {
        return smsId;
    }

    /**
     * 设置短信通道返回的编号
     *
     * @param smsId 短信通道返回的编号
     */
    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    /**
     * 获取发送时间
     *
     * @return send_time - 发送时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 设置发送时间
     *
     * @param sendTime 发送时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 获取过期时间
     *
     * @return overdue_time - 过期时间
     */
    public Date getOverdueTime() {
        return overdueTime;
    }

    /**
     * 设置过期时间
     *
     * @param overdueTime 过期时间
     */
    public void setOverdueTime(Date overdueTime) {
        this.overdueTime = overdueTime;
    }

    /**
     * 获取注册成功时间
     *
     * @return regist_time - 注册成功时间
     */
    public Date getRegistTime() {
        return registTime;
    }

    /**
     * 设置注册成功时间
     *
     * @param registTime 注册成功时间
     */
    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    /**
     * 获取请求次数，n次后禁止触发短信通道。并添加至黑名单
     *
     * @return request_count - 请求次数，n次后禁止触发短信通道。并添加至黑名单
     */
    public Integer getRequestCount() {
        return requestCount;
    }

    /**
     * 设置请求次数，n次后禁止触发短信通道。并添加至黑名单
     *
     * @param requestCount 请求次数，n次后禁止触发短信通道。并添加至黑名单
     */
    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }

    /**
     * 获取是否注册成功,1-成功
     *
     * @return status - 是否注册成功,1-成功
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否注册成功,1-成功
     *
     * @param status 是否注册成功,1-成功
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param dateTime
     */
    public void setCreatetime(Date dateTime) {
        this.createtime = dateTime;
    }

    /**
     * @return isdelete
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * @param isdelete
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    /**
     * @return modfiytime
     */
    public Date getModfiytime() {
        return modfiytime;
    }

    /**
     * @param modfiytime
     */
    public void setModfiytime(Date modfiytime) {
        this.modfiytime = modfiytime;
    }

    /**
     * @return create_userid
     */
    public String getCreateUserid() {
        return createUserid;
    }

    /**
     * @param createUserid
     */
    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }

    /**
     * @return modify_userid
     */
    public String getModifyUserid() {
        return modifyUserid;
    }

    /**
     * @param modifyUserid
     */
    public void setModifyUserid(String modifyUserid) {
        this.modifyUserid = modifyUserid;
    }
}