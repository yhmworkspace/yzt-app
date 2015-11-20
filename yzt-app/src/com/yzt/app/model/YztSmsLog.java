package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzt_sms_log")
public class YztSmsLog {
    @Id
    private String id;

    /**
     * 手机号
     */
    @Column(name = "is_mobile")
    private String isMobile;

    /**
     * 短信内容
     */
    @Column(name = "is_content")
    private String isContent;

    /**
     * 触发通道返回编号
     */
    @Column(name = "sms_id")
    private String smsId;

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
     * 获取短信内容
     *
     * @return is_content - 短信内容
     */
    public String getIsContent() {
        return isContent;
    }

    /**
     * 设置短信内容
     *
     * @param isContent 短信内容
     */
    public void setIsContent(String isContent) {
        this.isContent = isContent;
    }

    /**
     * 获取触发通道返回编号
     *
     * @return sms_id - 触发通道返回编号
     */
    public String getSmsId() {
        return smsId;
    }

    /**
     * 设置触发通道返回编号
     *
     * @param smsId 触发通道返回编号
     */
    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    /**
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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