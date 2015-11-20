package com.yzt.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.yzt.app.service.BabyService;
import com.yzt.app.service.CdkeyService;
import com.yzt.app.service.ChoicenesscontentCollectService;
import com.yzt.app.service.ChoicenesscontentCommentService;
import com.yzt.app.service.ChoicenesscontentPicService;
import com.yzt.app.service.ChoicenesscontentService;
import com.yzt.app.service.CommodityService;
import com.yzt.app.service.DictionaryService;
import com.yzt.app.service.FeedbackService;
import com.yzt.app.service.IndexChoicenesscontentService;
import com.yzt.app.service.IndexLogoService;
import com.yzt.app.service.IndexPicService;
import com.yzt.app.service.IndexVideoService;
import com.yzt.app.service.IntegralService;
import com.yzt.app.service.LearningcenterCollectService;
import com.yzt.app.service.LearningcenterCommentPicService;
import com.yzt.app.service.LearningcenterCommentService;
import com.yzt.app.service.LearningcenterCourseService;
import com.yzt.app.service.LearningcenterPicService;
import com.yzt.app.service.LearningcenterService;
import com.yzt.app.service.LearningcenterVideoService;
import com.yzt.app.service.MessageService;
import com.yzt.app.service.RegistService;
import com.yzt.app.service.TaskService;
import com.yzt.app.service.UserMessageService;
import com.yzt.app.service.UserinfoService;

public class BaseController extends MultiActionController{
	/*@Autowired
	protected UploadVideoRunnable uploadVideoRunnable;*/
	
	@Autowired
	protected ChoicenesscontentCollectService choicenesscontentCollectService;
	
	@Autowired
	protected UserinfoService userinfoService;
	
	@Autowired
	protected DictionaryService dictionaryService;
	
	@Autowired
	protected LearningcenterService learningcenterService;
	
	@Autowired
	protected RegistService registService;
	
	@Autowired
	protected BabyService babyService;
	
	@Autowired
	protected CommodityService commodityService;
	
	@Autowired
	protected CdkeyService cdkeyService;
	
	@Autowired
	protected TaskService taskService;
	
	@Autowired
	protected IntegralService integralService;
	
	@Autowired
	protected LearningcenterPicService learningcenterPicService;
	
	@Autowired
	protected LearningcenterVideoService learningcenterVideoService;
	
	@Autowired
	protected LearningcenterCourseService learningcenterCourseService;
	
	@Autowired
	protected LearningcenterCommentService learningcenterCommentService;
	
	@Autowired
	protected LearningcenterCommentPicService learningcenterCommentPicService;
	
	@Autowired
	protected FeedbackService feedbackService;
	
	@Autowired
	protected MessageService messageService;
	
	@Autowired
	protected UserMessageService usermessageService;
	
	@Autowired 
	protected ChoicenesscontentService choicenesscontentService;
	
	@Autowired 
	protected ChoicenesscontentPicService choicenesscontentPicService;
	
	@Autowired 
	protected ChoicenesscontentCommentService choicenesscontentCommentService;
	
	@Autowired 
	protected IndexLogoService indexLogoService;
	
	@Autowired 
	protected IndexPicService indexPicService;
	@Autowired 
	protected IndexVideoService indexVideoService;
	@Autowired 
	protected IndexChoicenesscontentService indexChoicenesscontentService;
	@Autowired
	protected LearningcenterCollectService learningcenterCollectService;
	

}
