package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzt_integral_detail")
public class YztIntegralDetail {
    @Id
    private String id;

    @Column(name = "yzt_user_id")
    private String yztUserId;

    /**
     * 原由（糖果数量变化原由,完成任务或兑换
     */
    private String content;

    /**
     * 时间
     */
    @Column(name = "content_datetime")
    private String contentDatetime;

    /**
     * 赚取1或消费0
     */
    private Integer status;

    /**
     * 糖果变化数量
     */
    @Column(name = "integral_count")
    private Integer integralCount;
    /**
     * 糖果变化数量
     */
    @Column(name = "integral_value")
    private Integer integralValue;

    public Integer getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(Integer integralValue) {
		this.integralValue = integralValue;
	}

	/**
     * 增加是对应任务id，消耗对应商品id
     */
    @Column(name = "task_id")
    private String taskId;

    /**
     * 消耗事对应商品的兑换码id
     */
    @Column(name = "cdkey_id")
    private String cdkeyId;
    /**
     * 消耗事对应商品的兑换码
     */
    @Column(name = "cdkey_code")
    private String cdkeyCode;

    public String getCdkeyCode() {
		return cdkeyCode;
	}

	public void setCdkeyCode(String cdkeyCode) {
		this.cdkeyCode = cdkeyCode;
	}

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
     * @return yzt_user_id
     */
    public String getYztUserId() {
        return yztUserId;
    }

    /**
     * @param yztUserId
     */
    public void setYztUserId(String yztUserId) {
        this.yztUserId = yztUserId;
    }

    /**
     * 获取原由（糖果数量变化原由,完成任务或兑换
     *
     * @return content - 原由（糖果数量变化原由,完成任务或兑换
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置原由（糖果数量变化原由,完成任务或兑换
     *
     * @param content 原由（糖果数量变化原由,完成任务或兑换
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取时间
     *
     * @return content_datetime - 时间
     */
    public String getContentDatetime() {
        return contentDatetime;
    }

    /**
     * 设置时间
     *
     * @param contentDatetime 时间
     */
    public void setContentDatetime(String contentDatetime) {
        this.contentDatetime = contentDatetime;
    }

    /**
     * 获取赚取1或消费0
     *
     * @return status - 赚取1或消费0
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置赚取1或消费0
     *
     * @param status 赚取1或消费0
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取糖果变化数量
     *
     * @return integral_count - 糖果变化数量
     */
    public Integer getIntegralCount() {
        return integralCount;
    }

    /**
     * 设置糖果变化数量
     *
     * @param integralCount 糖果变化数量
     */
    public void setIntegralCount(Integer integralCount) {
        this.integralCount = integralCount;
    }

    /**
     * 获取增加是对应任务id，消耗对应商品id
     *
     * @return task_id - 增加是对应任务id，消耗对应商品id
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置增加是对应任务id，消耗对应商品id
     *
     * @param taskId 增加是对应任务id，消耗对应商品id
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 获取消耗事对应商品的兑换码id
     *
     * @return cdkey_id - 消耗事对应商品的兑换码id
     */
    public String getCdkeyId() {
        return cdkeyId;
    }

    /**
     * 设置消耗事对应商品的兑换码id
     *
     * @param cdkeyId 消耗事对应商品的兑换码id
     */
    public void setCdkeyId(String cdkeyId) {
        this.cdkeyId = cdkeyId;
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