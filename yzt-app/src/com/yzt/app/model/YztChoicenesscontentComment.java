package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;
/**
 * 用于精选内容评论
 * @author Administrator
 *
 */
@Table(name = "yzt_choicenesscontent_comment")
public class YztChoicenesscontentComment {
    @Id
    private String id;

    @Column(name = "yzt_choicenesscontent_id")
    private String yztChoicenesscontentId;

    @Column(name = "yzt_user_id")
    private String yztUserId;

    private String grade;

    private String content;

    /**
     * 是否是匿名 0 不是  1是
     */
    private Integer anonymity;

    /**
     * 0评论 1回复
     */
    private Integer commenttype;

    @Column(name = "pic_url")
    private String picUrl;

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
     * @return yzt_choicenesscontent_id
     */
    public String getYztChoicenesscontentId() {
        return yztChoicenesscontentId;
    }

    /**
     * @param yztChoicenesscontentId
     */
    public void setYztChoicenesscontentId(String yztChoicenesscontentId) {
        this.yztChoicenesscontentId = yztChoicenesscontentId;
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
     * @return grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
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
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * @param picUrl
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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