package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzt_integral_task")
public class YztIntegralTask {
    @Id
    private String id;

    /**
     * 每日任务
     */
    @Column(name = "task_type")
    private Integer taskType;

    /**
     * 名称
     */
    private String name;

    /**
     * 糖果数量（奖励糖果数量）
     */
    @Column(name = "integral_count")
    private Integer integralCount;
    
    /**
     * 糖果数量（奖励糖果数量）
     */
    @Column(name = "integral_value")
    private Integer integralValue;

    /**
     * 截至时间
     */
    private String endtime;

    private Date createtime;

    private Date modfiytime;

    @Column(name = "create_userid")
    private String createUserid;

    @Column(name = "modify_userid")
    private String modifyUserid;

    private Integer isdelete;
    
    private Integer iscomplete;

    public Integer getIscomplete() {
		return iscomplete;
	}

	public void setIscomplete(Integer iscomplete) {
		this.iscomplete = iscomplete;
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
     * 获取每日任务
     *
     * @return task_type - 每日任务
     */
    public Integer getTaskType() {
        return taskType;
    }

    /**
     * 设置每日任务
     *
     * @param taskType 每日任务
     */
    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取糖果数量（奖励糖果数量）
     *
     * @return integral_count - 糖果数量（奖励糖果数量）
     */
    public Integer getIntegralValue() {
        return integralValue;
    }

    /**
     * 设置糖果数量（奖励糖果数量）
     *
     * @param integralCount 糖果数量（奖励糖果数量）
     */
    public void setIntegralValue(Integer integralValue) {
        this.integralValue = integralValue;
    }

    /**
     * 获取糖果数量（奖励糖果数量）
     *
     * @return integral_count - 糖果数量（奖励糖果数量）
     */
    public Integer getIntegralCount() {
        return integralCount;
    }

    /**
     * 设置糖果数量（奖励糖果数量）
     *
     * @param integralCount 糖果数量（奖励糖果数量）
     */
    public void setIntegralCount(Integer integralCount) {
        this.integralCount = integralCount;
    }

    /**
     * 获取截至时间
     *
     * @return endtime - 截至时间
     */
    public String getEndtime() {
        return endtime;
    }

    /**
     * 设置截至时间
     *
     * @param endtime 截至时间
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
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