package com.yzt.app.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.yzt.app.config.YztConstant;
import com.yzt.app.config.sms.Demo_Client;
import com.yzt.app.mapper.YztUserinfoMapper;
import com.yzt.app.model.YztRegistor;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.service.RegistService;
import com.yzt.app.service.UserinfoService;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.mymapper.YztMapper;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.FieldFilter;
import com.yzt.app.utils.web.Json;

@Service("userinfoService")
public class UserinfoServiceImpl extends BaseServiceImpl<YztUserinfo> implements UserinfoService {
	@Autowired
    protected YztUserinfoMapper mapper;
	@Autowired
	RegistService registService;

	@Override
	public EasyuiDataGridJson datagrid(YztUserinfo userinfo, EasyuiDataGrid dg) {

		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		
		Example example = new Example(YztUserinfo.class);
	    Example.Criteria criteria = example.createCriteria();
	    String filterRules = dg.getFilterRules();
	    if(filterRules !=null && !"[]".equals(filterRules)){ 
			List<FieldFilter> filtersr =  JSON.parseArray(filterRules,FieldFilter.class);
			for(FieldFilter ft : filtersr){
	        	//postparam.append(" and "+ft.getField()+" like '%"+ft.getValue().trim().replace(" ", "%")+"%'  ");
	        	criteria.andLike(ft.getField(), "%" + ft.getValue().trim().replaceAll(" ", "%") + "%");
			}
		}
	  //加入排序
        if(dg.getSort() != null && dg.getOrder() !=null){
        	String orderby="";
        	String[] order = dg.getSort().split(",");
        	String[] sort = dg.getOrder().split(",");
        	for(int i =0;i<order.length;i++){
        		orderby += order[i]+" "+sort[i]+",";
        	}
        	orderby = StringUtils.substringBeforeLast(orderby, ",");
        	 example.setOrderByClause(orderby);
        }
	    //查询总数
	    listjson.setTotal( selectCountByExample(example));
	    //分页查询
	    PageHelper.startPage(dg.getPage(), dg.getRows());
	    //return selectByExample(example);
	   // List<YztLearningcenter>
	   
		listjson.setRows(selectByExample(example));

		return listjson;
	}

	@Override
	public Json login(YztUserinfo userinfo) {
		Json json = new Json();
		if(userinfo.getAccount().equals(null) || userinfo.getPassword().equals(null) || userinfo.getAccount().isEmpty() || userinfo.getPassword().isEmpty()){
			json.setSuccess(false);
			json.setMsg(YztConstant.LOGIN_NOTNULL);
		}else if(YztUtil.isMobileNO(userinfo.getAccount())){
			Example example = new Example(YztUserinfo.class);
		    Example.Criteria criteria = example.createCriteria();
		    criteria.andEqualTo("account", userinfo.getAccount());
		    List<YztUserinfo> list = selectByExample(example);
		    if(list.size()==1){
		    	for(YztUserinfo user : list){
		    		if(user.getStatus() !=null && user.getStatus()==1){
		    			json.setSuccess(false);
				    	json.setMsg(YztConstant.LOGIN_ACCOUNT_BAN);
			    	}else{
			    		if(user.getPassword().equals(userinfo.getPassword())){
			    			json.setSuccess(true);
					    	json.setMsg(YztConstant.LOGIN_SUCCESS);
					    	json.setObj(user);
			    		}else{
			    			json.setSuccess(false);
			    			json.setMsg(YztConstant.LOGIN_ERR);
			    		}
			    	}
		    	}
		    	
		    }else{
		    	json.setSuccess(false);
		    	json.setMsg(YztConstant.LOGIN_ACCOUNT_NULL);
		    }
		}else{
			json.setSuccess(false);
	    	json.setMsg(YztConstant.MSG_MOBILE_ERR);
		}
		
		return json;
	}
	
