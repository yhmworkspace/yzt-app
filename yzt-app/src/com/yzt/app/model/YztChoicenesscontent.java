package com.yzt.app.model;

import java.util.Date;

import javax.persistence.*;

/**
 * 用于精选内容信息
 * 
 * @author Administrator
 *
 */
@Table(name = "yzt_choicenesscontent")
public class YztChoicenesscontent {
	@Id
	private String id;

	@Column(name = "choicenesscontent_id")
	private String choicenesscontentId;

	public String getChoicenesscontentId() {
		return choicenesscontentId;
	}

	public void setChoicenesscontentId(String choicenesscontentId) {
		this.choicenesscontentId = choicenesscontentId;
	}

	/**
	 * 标题
	 */
	private String maintitle;

	@Column(name = "contact_phone")
	private String contactPhone;

	/**
	 * url地址
	 */
	@Column(name = "logo_url")
	private String logoUrl;

	/**
	 * 生效时间
	 */
	@Column(name = "start_time")
	private String startTime;

	/**
	 * 失效时间
	 */
	@Column(name = "end_time")
	private String endTime;

	/**
	 * 地理位置
	 */
	@Column(name = "geographic_position")
	private String geographicPosition;

	private String province;

	private String city;

	private String district;

	private String street;

	private String building;

	private String longitude;

	private String latitude;

	@Column(name = "businiss_circle")
	private String businissCircle;

	@Column(name = "business_time")
	private String businessTime;

	public String getBusinessTime() {
		return businessTime;
	}

	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}

	@Column(name = "min_age")
	private Integer minAge;

	@Column(name = "max_age")
	private Integer maxAge;

	@Column(name = "edu_type")
	private String eduType;

	@Column(name = "view_count")
	private Integer viewCount;

	private Integer weight;

	@Column(name = "pic_count")
	private Integer picCount;

	private Date createtime;

	private Date modfiytime;

	@Column(name = "create_userid")
	private String createUserid;

	@Column(name = "modify_userid")
	private String modifyUserid;

	private Integer isdelete;

	private String introduction;

	private double comments;
	@Column(name = "comments_count")
	private int commentsCount;

	/**
	 * 被用户收藏的次数
	 */
	private Integer collectedCount;
	/**
	 * 存放内容编辑的URL
	 */
	private String introductionHtmlUrl;

	public String getIntroductionHtmlUrl() {
		return introductionHtmlUrl;
	}

	public void setIntroductionHtmlUrl(String introductionHtmlUrl) {
		this.introductionHtmlUrl = introductionHtmlUrl;
	}

	public Integer getCollectedCount() {
		return collectedCount;
	}

	public void setCollectedCount(Integer collectedCount) {
		this.collectedCount = collectedCount;
	}

	public double getComments() {
		return comments;
	}

	public void setComments(double comments) {
		this.comments = comments;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
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
	 * @return maintitle - 标题
	 */
	public String getMaintitle() {
		return maintitle;
	}

	/**
	 * 设置标题
	 *
	 * @param maintitle
	 *            标题
	 */
	public void setMaintitle(String maintitle) {
		this.maintitle = maintitle;
	}

	/**
	 * @return contact_phone
	 */
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * @param contactPhone
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/**
	 * 获取url地址
	 *
	 * @return logo_url - url地址
	 */
	public String getLogoUrl() {
		return logoUrl;
	}

	/**
	 * 设置url地址
	 *
	 * @param logoUrl
	 *            url地址
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	/**
	 * 获取生效时间
	 *
	 * @return start_time - 生效时间
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 设置生效时间
	 *
	 * @param startTime
	 *            生效时间
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取失效时间
	 *
	 * @return end_time - 失效时间
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 设置失效时间
	 *
	 * @param endTime
	 *            失效时间
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取地理位置
	 *
	 * @return geographic_position - 地理位置
	 */
	public String getGeographicPosition() {
		return geographicPosition;
	}

	/**
	 * 设置地理位置
	 *
	 * @param geographicPosition
	 *            地理位置
	 */
	public void setGeographicPosition(String geographicPosition) {
		this.geographicPosition = geographicPosition;
	}

	/**
	 * @return province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return building
	 */
	public String getBuilding() {
		return building;
	}

	/**
	 * @param building
	 */
	public void setBuilding(String building) {
		this.building = building;
	}

	/**
	 * @return longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return businiss_circle
	 */
	public String getBusinissCircle() {
		return businissCircle;
	}

	/**
	 * @param businissCircle
	 */
	public void setBusinissCircle(String businissCircle) {
		this.businissCircle = businissCircle;
	}

	/**
	 * @return min_age
	 */
	public Integer getMinAge() {
		return minAge;
	}

	/**
	 * @param minAge
	 */
	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	/**
	 * @return max_age
	 */
	public Integer getMaxAge() {
		return maxAge;
	}

	/**
	 * @param maxAge
	 */
	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	/**
	 * @return edu_type
	 */
	public String getEduType() {
		return eduType;
	}

	/**
	 * @param eduType
	 */
	public void setEduType(String eduType) {
		this.eduType = eduType;
	}

	/**
	 * @return view_count
	 */
	public Integer getViewCount() {
		return viewCount;
	}

	/**
	 * @param viewCount
	 */
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
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
	 * @return pic_count
	 */
	public Integer getPicCount() {
		return picCount;
	}

	/**
	 * @param picCount
	 */
	public void setPicCount(Integer picCount) {
		this.picCount = picCount;
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
	 * @return introduction
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * @param introduction
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}