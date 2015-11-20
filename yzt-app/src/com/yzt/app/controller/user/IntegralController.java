package com.yzt.app.controller.user;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tk.mybatis.mapper.entity.Example;

import com.yzt.app.controller.BaseController;
import com.yzt.app.model.YztBaby;
import com.yzt.app.model.YztCdkey;
import com.yzt.app.model.YztCommodity;
import com.yzt.app.model.YztIntegralDetail;
import com.yzt.app.model.YztIntegralTask;
import com.yzt.app.model.YztRegistor;
import com.yzt.app.model.YztUserinfo;
import com.yzt.app.utils.YztUtil;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.Json;

@Controller
@RequestMapping("/user/integral")
public class IntegralController extends BaseController {
	
	@RequestMapping(value={"/html/{userId}"},method = RequestMethod.GET)
	public String html(@PathVariable String userId, ModelMap m){
		m.put("userId", userId);
		m.put("status", "2");
		return "user/integral/integrallist";
	}
	
	@RequestMapping(value={"/html/{userId}/{status}"},method = RequestMethod.GET)
	public String html(@PathVariable String userId,@PathVariable String status, ModelMap m){
		if(!status.equals("0") && !status.equals("1")){
			status = "2";
		}
		m.put("userId", userId);
		m.put("status", status);
		return "user/integral/integrallist";
	}
	/**
	 * 
	 * @param userId
	 * @param status 0-消费，1-增加，2-所有
	 * @param integral
	 * @param dg
	 * @param m
	 * @return
	 */
	@RequestMapping("/listjson/{userId}/{status}")
	@ResponseBody
	public EasyuiDataGridJson listjson(@PathVariable String userId,@PathVariable String status,YztIntegralDetail integral, EasyuiDataGrid dg, ModelMap m){
		int sta =1;
		if(status.equals("0")){
			sta = 0;
		}else if(status.equals("1")){
			sta = 1;
		}else if(status.equals("2")){
			sta = 2;
		}
		integral.setYztUserId(userId);
		integral.setStatus(sta);
		return integralService.datagrid(integral, dg);
	}
	//做任务赚积分
	@RequestMapping("/earn/{userId}/{taskId}")
	@ResponseBody
	public Json earn(@PathVariable String userId,@PathVariable String taskId,YztIntegralDetail integral,ModelMap m) throws Exception{
		Json json = new Json();
		YztIntegralTask task = taskService.selectByKey(taskId);
		//1-每日任务，2-进阶
		if(!integralService.isTaskComplete(userId, taskId, task.getTaskType())){
			integral.setId(GUIDGener.getGUID());
			integral.setContent(task.getName());
			integral.setIntegralValue(task.getIntegralValue());
			integral.setCreatetime(new Date());
			integral.setStatus(1);
			integral.setYztUserId(userId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//sdf.format(new Date());
			integral.setContentDatetime(sdf.format(new Date()));
			integralService.save(integral);
			//完成任务后，收入糖果增加积分
			//YztUserinfo userinfo = new YztUserinfo();
			//userinfo.setId(userId);
			userinfoService.updateBySql("set integral_curr= integral_curr+"+integral.getIntegralValue()+",integral_earn=integral_earn+"+integral.getIntegralValue()+" where id='"+userId+"'");
			json.setSuccess(true);
			json.setMsg("完成任务");
		}else{
			json.setSuccess(false);
			json.setMsg("已经完成任务，请不要重复");
		}
		
		return json;
	}
	
	//消费积分
	@RequestMapping("/cost/{userId}/{commodityId}")
	@ResponseBody
	public Json cost(@PathVariable String userId,@PathVariable String commodityId,YztIntegralDetail integral,ModelMap m) throws Exception{
		Json json = new Json();
		YztUserinfo userinfo = userinfoService.selectByKey(userId);
		YztCommodity commodity = commodityService.selectByKey(commodityId);
		if(commodity.getStatus()==0){
			json.setSuccess(false);
			json.setMsg("商品无效");
		}else if(commodity.getIsCount().intValue() <= commodity.getIsSellCount().intValue()){
			json.setSuccess(false);
			json.setMsg("商品已兑换完");
		}else{
		
			SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
			Date date = sdf.parse(commodity.getEndTime());
			Calendar cal = (GregorianCalendar) Calendar.getInstance();
			cal.setTime(new Date());
			GregorianCalendar endCalendar = ( GregorianCalendar ) Calendar.getInstance();
			endCalendar.setTime(date);
			
			if(cal.compareTo(endCalendar)>0){//时间已过期
				json.setSuccess(false);
				json.setMsg("商品过期");
			}else if(userinfo.getIntegralCurr().intValue()<commodity.getIntegralValue().intValue()){
				json.setSuccess(false);
				json.setMsg("糖果不足");
			}else{
				if(!integralService.isCommodityComplete(userId, commodity)){
					integral.setId(GUIDGener.getGUID());
					integral.setContent(commodity.getName());
					integral.setIntegralValue(commodity.getIntegralValue());
					integral.setCreatetime(new Date());
					integral.setStatus(0);
					integral.setYztUserId(userId);
					integral.setTaskId(commodityId);
					
					//SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
					sdf.format(new Date());
					integral.setContentDatetime(sdf.format(new Date()));
					
					//发送兑换码,同时将一个兑换码的用户id填充。
					Example example = new Example(YztCdkey.class);
					Example.Criteria criteria = example.createCriteria();
					criteria.andEqualTo("yztCommodityId", commodityId);
					criteria.andIsNull("yztUserId"); 
					//criteria.and
					
					
					//List<YztCdkey> cdkeylist = cdkeyService.selectByExample(example);
					YztCdkey cdkey= cdkeyService.selectOneByExample(example);
					cdkey.setYztUserId(userId);
					//将兑换码填充上userId;
					cdkeyService.updateNotNull(cdkey);
					integral.setCdkeyCode(cdkey.getCdkey());
					//增加消费记录
					integralService.save(integral);
					//商品消耗数+1
					commodityService.updateBySql("set is_sell_count =is_sell_count+1 where id='"+commodityId+"'");
					///////////////
					//发送短信，兑换码
					//
					///////////////
					//完成兑换后，用户中当前积分减少，花费糖果增加积分
					userinfoService.updateBySql("set integral_curr=integral_curr-"+integral.getIntegralValue()+",integral_cost=integral_cost+"+integral.getIntegralValue()+" where id='"+userId+"'");
					json.setSuccess(true);
					json.setMsg("消费成功");
					json.setObj(integral);
				}else{
					json.setSuccess(false);
					json.setMsg("已经兑换过，不能重复");
				}
			}
		}
		return json;
	}
		
	
}
