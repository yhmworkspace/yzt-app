package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzt_message")
public class YztMessage {
    @Id
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    private Integer msgtype;

    /**
     * 连接地址
     */
    private String linkurl;

    /**
     * 发送时间
     */
    private Date sendtime;

    private Integer isread;

    /**
     * 发送类型 0及时发送 1定时发送
     */
    private Integer sendtype;

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
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return msgtype
     */
    public Integer getMsgtype() {
        return msgtype;
    }

    /**
     * @param msgtype
     */
    public void setMsgtype(Integer msgtype) {
        this.msgtype = msgtype;
    }

    /**
     * 获取连接地址
     *
     * @return linkurl - 连接地址
     */
    public String getLinkurl() {
        return linkurl;
    }

    /**
     * 设置连接地址
     *
     * @param linkurl 连接地址
     */
    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    /**
     * 获取发送时间
     *
     * @return sendtime - 发送时间
     */
    public Date getSendtime() {
        return sendtime;
    }

    /**
     * 设置发送时间
     *
     * @param sendtime 发送时间
     */
    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    /**
     * @return isread
     */
    public Integer getIsread() {
        return isread;
    }

    /**
     * @param isread
     */
    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    /**
     * 获取发送类型 0及时发送 1定时发送
     *
     * @return sendtype - 发送类型 0及时发送 1定时发送
     */
    public Integer getSendtype() {
        return sendtype;
    }

    /**
     * 设置发送类型 0及时发送 1定时发送
     *
     * @param sendtype 发送类型 0及时发送 1定时发送
     */
    public void setSendtype(Integer sendtype) {
        this.sendtype = sendtype;
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