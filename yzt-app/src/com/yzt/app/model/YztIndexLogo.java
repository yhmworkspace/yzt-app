package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzt_index_logo")
public class YztIndexLogo {
    @Id
    private String id;

    /**
     * 早教中心关键字
     */
    private String keyword;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "end_time")
    private String endTime;

    private Integer status;

    private Integer weight;

    private Date createtime;

   

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
     * 获取早教中心关键字
     *
     * @return keyword - 早教中心关键字
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置早教中心关键字
     *
     * @param keyword 早教中心关键字
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
     * @return logo_url
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * @param logoUrl
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
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
     * @return weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
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