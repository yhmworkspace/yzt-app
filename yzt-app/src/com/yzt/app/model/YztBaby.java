package com.yzt.app.model;

import java.util.Date;

import javax.persistence.*;

@Table(name = "yzt_baby")
public class YztBaby {
    @Id
    private String id;

    /**
     * 宝宝名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 0男1女
     */
    @Column(name = "sex")
    private String sex;

    /**
     * 生日
     */
    @Column(name = "birthday")
    private String birthday;

    /**
     * 生肖
     */
    private String zodiac;

    /**
     * 关联用户多对一
     */
    @Column(name = "yzt_user_id")
    private String yztUserId;

    /**
     * 星座
     */
    private String constellation;

    /**
     * 图片路径
     */
    private String picurl;

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
     * 获取宝宝名称
     *
     * @return name - 宝宝名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置宝宝名称
     *
     * @param name 宝宝名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取0男1女
     *
     * @return sex - 0男1女
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置0男1女
     *
     * @param sex 0男1女
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取生日
     *
     * @return birthday - 生日
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取生肖
     *
     * @return zodiac - 生肖
     */
    public String getZodiac() {
        return zodiac;
    }

    /**
     * 设置生肖
     *
     * @param zodiac 生肖
     */
    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    /**
     * 获取关联用户多对一
     *
     * @return yzt_user_id - 关联用户多对一
     */
    public String getYztUserId() {
        return yztUserId;
    }

    /**
     * 设置关联用户多对一
     *
     * @param yztUserId 关联用户多对一
     */
    public void setYztUserId(String yztUserId) {
        this.yztUserId = yztUserId;
    }

    /**
     * 获取星座
     *
     * @return constellation - 星座
     */
    public String getConstellation() {
        return constellation;
    }

    /**
     * 设置星座
     *
     * @param constellation 星座
     */
    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    /**
     * 获取图片路径
     *
     * @return picurl - 图片路径
     */
    public String getPicurl() {
        return picurl;
    }

    /**
     * 设置图片路径
     *
     * @param picurl2 图片路径
     */
    public void setPicurl(String picurl) {
        this.picurl = picurl;
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