package com.yzt.app.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

@Table(name = "yzt_learningcenter")
public class YztLearningcenter {
    @Id
    private String id;

    /**
     * 早教中心名称
     */
    private String name;

    /**
     * 相当于parentid
     */
    @Column(name = "yzt_learningcenter_id")
    private String yztLearningcenterId;

    @Column(name = "branch_url")
    private String branchUrl;

    @Column(name = "branch_count")
    private Integer branchCount;

    @Column(name = "learningcenter_url")
    private String learningcenterUrl;

    /**
     * 分支名称
     */
    @Column(name = "branch_name")
    private String branchName;

    /**
     * 咨询电话（如有多个用 | 分隔）
     */
    @Column(name = "consult_phone")
    private String consultPhone;
    
    /**
     * 联系电话（如有多个用 | 分隔）
     */
    @Column(name = "contact_phone")
    private String contactPhone;
    
    /**
     * 营业时间（如果有多个 | 分隔）
     */
    @Column(name = "business_time")
    private String businessTime;

    /**
     * logo地址
     */
    @Column(name = "logo_url")
    private String logoUrl;

    /**
     * 地理位置
     */
    @Column(name = "geographic_position")
    private String geographicPosition;

    /**
     * 省
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 街道
     */
    private String street;

    /**
     * 邮编
     */
    private String postcode;

    /**
     * 建筑物
     */
    private String building;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;
    
    private int distance;
    
  
    @Column(name = "comments_count")
	public int commentsCount; 
    public double comments;
    
    @Column(name = "commodity_count")
   	public int commodityCount; 
	
    //图片list
    //public List<Map<String,Object>> piclist;
    //评论list
    //public List<YztLearningcenterComment> commentlist;
    //介绍html链接
    public String htmlurl;

    public String getHtmlurl() {
		return htmlurl;
	}

	public void setHtmlurl(String htmlurl) {
		this.htmlurl = htmlurl;
	}

	

	public int getCommodityCount() { 
		return commodityCount;
	}