	@Override
	public Json updatePWD(YztUserinfo userinfo,String password_new,String password_re) {
		Json json = new Json();
		if(userinfo.getPassword().isEmpty() || password_new.isEmpty() || password_re.isEmpty()){
			json.setSuccess(false);
			json.setMsg(YztConstant.UP_PWD_ALLNULL);
		}else if(!password_new.equals(password_re)){
			json.setSuccess(false);
			json.setMsg(YztConstant.UP_PWD_NOTEQUAL);
		}else{
			System.out.println("==1="+userinfo.getId());
			Example example = new Example(YztUserinfo.class);
		    Example.Criteria criteria = example.createCriteria();
		    criteria.andEqualTo("id",userinfo.getId());
		    criteria.andEqualTo("account", userinfo.getAccount());
		    criteria.andEqualTo("password",userinfo.getPassword());
		    
		    List<YztUserinfo> list = selectByExample(example);
		    if(list.size()==1){
		    	for(YztUserinfo user : list){
		    		if(user.getStatus() !=null && user.getStatus()==1){
		    			json.setSuccess(false);
				    	json.setMsg(YztConstant.LOGIN_ACCOUNT_BAN);
			    	}else{
			    		YztUserinfo user_ = new YztUserinfo();
			    		user_.setId(user.getId());
			    		user_.setAccount(user.getAccount());
			    		user_.setPassword(password_new);
			    		updateNotNull(user_);
			    		json.setSuccess(true);
				    	json.setMsg(YztConstant.UP_PWD_SUCCESS);
				    	json.setObj(user);
			    	}
		    	}
		    	
		    }else{
		    	json.setSuccess(false);
		    	json.setMsg(YztConstant.UP_PWD_PWDERR);
		    }
		}
		
		return json;
	}
	
	@Override
	public Json getVerifyCode(YztRegistor registor) {
		//LocalDateTime dateTime = LocalDateTime.now();
		System.out.println("registor.getIsMobile()="+registor.getIsMobile());
		Json json = new Json();
		if(registor.getIsMobile() !=null && !registor.getIsMobile().isEmpty()){
			if(YztUtil.isMobileNO(registor.getIsMobile())){
				
				
				Example example = new Example(YztRegistor.class);
				Example.Criteria criteria = example.createCriteria();
				criteria.andEqualTo("isMobile", registor.getIsMobile());
				
				Example example1 = new Example(YztUserinfo.class);
				Example.Criteria criteria1 = example1.createCriteria();
				criteria1.andEqualTo("account", registor.getIsMobile());
				criteria1.andEqualTo("status",1);//0,非禁用 用户,1-禁用
				List<YztUserinfo> list1 = selectByExample(example1);
				if(list1.size()>0){
					json.setSuccess(false);
					json.setMsg(YztConstant.LOGIN_ACCOUNT_BAN);
				}else{
					List<YztRegistor> list = registService.selectByExample(example);
					if(list.size()>0){//已经注册过，对比过期时间，如果过期，重新发送验证码。如果没过期提示查看手机验证码
						//System.out.println("已经注册过");
						for(YztRegistor reg : list){
							if(reg.getStatus() !=null && reg.getStatus()==1){//已经是成功注册的用户，可以发送修改密码的验证
								Calendar cal = (GregorianCalendar) Calendar.getInstance();
								cal.setTime(new Date());
								GregorianCalendar endCalendar = ( GregorianCalendar ) Calendar.getInstance();
								endCalendar.setTime(reg.getOverdueTime());
								if(cal.compareTo(endCalendar)>0){//时间已过期
									System.out.println("重新发送");
									String smscontent = (int)((Math.random()*9)*100000) +"";
									Calendar c = Calendar.getInstance();
									try {
										String sms_result = Demo_Client.sentContentTo(registor.getIsMobile(), smscontent);
										//String sms_result = "654321"; 
												
										reg.setIsVerifyCode(smscontent);
										reg.setSmsId(sms_result);
										reg.setSendTime(c.getTime());
										//加一天
										//c.add(Calendar.DAY_OF_MONTH, 1);
										c.add(Calendar.MINUTE, 3);
										reg.setOverdueTime(c.getTime());
										//reg.setRequestCount(reg.getRequestCount()+1);
										registService.updateNotNull(reg);
									} catch (UnsupportedEncodingException e) {
										e.printStackTrace();
									}
									json.setSuccess(true);
									json.setMsg(YztConstant.MSG_SUCCESS);
									json.setObj(reg);
								}else{//在有效期内
									//System.out.println(YztConstant.MSG_SECOND);
									json.setSuccess(false);
									json.setMsg(YztConstant.MSG_SECOND);
								}
								
							}else{
								json.setSuccess(false);
								json.setMsg(YztConstant.REGIST_MOBILE_NULL);
							}
							
						}
						//return "";
					}else{
						json.setSuccess(false);
						json.setMsg(YztConstant.REGIST_MOBILE_NULL);
					}
				}
			}else{
				json.setSuccess(false);
				json.setMsg(YztConstant.MSG_MOBILE_ERR);
			}
			
		}else{
			json.setSuccess(false);
			json.setMsg(YztConstant.MSG_MOBILE_NULL);
		}
		return json;
	}
	
