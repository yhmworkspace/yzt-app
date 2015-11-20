package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;
/**
 *	用于记录用户所收藏的精选内容记录                                                                                                                                                                                                                                 
 * @author Administrator
 *
 */
@Table(name = "yzt_choicenesscontent_collect")
public class YztChoicenesscontentCollect {
    @Id
    private String id;
    //关联用户
    @Column(name = "yzt_user_id")
    private String yztUserId;
    //关联早教中心
    @Column(name = "yzt_choicenesscontent_id")
    private String yztChoicenesscontentId;
    //收藏（0取消收藏1收藏）
    private Integer collect;
    //创建时间   
    private Date createtime;
    //修改时间
    private Date modfiytime;
    //生成客户id
    @Column(name = "create_userid")
    private String createUserid;
    //修改客户id
    @Column(name = "modify_userid")
    private String modifyUserid;
    //是否删除
    private Integer isdelete;
    
	//精选内容的标题
    private String yztChoicenesscontentMaintitle;

    //精选生效时间
    private String yztChoicenesscontentStartTime;
    
	//精选失效时间
	private String yztChoicenesscontentEndTime;
    
    //用户的昵称
    private String yztUserinfoNickname;

	//用户账号
	private String yztUserinfoAccount;
  
	public String getYztUserinfoAccount() {
		return yztUserinfoAccount;
	}

	public void setYztUserinfoAccount(String yztUserinfoAccount) {
		this.yztUserinfoAccount = yztUserinfoAccount;
	}

	public String getYztChoicenesscontentStartTime() {
		return yztChoicenesscontentStartTime;
	}

	public void setYztChoicenesscontentStartTime(
			String yztChoicenesscontentStartTime) {
		this.yztChoicenesscontentStartTime = yztChoicenesscontentStartTime;
	}

	public String getYztChoicenesscontentEndTime() {
		return yztChoicenesscontentEndTime;
	}

	public void setYztChoicenesscontentEndTime(String yztChoicenesscontentEndTime) {
		this.yztChoicenesscontentEndTime = yztChoicenesscontentEndTime;
	}


	/**
     * 获取精选内容的标题
     * @return
     */
    public String getYztChoicenesscontentMaintitle() {
		return yztChoicenesscontentMaintitle;
	}

    /**
	 * 设置精选内容的标题
	 * @param yztChoicenesscontentMaintitle
	 */
	public void setYztChoicenesscontentMaintitle(
			String yztChoicenesscontentMaintitle) {
		this.yztChoicenesscontentMaintitle = yztChoicenesscontentMaintitle;
	}
	
	/**
	 * 获取用户的昵称
	 * @return
	 */
	public String getYztUserinfoNickname() {
		return yztUserinfoNickname;
	}
	
	/**
	 * 设置用户的昵称
	 * @param yztUserinfoNickname
	 */
	public void setYztUserinfoNickname(String yztUserinfoNickname) {
		this.yztUserinfoNickname = yztUserinfoNickname;
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
     * @return collect
     */
    public Integer getCollect() {
        return collect;
    }

    /**
     * @param collect
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