package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzt_commodity_exchange")
public class YztCommodityExchange {
    @Id
    private String id;

    @Column(name = "yzt_cdkey_id")
    private String yztCdkeyId;

    @Column(name = "yzt_commodity_id")
    private String yztCommodityId;

    @Column(name = "yzt_user_id")
    private String yztUserId;

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
     * @return yzt_cdkey_id
     */
    public String getYztCdkeyId() {
        return yztCdkeyId;
    }

    /**
     * @param yztCdkeyId
     */
    public void setYztCdkeyId(String yztCdkeyId) {
        this.yztCdkeyId = yztCdkeyId;
    }

    /**
     * @return yzt_commodity_id
     */
    public String getYztCommodityId() {
        return yztCommodityId;
    }

    /**
     * @param yztCommodityId
     */
    public void setYztCommodityId(String yztCommodityId) {
        this.yztCommodityId = yztCommodityId;
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