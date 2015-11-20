package com.yzt.app.model;

import java.util.Date;

import javax.persistence.*;

/**
 * 修改（yhm）
 * 
 * @author Administrator
 *
 */
@Table(name = "yzt_learningcenter_video")
public class YztLearningcenterVideo {
	@Id
	private String id;

	@Column(name = "yzt_learningcenter_id")
	private String yztLearningcenterId;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_type")
	private String fileType;

	@Column(name = "file_path")
	private String filePath;

	@Column(name = "file_size")
	private Integer fileSize;
	// 按照顺序
	private Integer sequence;

	@Column(name = "file_contenttype")
	private String fileContenttype;

	@Column(name = "course_id")
	private String courseId;

	private Date createtime;

	private Date modfiytime;

	@Column(name = "create_userid")
	private String createUserid;

	@Column(name = "modify_userid")
	private String modifyUserid;

	private Integer isdelete;
	private Integer ispic;
	/**
	 * 云端id
	 */
	//private String videoId;
	/**
	 * 标题
	 */
	//private String title;
	/**
	 * 视频描述
	 */
	//private String description;
	/**
	 * 是否上传云端
	 */
	//private String status;
	/**
	 * 是否检查
	 */
	//private Integer checked;
/*
	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}*/

	public Integer getIspic() {
		return ispic;
	}

	public void setIspic(Integer ispic) {
		this.ispic = ispic;
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
	 * @return file_name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return file_type
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return file_path
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return file_size
	 */
	public Integer getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize
	 */
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return sequence
	 */
	public Integer getSequence() {
		return sequence;
	}

	/**
	 * @param sequence
	 */
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return file_contenttype
	 */
	public String getFileContenttype() {
		return fileContenttype;
	}

	/**
	 * @param fileContenttype
	 */
	public void setFileContenttype(String fileContenttype) {
		this.fileContenttype = fileContenttype;
	}

	/**
	 * @return course_id
	 */
	public String getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
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

	/*public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
*/
}