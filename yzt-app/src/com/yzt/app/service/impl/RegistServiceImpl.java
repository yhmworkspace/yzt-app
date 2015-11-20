package com.yzt.app.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.yzt.app.config.YztConstant;
import com.yzt.app.config.sms.Demo_Client;
import com.yzt.app.model.YztRegistor;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.service.RegistService;
import com.yzt.app.service.UserinfoService;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.FieldFilter;
import com.yzt.app.utils.web.Json;

@Service("registService")
public class RegistServiceImpl extends BaseServiceImpl<YztRegistor> implements RegistService {
	@Autowired
	protected UserinfoService userinfoService;

	@Override
	public EasyuiDataGridJson datagrid(YztRegistor registor, EasyuiDataGrid dg) {

		EasyuiDataGridJson listjson = new EasyuiDataGridJson();
		
		Example example = new Example(YztRegistor.class);
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
	    
	   
		listjson.setRows(selectByExample(example));

		return listjson;
	}
	
	@Override
	public Json verifyCode(YztRegistor registor) {
		//LocalDateTime dateTime = LocalDateTime.now();
		System.out.println("shoujihao===="+registor.getIsMobile());
		Json json = new Json();
		if(registor.getIsMobile() !=null && !registor.getIsMobile().isEmpty()){
			if(YztUtil.isMobileNO(registor.getIsMobile())){
			Example example = new Example(YztRegistor.class);
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("isMobile", registor.getIsMobile());
			List<YztRegistor> list = selectByExample(example);
			if(list.size()>0){//已经注册过，对比过期时间，如果过期，重新发送验证码。如果没过期提示查看手机验证码
				System.out.println("已经注册过");
				for(YztRegistor reg : list){
					reg.getStatus();
					reg.getOverdueTime();
					if(reg.getStatus() !=null && reg.getStatus()==1){//已经是成功的用户
						json.setSuccess(false);
						json.setMsg(YztConstant.REGIST_SECOND);
					}else{
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
								reg.setRequestCount(reg.getRequestCount()+1);
								updateAll(reg);
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
								json.setSuccess(true);
								json.setMsg(YztConstant.MSG_SUCCESS);
								json.setObj(reg);
								//json.setObj("====ok");
						}else{//在有效期内
							System.out.println(YztConstant.MSG_SECOND);
							json.setSuccess(false);
							json.setMsg(YztConstant.MSG_SECOND);
						}
					}
					
				}
				//return "";
			}else{//如果为0，调用短信通道，并保存。
				//6位随机数
				String smscontent = (int)((Math.random()*9)*100000) +"";
				Calendar c = Calendar.getInstance();
				try {
					String sms_result = Demo_Client.sentContentTo(registor.getIsMobile(), smscontent);
					//String sms_result = "123456789"; 
							
					registor.setIsVerifyCode(smscontent);
					registor.setSmsId(sms_result);
					registor.setSendTime(c.getTime());
					//加一天
					//c.add(Calendar.DAY_OF_MONTH, 1);
					c.add(Calendar.MINUTE, 3);
					registor.setOverdueTime(c.getTime());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				registor.setId(GUIDGener.getGUID());
				registor.setCreatetime(new Date());
				registor.setRequestCount(1);
				save(registor);
				json.setSuccess(true);
				json.setMsg(YztConstant.MSG_SUCCESS);
				json.setObj(registor);
			}
			}else{
				json.setSuccess(false);
				json.setMsg(YztConstant.MSG_MOBILE_ERR);
			}
		}else{
			json.setSuccess(false);
			json.setMsg(YztConstant.MSG_MOBILE_NULL);
		}
		//System.out.println("==="+json);
		return json;
	}

	@Override
	public Json regist(YztRegistor registor) {
		Json json = new Json();
		//if(!registor.getIsMobile().isEmpty() && !registor.getIsVerifyCode().isEmpty() && !registor.getIsPassword().isEmpty()){
		if(!registor.getIsMobile().isEmpty()  && !registor.getIsPassword().isEmpty()){
					
			Example example = new Example(YztRegistor.class);
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("isMobile", registor.getIsMobile());
			
			//criteria.andEqualTo("isVerifyCode", registor.getIsVerifyCode());
			List<YztRegistor> list = selectByExample(example);
			if(list.size()>0){//已经发送过验证码，对比过期时间，如果过期，重新发送验证码。如果没过期提示查看手机验证码
				json.setSuccess(false);
				json.setMsg(YztConstant.REGIST_SECOND);
				/*
				for(YztRegistor reg : list){
					//reg.getOverdueTime();
					if(reg.getStatus() !=null && reg.getStatus()==1){
						json.setSuccess(false);
						json.setMsg(YztConstant.REGIST_SECOND);
					}else{
						Calendar cal = (GregorianCalendar) Calendar.getInstance();
						cal.setTime(new Date());
						GregorianCalendar endCalendar = ( GregorianCalendar ) Calendar.getInstance();
						endCalendar.setTime(reg.getOverdueTime());
						if(cal.compareTo(endCalendar)>0){//时间已过期
							//System.out.println("重新发送");
							json.setSuccess(false);
							json.setMsg(YztConstant.MSG_VERFIYCODE_OVERDUE);
						}else{//注册成功
							reg.setRegistTime(new Date());
							reg.setStatus(1);
							updateAll(reg);
							
							//同时往用户表里插入
							YztUserinfo user = new YztUserinfo();
							user.setId(GUIDGener.getGUID());
							user.setAccount(reg.getIsMobile());
							user.setPassword(registor.getIsPassword());
							user.setCreatetime(new Date());
							user.setBabyCount(0);
							user.setIntegralCurr(0);
							user.setIntegralEarn(0);
							user.setIntegralCost(0);
							userinfoService.save(user);
							
							json.setSuccess(true);
							json.setMsg(YztConstant.REGIST_SUCCESS);
						}
					}
					
				}
				*/
			}else{//找不到用户，提示验证码错误
				registor.setId(GUIDGener.getGUID());
				registor.setCreatetime(new Date());
				registor.setRequestCount(1);
				registor.setRegistTime(new Date());
				registor.setStatus(1);
				save(registor);
				
				//同时往用户表里插入
				YztUserinfo user = new YztUserinfo();
				user.setId(GUIDGener.getGUID());
				user.setAccount(registor.getIsMobile());
				user.setPassword(registor.getIsPassword());
				user.setCreatetime(new Date());
				user.setBabyCount(0);
				user.setIntegralCurr(0);
				user.setIntegralEarn(0);
				user.setIntegralCost(0);
				userinfoService.save(user);
				
				json.setSuccess(true);
				json.setMsg(YztConstant.REGIST_SUCCESS);
				/*
				json.setSuccess(false);
				json.setMsg(YztConstant.MSG_MOBILE_VERFIYCODE_NOTEQUALL);
				*/
			}
		}else{
			json.setSuccess(false);
			json.setMsg(YztConstant.MSG_MOBILE_VERFIYCODE_NOTNULL);
		}
		
		return json;
	}

	
	
}
