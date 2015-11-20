package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzt_userinfo")
public class YztUserinfo {
    @Id
    private String id;

    /**
     * 账号
     */
    @Column(name = "account")
    private String account;
    
    /**
     * 密码
     */
    @Column(name = "password")
    private String password;
    public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 关联系统用户信息一对一
     */
    @Column(name = "back_user_id")
    private String backUserId;

    /**
     * 是否禁用 0 否 1是
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 图片路径
     */
    @Column(name = "picurl")
    private String picurl;

    @Column(name = "system_msg_switch")
    private Integer systemMsgSwitch;

    @Column(name = "choiceness_msg_switch")
    private Integer choicenessMsgSwitch;

    @Column(name = "`change_msg_ switch`")
    private Integer changeMsgSwitch;

    /**
     * 当前位置
     */
    private String location;

    /**
     * 当前糖果积分
     */
    @Column(name = "integral_curr")
    private Integer integralCurr;

    /**
     * 赚取的总分
     */
    @Column(name = "integral_earn")
    private Integer integralEarn;

    /**
     * 花费的总分
     */
    @Column(name = "integral_cost")
    private Integer integralCost;

    /**
     * 是否商户0-否，1-是
     */
    @Column(name = "is_merchants")
    private Integer isMerchants;
    
    /**
     * 宝宝数量
     */
    @Column(name = "baby_count")
    private Integer babyCount;

    /**
     * 早教中心id，一对一
     */
    @Column(name = "yzt_learningcenter_id")
    private String yztLearningcenterId;

    private Date createtime;

    private Integer isdelete;

    private Date modfiytime;

    @Column(name = "create_userid")
    private String createUserid;

    @Column(name = "modify_userid")
    private String modifyUserid;
    
    private String verifycode;
  
    /**
     * 用户收藏精选的数量
     */
    private Integer collectContentCount;
  

	public Integer getCollectContentCount() {
		return collectContentCount;
	}

	public void setCollectContentCount(Integer collectContentCount) {
		this.collectContentCount = collectContentCount;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
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
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取关联系统用户信息一对一
     *
     * @return back_user_id - 关联系统用户信息一对一
     */
    public String getBackUserId() {
        return backUserId;
    }

    /**
     * 设置关联系统用户信息一对一
     *
     * @param backUserId 关联系统用户信息一对一
     */
    public void setBackUserId(String backUserId) {
        this.backUserId = backUserId;
    }

    /**
     * 获取是否禁用 0 否 1是
     *
     * @return status - 是否禁用 0 否 1是
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否禁用 0 否 1是
     *
     * @param status 是否禁用 0 否 1是
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取图片路径
     *
     * @return picurl - 图片路径
     */
    public String getPicurl() {
        return picurl;
    }

    /**
     * 设置图片路径
     *
     * @param picurl 图片路径
     */
    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    /**
     * @return system_msg_switch
     */
    public Integer getSystemMsgSwitch() {
        return systemMsgSwitch;
    }

    /**
     * @param systemMsgSwitch
     */
    public void setSystemMsgSwitch(Integer systemMsgSwitch) {
        this.systemMsgSwitch = systemMsgSwitch;
    }

    /**
     * @return choiceness_msg_switch
     */
    public Integer getChoicenessMsgSwitch() {
        return choicenessMsgSwitch;
    }

    /**
     * @param choicenessMsgSwitch
     */
    public void setChoicenessMsgSwitch(Integer choicenessMsgSwitch) {
        this.choicenessMsgSwitch = choicenessMsgSwitch;
    }

    /**
     * @return change_msg_ switch
     */
    public Integer getChangeMsgSwitch() {
        return changeMsgSwitch;
    }

    /**
     * @param changeMsgSwitch
     */
    public void setChangeMsgSwitch(Integer changeMsgSwitch) {
        this.changeMsgSwitch = changeMsgSwitch;
    }

    /**
     * 获取当前位置
     *
     * @return location - 当前位置
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置当前位置
     *
     * @param location 当前位置
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取当前糖果积分
     *
     * @return integral_curr - 当前糖果积分
     */
    public Integer getIntegralCurr() {
        return integralCurr;
    }

    /**
     * 设置当前糖果积分
     *
     * @param integralCurr 当前糖果积分
     */
    public void setIntegralCurr(Integer integralCurr) {
        this.integralCurr = integralCurr;
    }

    /**
     * 获取赚取的总分
     *
     * @return integral_earn - 赚取的总分
     */
    public Integer getIntegralEarn() {
        return integralEarn;
    }

    /**
     * 设置赚取的总分
     *
     * @param integralEarn 赚取的总分
     */
    public void setIntegralEarn(Integer integralEarn) {
        this.integralEarn = integralEarn;
    }

    /**
     * 获取花费的总分
     *
     * @return integral_cost - 花费的总分
     */
    public Integer getIntegralCost() {
        return integralCost;
    }

    /**
     * 设置花费的总分
     *
     * @param integralCost 花费的总分
     */
    public void setIntegralCost(Integer integralCost) {
        this.integralCost = integralCost;
    }

    /**
     * 获取是否商户0-否，1-是
     *
     * @return is_merchants - 是否商户0-否，1-是
     */
    public Integer getIsMerchants() {
        return isMerchants;
    }

    /**
     * 设置是否商户0-否，1-是
     *
     * @param isMerchants 是否商户0-否，1-是
     */
    public void setIsMerchants(Integer isMerchants) {
        this.isMerchants = isMerchants;
    }

    public Integer getBabyCount() {
		return babyCount;
	}

	public void setBabyCount(Integer babyCount) {
		this.babyCount = babyCount;
	}

	/**
     * 获取早教中心id，一对一
     *
     * @return yzt_learningcenter_id - 早教中心id，一对一
     */
    public String getYztLearningcenterId() {
        return yztLearningcenterId;
    }

    /**
     * 设置早教中心id，一对一
     *
     * @param yztLearningcenterId 早教中心id，一对一
     */
    public void setYztLearningcenterId(String yztLearningcenterId) {
        this.yztLearningcenterId = yztLearningcenterId;
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
}