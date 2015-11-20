package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzt_dictionary")
public class YztDictionary {
    @Id
    private String id;

    @Column(name = "dic_name")
    private String dicName;

    @Column(name = "dic_code")
    private String dicCode;

    @Column(name = "dic_sequence")
    private Integer dicSequence;

    @Column(name = "dic_type")
    private Integer dicType;

    @Column(name = "dic_pid")
    private String dicPid;

    private Date create_time;

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
     * @return dic_name
     */
    public String getDicName() {
        return dicName;
    }

    /**
     * @param dicName
     */
    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    /**
     * @return dic_code
     */
    public String getDicCode() {
        return dicCode;
    }

    /**
     * @param dicCode
     */
    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    /**
     * @return dic_sequence
     */
    public Integer getDicSequence() {
        return dicSequence;
    }

    /**
     * @param dicSequence
     */
    public void setDicSequence(Integer dicSequence) {
        this.dicSequence = dicSequence;
    }

    /**
     * @return dic_type
     */
    public Integer getDicType() {
        return dicType;
    }

    /**
     * @param dicType
     */
    public void setDicType(Integer dicType) {
        this.dicType = dicType;
    }

    /**
     * @return dic_pid
     */
    public String getDicPid() {
        return dicPid;
    }

    /**
     * @param dicPid
     */
    public void setDicPid(String dicPid) {
        this.dicPid = dicPid;
    }



    public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
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