	public void setCommodityCount(int commodityCount) {
		this.commodityCount = commodityCount;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	

    public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public double getComments() {
		return comments;
	}

	public void setComments(double comments) {
		this.comments = comments;
	}

	

	/**
     * 商圈
     */
    @Column(name = "businiss_circle")
    private String businissCircle;

    /**
     * 最小年龄
     */
    @Column(name = "min_age")
    private Integer minAge;

    /**
     * 最大年龄
     */
    @Column(name = "max_age")
    private Integer maxAge;

    /**
     * 分类
     */
    @Column(name = "edu_type")
    private String eduType;

    /**
     * 点击量
     */
    @Column(name = "view_count")
    private Integer viewCount;

    
    /**
     * 权重
     */
    private Integer weight;

    /**
     * 图片数量
     */
    @Column(name = "pic_count")
    private Integer picCount;

    /**
     * 视频数量
     */
    @Column(name = "video_count")
    private Integer videoCount;

    /**
     * 课程数量
     */
    @Column(name = "course_count")
    private Integer courseCount;

    private Integer status;
    
    /**
     * 被收藏的用户数量==========================================
     */
    private Integer colletedCount;

    public Integer getColletedCount() {
		return colletedCount;
	}

	public void setColletedCount(Integer colletedCount) {
		this.colletedCount = colletedCount;
	}



	/**
     * 早教中心类别0 未合作 1是合作
     */
    @Column(name = "learningcenter_type")
    private Integer learningcenterType;

    private Date createtime;

    private Date modfiytime;

    @Column(name = "create_userid")
    private String createUserid;

    @Column(name = "modify_userid")
    private String modifyUserid;

    private Integer isdelete;
    
    @Column(name = "img_url")
    private String imgUrl;
    
    public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTvVideourl() {
		return tvVideourl;
	}

	public void setTvVideourl(String tvVideourl) {
		this.tvVideourl = tvVideourl;
	}



	@Column(name = "tv_videourl")
    private String tvVideourl;

    /**
     * 简介
     */
    private String introduction;

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
     * 获取早教中心名称
     *
     * @return name - 早教中心名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置早教中心名称
     *
     * @param name 早教中心名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取相当于parentid
     *
     * @return yzt_learningcenter_id - 相当于parentid
     */
    public String getYztLearningcenterId() {
        return yztLearningcenterId;
    }

    /**
     * 设置相当于parentid
     *
     * @param yztLearningcenterId 相当于parentid
     */
    public void setYztLearningcenterId(String yztLearningcenterId) {
        this.yztLearningcenterId = yztLearningcenterId;
    }

    /**
     * @return branch_url
     */
    public String getBranchUrl() {
        return branchUrl;
    }

    /**
     * @param branchUrl
     */
    public void setBranchUrl(String branchUrl) {
        this.branchUrl = branchUrl;
    }

    /**
     * @return branch_count
     */
    public Integer getBranchCount() {
        return branchCount;
    }

    /**
     * @param branchCount
     */
    public void setBranchCount(Integer branchCount) {
        this.branchCount = branchCount;
    }

    /**
     * @return learningcenter_url
     */
    public String getLearningcenterUrl() {
        return learningcenterUrl;
    }

    /**
     * @param learningcenterUrl
     */
    public void setLearningcenterUrl(String learningcenterUrl) {
        this.learningcenterUrl = learningcenterUrl;
    }

    /**
     * 获取分支名称
     *
     * @return branch_name - 分支名称
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * 设置分支名称
     *
     * @param branchName 分支名称
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * 获取咨询电话（如有多个用 | 分隔）
     *
     * @return consult_phone - 咨询电话（如有多个用 | 分隔）
     */
    public String getConsultPhone() {
        return consultPhone;
    }

    /**
     * 设置咨询电话（如有多个用 | 分隔）
     *
     * @param consultPhone 咨询电话（如有多个用 | 分隔）
     */
    public void setConsultPhone(String consultPhone) {
        this.consultPhone = consultPhone;
    }

    /**
     * 获取营业时间（如果有多个 | 分隔）
     *
     * @return business_time - 营业时间（如果有多个 | 分隔）
     */
    public String getBusinessTime() {
        return businessTime;
    }

    /**
     * 设置营业时间（如果有多个 | 分隔）
     *
     * @param businessTime 营业时间（如果有多个 | 分隔）
     */
    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    /**
     * 获取logo地址
     *
     * @return logo_url - logo地址
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * 设置logo地址
     *
     * @param logoUrl logo地址
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
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
     * @param geographicPosition 地理位置
     */
    public void setGeographicPosition(String geographicPosition) {
        this.geographicPosition = geographicPosition;
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区
     *
     * @return district - 区
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置区
     *
     * @param district 区
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * 获取街道
     *
     * @return street - 街道
     */
    public String getStreet() {
        return street;
    }

    /**
     * 设置街道
     *
     * @param street 街道
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * 获取邮编
     *
     * @return postcode - 邮编
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * 设置邮编
     *
     * @param postcode 邮编
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * 获取建筑物
     *
     * @return building - 建筑物
     */
    public String getBuilding() {
        return building;
    }

    /**
     * 设置建筑物
     *
     * @param building 建筑物
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取纬度
     *
     * @return latitude - 纬度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取商圈
     *
     * @return businiss_circle - 商圈
     */
    public String getBusinissCircle() {
        return businissCircle;
    }

    /**
     * 设置商圈
     *
     * @param businissCircle 商圈
     */
    public void setBusinissCircle(String businissCircle) {
        this.businissCircle = businissCircle;
    }

    /**
     * 获取最小年龄
     *
     * @return min_age - 最小年龄
     */
    public Integer getMinAge() {
        return minAge;
    }

    /**
     * 设置最小年龄
     *
     * @param minAge 最小年龄
     */
    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    /**
     * 获取最大年龄
     *
     * @return max_age - 最大年龄
     */
    public Integer getMaxAge() {
        return maxAge;
    }

    /**
     * 设置最大年龄
     *
     * @param maxAge 最大年龄
     */
    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * 获取分类
     *
     * @return edu_type - 分类
     */
    public String getEduType() {
        return eduType;
    }

    /**
     * 设置分类
     *
     * @param eduType 分类
     */
    public void setEduType(String eduType) {
        this.eduType = eduType;
    }

    /**
     * 获取点击量
     *
     * @return view_count - 点击量
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * 设置点击量
     *
     * @param viewCount 点击量
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * 获取权重
     *
     * @return weight - 权重
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置权重
     *
     * @param weight 权重
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 获取图片数量
     *
     * @return pic_count - 图片数量
     */
    public Integer getPicCount() {
        return picCount;
    }

    /**
     * 设置图片数量
     *
     * @param picCount 图片数量
     */
    public void setPicCount(Integer picCount) {
        this.picCount = picCount;
    }

    /**
     * 获取视频数量
     *
     * @return video_count - 视频数量
     */
    public Integer getVideoCount() {
        return videoCount;
    }

    /**
     * 设置视频数量
     *
     * @param videoCount 视频数量
     */
    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }

    /**
     * 获取课程数量
     *
     * @return course_count - 课程数量
     */
    public Integer getCourseCount() {
        return courseCount;
    }

    /**
     * 设置课程数量
     *
     * @param courseCount 课程数量
     */
    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
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
     * 获取早教中心类别0 未合作 1是合作
     *
     * @return learningcenter_type - 早教中心类别0 未合作 1是合作
     */
    public Integer getLearningcenterType() {
        return learningcenterType;
    }

    /**
     * 设置早教中心类别0 未合作 1是合作
     *
     * @param learningcenterType 早教中心类别0 未合作 1是合作
     */
    public void setLearningcenterType(Integer learningcenterType) {
        this.learningcenterType = learningcenterType;
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
     * 获取简介
     *
     * @return introduction - 简介
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置简介
     *
     * @param introduction 简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    
    
}