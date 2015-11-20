package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzt_archivelog")
public class YztArchivelog {
    @Id
    private String id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_id")
    private String userId;

    private String operation;

    @Column(name = "archive_id")
    private String archiveId;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "field_id")
    private String fieldId;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "field_cn_name")
    private String fieldCnName;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "operation_date")
    private String operationDate;

    private Date createtime;

    private Date modfiytime;

    @Column(name = "create_userid")
    private String createUserid;

    @Column(name = "modify_userid")
    private String modifyUserid;

    private Integer isdelete;

    @Column(name = "new_value")
    private String newValue;

    @Column(name = "old_value")
    private String oldValue;

    private String note;

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
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * @return archive_id
     */
    public String getArchiveId() {
        return archiveId;
    }

    /**
     * @param archiveId
     */
    public void setArchiveId(String archiveId) {
        this.archiveId = archiveId;
    }

    /**
     * @return category_id
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return field_id
     */
    public String getFieldId() {
        return fieldId;
    }

    /**
     * @param fieldId
     */
    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * @return field_name
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @param fieldName
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * @return field_cn_name
     */
    public String getFieldCnName() {
        return fieldCnName;
    }

    /**
     * @param fieldCnName
     */
    public void setFieldCnName(String fieldCnName) {
        this.fieldCnName = fieldCnName;
    }

    /**
     * @return category_name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return operation_date
     */
    public String getOperationDate() {
        return operationDate;
    }

    /**
     * @param operationDate
     */
    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
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

    /**
     * @return new_value
     */
    public String getNewValue() {
        return newValue;
    }

    /**
     * @param newValue
     */
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    /**
     * @return old_value
     */
    public String getOldValue() {
        return oldValue;
    }

    /**
     * @param oldValue
     */
    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    /**
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }
}