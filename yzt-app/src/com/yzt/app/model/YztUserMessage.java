package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzt_user_message")
public class YztUserMessage {
    @Id
    private String id;

    
    private String title;

    /**
     * 内容
     */
    private String content;


    /**
     * 连接地址
     */
    private String linkurl;




    private Date createtime;


    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "is_read")
    private Integer isRead;


    public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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


}