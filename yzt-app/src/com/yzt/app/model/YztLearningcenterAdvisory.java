package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzt_learningcenter_advisory")
public class YztLearningcenterAdvisory {
    @Id
    private String id;

    /**
     * 电话号码
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 关联用户
     */
    @Column(name = "yzt_user_id")
    private String yztUserId;

    @Column(name = "yzt_learningcenter_id")
    private String yztLearningcenterId;

    private Date createtime;

    private Date modfiytime;

    @Column(name = "create_userid")
    private String createUserid;

    @Column(name = "modify_userid")
    private String modifyUserid;

    private Integer isdelete;

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
     * 获取电话号码
     *
     * @return phone_number - 电话号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置电话号码
     *
     * @param phoneNumber 电话号码
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取关联用户
     *
     * @return yzt_user_id - 关联用户
     */
    public String getYztUserId() {
        return yztUserId;
    }

    /**
     * 设置关联用户
     *
     * @param yztUserId 关联用户
     */
    public void setYztUserId(String yztUserId) {
        this.yztUserId = yztUserId;
    }

    /**
     * @return yzt_learningcenter_id
     */
    public String getYztLearningcenterId() {
        return yztLearningcenterId;
    }

    /**
     * @param yztLearningcenterId
     */
    public void setYztLearningcenterId(String yztLearningcenterId) {
        this.yztLearningcenterId = yztLearningcenterId;
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
}