	@SuppressWarnings("unused")
	@Override
	public Json resetPWD(YztRegistor registor) {
		Json json = new Json();
		if(!registor.getIsMobile().isEmpty() && !registor.getIsVerifyCode().isEmpty() && !registor.getIsPassword().isEmpty()){
			Example example1 = new Example(YztUserinfo.class);
			Example.Criteria criteria1 = example1.createCriteria();
			criteria1.andEqualTo("account", registor.getIsMobile());
			//criteria1.andEqualTo("status",1);//0,非禁用 用户,1-禁用
			List<YztUserinfo> list1 = selectByExample(example1);
			
			Example example = new Example(YztRegistor.class);
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("isMobile", registor.getIsMobile());
			//criteria.andEqualTo("isVerifyCode", registor.getIsVerifyCode());
			List<YztRegistor> list = registService.selectByExample(example);
			for(YztUserinfo userinfo : list1){
				if(userinfo.getStatus() !=null && userinfo.getStatus()==1){
					json.setSuccess(false);
					json.setMsg(YztConstant.LOGIN_ACCOUNT_BAN);
				}else{
					if(list.size()>0){//已经发送过验证码，对比过期时间，如果过期，重新发送验证码。如果没过期提示查看手机验证码
						for(YztRegistor reg : list){
							//reg.getOverdueTime();
							if(reg.getStatus() !=null && reg.getStatus()==1){
								//json.setSuccess(false);
								//json.setMsg(YztConstant.REGIST_SECOND);
							//}else{
								Calendar cal = (GregorianCalendar) Calendar.getInstance();
								cal.setTime(new Date());
								GregorianCalendar endCalendar = ( GregorianCalendar ) Calendar.getInstance();
								endCalendar.setTime(reg.getOverdueTime());
								//if(cal.compareTo(endCalendar)>0){//时间已过期
								if(1>2){
									json.setSuccess(false);
									json.setMsg(YztConstant.MSG_VERFIYCODE_OVERDUE);
								}else{//注册成功
									//reg.setRegistTime(new Date());
									//reg.setStatus(1);
									//registService.updateAll(reg);
									
									//同时往用户表里插入
									YztUserinfo user = new YztUserinfo();
									user.setId(userinfo.getId());
									//user.setAccount(reg.getIsMobile());
									user.setPassword(registor.getIsPassword());
						
									updateNotNull(user);
									
									json.setSuccess(true);
									json.setMsg(YztConstant.UP_PWD_SUCCESS);
								}
							}
							
						}
						
					}else{//找不到用户，提示验证码错误
						json.setSuccess(false);
						json.setMsg(YztConstant.MSG_MOBILE_VERFIYCODE_NOTEQUALL);
					}
				}
			}
			
		}else{
			json.setSuccess(false);
			json.setMsg(YztConstant.MSG_MOBILE_VERFIYCODE_NOTNULL);
		}
		
		return json;
	}
	/*
	public Integer updateBySql(String sql) throws Exception{
    	return mapper.updateBySql(sql);
    }*/
}
