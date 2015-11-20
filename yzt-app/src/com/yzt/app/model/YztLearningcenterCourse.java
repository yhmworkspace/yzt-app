package com.yzt.app.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yzt_learningcenter_course")
public class YztLearningcenterCourse {
    @Id
    private String id;

    @Column(name = "yzt_learningcenter_id")
    private String yztLearningcenterId;

    /**
     * 课程名称
     */
    private String name;

    private String stage;
    
    @Column(name = "is_type")
    private String isType;
    
    @Column(name = "is_sequence")
    private int isSequence;
    
    public int getIsSequence() {
		return isSequence;
	}

	public void setIsSequence(int isSequence) {
		this.isSequence = isSequence;
	}

	@Column(name = "pic_count")
    private int picCount;
    
    @Column(name = "video_count")
    private int videoCount;
    @Column(name = "course_count")
    private int courseCount;
    
    private String introduction;

    public int getCourseCount() {
		return courseCount;
	}

	public void setCourseCount(int courseCount) {
		this.courseCount = courseCount;
	}

	public String getIsType() {
		return isType;
	}

	public void setIsType(String isType) {
		this.isType = isType;
	}

	public int getPicCount() {
		return picCount;
	}

	public void setPicCount(int picCount) {
		this.picCount = picCount;
	}

	public int getVideoCount() {
		return videoCount;
	}

	public void setVideoCount(int videoCount) {
		this.videoCount = videoCount;
	}

	/**
     * 年龄
     */
    private String age;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    private String startTime;

    /**
     * 结束时间通过开始时间加上课时时长计算得出
     */
    @Column(name = "end_time")
    private String endTime;

    /**
     * 课时时长
     */
    private String attendclasstime;

    /**
     * 星期
     */
    private String week;

    private String teacher;

    private String picurl;

    private Date createtime;

    private Date modfiytime;
    
    /**
     * 父课程
     */
    private String parentid;

    public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

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
     * @return yzt_learningcenter_id
     */
    public String getYztLearningcenterId() {
        return yztLearningcenterId;
    }

    /**
     * @param yztLearningcenterId
     */
    public void setYztLearningcenterId(String yztLearningcenterId) {
        this.yztLearningcenterId = yztLearningcenterId;
    }

    /**
     * 获取课程名称
     *
     * @return name - 课程名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置课程名称
     *
     * @param name 课程名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return stage
     */
    public String getStage() {
        return stage;
    }

    /**
     * @param stage
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public String getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * 获取开始时间
     *
     * @return start_time - 开始时间
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间通过开始时间加上课时时长计算得出
     *
     * @return end_time - 结束时间通过开始时间加上课时时长计算得出
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间通过开始时间加上课时时长计算得出
     *
     * @param endTime 结束时间通过开始时间加上课时时长计算得出
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取课时时长
     *
     * @return attendclasstime - 课时时长
     */
    public String getAttendclasstime() {
        return attendclasstime;
    }

    /**
     * 设置课时时长
     *
     * @param attendclasstime 课时时长
     */
    public void setAttendclasstime(String attendclasstime) {
        this.attendclasstime = attendclasstime;
    }

    /**
     * 获取星期
     *
     * @return week - 星期
     */
    public String getWeek() {
        return week;
    }

    /**
     * 设置星期
     *
     * @param week 星期
     */
    public void setWeek(String week) {
        this.week = week;
    }

    /**
     * @return teacher
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * @param teacher
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    /**
     * @return picurl
     */
    public String getPicurl() {
        return picurl;
    }

    /**
     * @param picurl
     */
    public void setPicurl(String picurl) {
        this.picurl = picurl;
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
}