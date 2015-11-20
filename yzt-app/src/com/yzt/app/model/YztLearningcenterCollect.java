package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzt_learningcenter_collect")
public class YztLearningcenterCollect {
    @Id
    private String id;

    @Column(name = "yzt_user_id")
    private String yztUserId;

    @Column(name = "yzt_learningcenter_id")
    private String yztLearningcenterId;

    /**
     * 0取消收藏1收藏
     */
    private Integer collect;

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
     * 获取0取消收藏1收藏
     *
     * @return collect - 0取消收藏1收藏
     */
    public Integer getCollect() {
        return collect;
    }

    /**
     * 设置0取消收藏1收藏
     *
     * @param collect 0取消收藏1收藏
     */
    public void setCollect(Integer collect) {
        this.collect = collect;
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