package com.yzt.app.controller.system;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzt.app.controller.BaseController;
import com.yzt.app.model.YztDictionary;
import com.yzt.app.utils.id.GUIDGener;
import com.yzt.app.utils.web.EasyuiDataGrid;
import com.yzt.app.utils.web.EasyuiDataGridJson;
import com.yzt.app.utils.web.EasyuiTreeNode;
import com.yzt.app.utils.web.Json;

@Controller
@RequestMapping("/system/dictionary")
public class DictionaryController extends BaseController {
	
	@RequestMapping(value={"","/"},method = RequestMethod.GET)
	public String dictionary(ModelMap m){
		return "system/dictionary/dictionary";
	}
	/**
	 * 
	 * @param id
	 * @param dic
	 * @param m
	 * @return 数型结构
	 * @throws Exception
	 */
	@RequestMapping("/treejson/{id}")
	@ResponseBody
	public List<EasyuiTreeNode> DictionaryTreeList(@PathVariable String id,YztDictionary dic,ModelMap m) throws Exception{
		return dictionaryService.treeDictionary(id);
	}
	
	/*
	 * 分页列表
	 */
	@RequestMapping("/listjson/{dic_pid}")
	@ResponseBody
	public EasyuiDataGridJson DictionaryListJson(@PathVariable String dic_pid, EasyuiDataGrid dg, ModelMap m) throws Exception{
		return dictionaryService.listDictionaryByPid(dic_pid,dg);
	}
	
	/*
	 * 列表-手机接口
	 */
	@RequestMapping("/list/{dic_pid}")
	@ResponseBody
	public List<YztDictionary> DictionaryList(@PathVariable String dic_pid,  ModelMap m) throws Exception{
		return dictionaryService.listDictionaryByPid(dic_pid);
	}
	
	
	@RequestMapping("/save/{dic_pid}")
	@ResponseBody
    public Json save(YztDictionary dic,@PathVariable String dic_pid) {
		Json json = new Json();
		int result = 0;
        if(dic_pid ==null || dic_pid.equals("")){
        	dic_pid = "0";
        }
		String id = GUIDGener.getGUID();
		dic.setId(id);
		dic.setDicPid(dic_pid);
		dic.setCreate_time(new Date());
		dic.setDicSequence(dictionaryService.listDictionaryByPid(dic_pid).size()+1);
        result = dictionaryService.save(dic);
       
        if(result>0){
        	json.setSuccess(true);
        	json.setMsg("保存成功");
        	json.setObj(dic);
        }else{
        	json.setSuccess(false);
        	json.setMsg("保存失败");
        }
        return json;
    }
	
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
    public Json delete(YztDictionary dic,@PathVariable String id) {
		Json json = new Json();
		int result = 0;
        
        result = dictionaryService.delete(id);
       
        if(result>0){
        	json.setSuccess(true);
        	json.setMsg("操作成功");
        	json.setObj(dic);
        }else{
        	json.setSuccess(false);
        	json.setMsg("保存失败");
        }
        return json;
    }
	
	@RequestMapping(value = "/update/{id}")
	@ResponseBody
    public Json update(YztDictionary dic,@PathVariable String id) {
		Json json = new Json();
		int result = 0;
        
        result = dictionaryService.updateNotNull(dic);
       
        if(result>0){
        	json.setSuccess(true);
        	json.setMsg("操作成功");
        	json.setObj(dic);
        }else{
        	json.setSuccess(false);
        	json.setMsg("保存失败");
        }
        return json;
    }
	
	/*
	 * 上下移动
	 */
	@RequestMapping(value = "/move/{updn}/{dicPid}/{id}/{cur_seq}")
	@ResponseBody
    public Json move(@PathVariable String updn,@PathVariable String dicPid,@PathVariable String id,@PathVariable int cur_seq) throws Exception {
		Json json = new Json();
		int result = 0;
        
        result = dictionaryService.move(updn, dicPid, id, cur_seq);;
       
        if(result>0){
        	json.setSuccess(true);
        	json.setMsg("操作成功");
        	//json.setObj(dic);
        }else{
        	json.setSuccess(false);
        	json.setMsg("保存失败");
        }
        return json;
    }
	
	/*
	 * 上下移动
	 */
	@RequestMapping(value = "/sort/{dic_pid}")
	@ResponseBody
    public Json sort(@PathVariable String dic_pid) throws Exception {
		Json json = new Json();
		int result = 0;
        
		List<YztDictionary> list = dictionaryService.listDictionaryByPid(dic_pid);
		int k=1;
		for(YztDictionary dic : list){
			dictionaryService.updateBySql(" set dic_sequence="+ k +" where id='"+dic.getId()+"'");
			k++;
			
			List<YztDictionary> list1 = dictionaryService.listDictionaryByPid(dic.getId());
			int j=1;
			for(YztDictionary dic1 : list1){
				dictionaryService.updateBySql(" set dic_sequence="+ j +" where id='"+dic1.getId()+"'");
				j++;
				
				List<YztDictionary> list2 = dictionaryService.listDictionaryByPid(dic1.getId());
				int m=1;
				for(YztDictionary dic2 : list2){
					dictionaryService.updateBySql(" set dic_sequence="+ m +" where id='"+dic2.getId()+"'");
					m++;
					
					List<YztDictionary> list3 = dictionaryService.listDictionaryByPid(dic2.getId());
					int n=1;
					for(YztDictionary dic3 : list3){
						dictionaryService.updateBySql(" set dic_sequence="+ n +" where id='"+dic3.getId()+"'");
						n++;
						
						List<YztDictionary> list4 = dictionaryService.listDictionaryByPid(dic3.getId());
						int p=1;
						for(YztDictionary dic4 : list4){
							dictionaryService.updateBySql(" set dic_sequence="+ p +" where id='"+dic4.getId()+"'");
							p++;
							
							List<YztDictionary> list5 = dictionaryService.listDictionaryByPid(dic4.getId());
							int q=1;
							for(YztDictionary dic5 : list5){
								dictionaryService.updateBySql(" set dic_sequence="+ q +" where id='"+dic5.getId()+"'");
								q++;
							}
						}
					}
				}
			}
		}
       
        if(result>0){
        	json.setSuccess(true);
        	json.setMsg("操作成功");
        	//json.setObj(dic);
        }else{
        	json.setSuccess(false);
        	json.setMsg("保存失败");
        }
        return json;
    }
}
