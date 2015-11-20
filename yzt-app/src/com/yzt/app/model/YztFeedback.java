package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzt_feedback")
public class YztFeedback {
    @Id
    private String id;

    /**
     * 意见反馈内容
     */
    @Column(name = "feedback_content")
    private String feedbackContent;

    /**
     * 关联用户多对一
     */
    @Column(name = "yzt_user_id")
    private String yztUserId;

    /**
     * 是否处理0-1
     */
    private Integer status;

    /**
     * 回应内容
     */
    private String responsecontent;

    /**
     * 回应时间
     */
    private Date responsetime;

    /**
     * 回应人。对应客服id。
     */
    @Column(name = "responser_id")
    private String responserId;
    
    @Column(name = "responser_name")
    private String responserName;

    public String getResponserId() {
		return responserId;
	}

	public void setResponserId(String responserId) {
		this.responserId = responserId;
	}

	public String getResponserName() {
		return responserName;
	}

	public void setResponserName(String responserName) {
		this.responserName = responserName;
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
     * 获取意见反馈内容
     *
     * @return feedback_content - 意见反馈内容
     */
    public String getFeedbackContent() {
        return feedbackContent;
    }

    /**
     * 设置意见反馈内容
     *
     * @param feedbackContent 意见反馈内容
     */
    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
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
     * 获取是否处理0-1
     *
     * @return status - 是否处理0-1
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否处理0-1
     *
     * @param status 是否处理0-1
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取回应内容
     *
     * @return responsecontent - 回应内容
     */
    public String getResponsecontent() {
        return responsecontent;
    }

    /**
     * 设置回应内容
     *
     * @param responsecontent 回应内容
     */
    public void setResponsecontent(String responsecontent) {
        this.responsecontent = responsecontent;
    }

    /**
     * 获取回应时间
     *
     * @return responsetime - 回应时间
     */
    public Date getResponsetime() {
        return responsetime;
    }

    /**
     * 设置回应时间
     *
     * @param responsetime 回应时间
     */
    public void setResponsetime(Date responsetime) {
        this.responsetime = responsetime;
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