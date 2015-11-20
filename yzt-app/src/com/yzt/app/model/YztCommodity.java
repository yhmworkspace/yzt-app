package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzt_commodity")
public class YztCommodity {
    @Id
    private String id;

    private String name;

    @Column(name = "integral_value")
    private  Integer integralValue;

    private String content;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    private Integer status;

    /**
     * 0兑换1抽奖
     */
    private Integer type;

    /**
     * 是否可以多次兑换或者抽奖 0单次  1多次
     */
    private String picurl;

    private Integer isrepetition;

    @Column(name = "is_count")
    private Integer isCount;

    @Column(name = "is_sell_count")
    private Integer isSellCount;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    

    public Integer getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(Integer integralValue) {
		this.integralValue = integralValue;
	}

	/**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return start_time
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return end_time
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取0兑换1抽奖
     *
     * @return type - 0兑换1抽奖
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置0兑换1抽奖
     *
     * @param type 0兑换1抽奖
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取是否可以多次兑换或者抽奖 0单次  1多次
     *
     * @return picurl - 是否可以多次兑换或者抽奖 0单次  1多次
     */
    public String getPicurl() {
        return picurl;
    }

    /**
     * 设置是否可以多次兑换或者抽奖 0单次  1多次
     *
     * @param picurl 是否可以多次兑换或者抽奖 0单次  1多次
     */
    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    /**
     * @return isrepetition
     */
    public Integer getIsrepetition() {
        return isrepetition;
    }

    /**
     * @param isrepetition
     */
    public void setIsrepetition(Integer isrepetition) {
        this.isrepetition = isrepetition;
    }

    /**
     * @return is_count
     */
    public Integer getIsCount() {
        return isCount;
    }

    /**
     * @param isCount
     */
    public void setIsCount(Integer isCount) {
        this.isCount = isCount;
    }

    /**
     * @return is_sell_count
     */
    public Integer getIsSellCount() {
        return isSellCount;
    }

    /**
     * @param isSellCount
     */
    public void setIsSellCount(Integer isSellCount) {
        this.isSellCount = isSellCount;
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