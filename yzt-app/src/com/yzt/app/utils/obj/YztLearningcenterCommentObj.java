package com.yzt.app.utils.obj;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

@Table(name = "yzt_learningcenter_comment")
public class YztLearningcenterCommentObj {
    @Id
    private String id;

    @Column(name = "yzt_learningcenter_id")
    private String yztLearningcenterId;

    @Column(name = "yzt_user_id")
    private String yztUserId;

    /**
     * 评论星级
     */
    private String grade;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 是否是匿名 0 不是  1是
     */
    private Integer anonymity;

    /**
     * 0评论 1回复
     */
    private Integer commenttype;

    @Column(name = "pic_count")
    private int picCount;

    private Date createtime;

    private Date modfiytime;

    @Column(name = "create_userid")
    private String createUserid;

    @Column(name = "modify_userid")
    private String modifyUserid;

    private Integer isdelete;
    
    private List<Map<String,Object>> piclist;

    public List<Map<String,Object>> getPiclist() {
		return piclist;
	}

	public void setPiclist(List<Map<String,Object>> piclist) {
		this.piclist = piclist;
	}

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
     * 获取评论星级
     *
     * @return grade - 评论星级
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 设置评论星级
     *
     * @param grade 评论星级
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 获取评论内容
     *
     * @return content - 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     *
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取是否是匿名 0 不是  1是
     *
     * @return anonymity - 是否是匿名 0 不是  1是
     */
    public Integer getAnonymity() {
        return anonymity;
    }

    /**
     * 设置是否是匿名 0 不是  1是
     *
     * @param anonymity 是否是匿名 0 不是  1是
     */
    public void setAnonymity(Integer anonymity) {
        this.anonymity = anonymity;
    }

    /**
     * 获取0评论 1回复
     *
     * @return commenttype - 0评论 1回复
     */
    public Integer getCommenttype() {
        return commenttype;
    }

    /**
     * 设置0评论 1回复
     *
     * @param commenttype 0评论 1回复
     */
    public void setCommenttype(Integer commenttype) {
        this.commenttype = commenttype;
    }

    /**
     * @return pic_url
     */
    public int getPicCount() {
        return picCount;
    }

    /**
     * @param picUrl
     */
    public void setPicCount(int picCount) {
        this.picCount = picCount;
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