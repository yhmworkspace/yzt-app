function openWindow(path){
	var X = screen.width * 0.07; // x position
	var Y = screen.height * 0.1; // y position
 	var W = screen.width * 0.86; // width
	var H = screen.height * 0.8; // height
	var s="resizable,scrollbars,status=yes,left="+X+",top="+Y+",screenX="+X+",screenY="+Y+",width="+W+",height="+H;
	var SGW = window.open(path,'_blank',s);
}
	function expandAll(treeMenu){
        $('#'+treeMenu).tree('expandAll');
    }
	function allChecked(treeMenu,check){
        var roots = $('#'+treeMenu).tree('getRoots');
		/*for ( var i = 0; i < roots.length; i++) {  
            var node = $('#'+treeMenu).tree('find', roots[i].id);  
            $('#'+treeMenu).tree(check, node.target); 
            
            var childs = $('#'+treeMenu).tree('getChildren', node.target);
            for ( var j = 0; j < childs.length; j++) {  
            	 var child = $('#'+treeMenu).tree('find', childs[j].id);  
                 $('#'+treeMenu).tree(check, child.target); 
            }
            
        } 
		*/
		subChecked(treeMenu,check,roots);
    }
	function subChecked(treeMenu,check,childs){
		for ( var i = 0; i < childs.length; i++) {  
            var node = $('#'+treeMenu).tree('find', childs[i].id);  
            $('#'+treeMenu).tree(check, node.target); 
            
            var sub_childs = $('#'+treeMenu).tree('getChildren', node.target);
            subChecked(treeMenu,check,sub_childs);
            
        } 
		
	}
	
	
	function jsonDateFormat(jsonDate) {//json日期格式转换为正常格式 /Date(1354648740000)/
	    try {
	    	
	        //var date = new Date(parseInt(jsonDate.replace("/Date(", "").replace(")/", ""), 10));
	        var date = new Date(parseInt(jsonDate, 10));
	        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	        var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	        var hours = date.getHours();
	        var minutes = date.getMinutes();
	        var seconds = date.getSeconds();
	        var milliseconds = date.getMilliseconds();
	        return date.getFullYear() + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;// + "." + milliseconds;
	    } catch (ex) {
	        return "";
	    }
	}
	
	function cellDate(value,row,index){
		if(value ==null){
			return "";
		}else{
			return jsonDateFormat(value);
		}
		
	}

	function cellStyler(value,row,index){
		if (value == 2){
			return 'background-color:#ffee00;color:red;';
		}else{
			return 'background-color:#ffee00;color:red;';
		}
	}
	

	function rowformatter(value,row,index){
		if (value == 2){
			return '数字';
		}else if(value == 1){
			return '字符';
		}
	}
	function formatter_status(value,row,index){
		if (value == 0){
			return '失效';
		}else if(value == 1){
			return '有效';
		}
	}
	
	function formatter_userrole(value,row,index){
		if (value == 0){
			return '系统管理员';
		}else if(value == 1){
			return '数据管理员';
		}else if(value == 2){
			return '数据录入员';
		}
	}
	
	function formatter_userstatus(value,row,index){
		if (value == 0){
			return '<font color=red>锁定</font>';
		}else if(value == 1){
			return '正常';
		}
	}
	
	function showPic(value,row,index){
		//openWindow("ar	chive/originalhtml/${is_category_id}/"+row.id);
		var category_id = $('#category_id').val();
		return "<a href=\"javacript:void(0);\" onclick=\"openWindow('system/"+category_id+"/pic/html/"+row.id+"');\"> "+value+" </a>";
	}
	function showVideo(value,row,index){
		//openWindow("archive/originalhtml/${is_category_id}/"+row.id);
		var category_id = $('#category_id').val();
		return "<a href=\"javacript:void(0);\" onclick=\"openWindow('system/"+category_id+"/video/html/"+row.id+"');\"> "+value+" </a>";
	}
	
	function showCourse(value,row,index){
		//openWindow("archive/originalhtml/${is_category_id}/"+row.id);
		var category_id = $('#category_id').val();
		return "<a href=\"javacript:void(0);\" onclick=\"openWindow('system/"+category_id+"/course/html/"+row.id+"');\">"+value+"</a>";
	}
	
	function showComments(value,row,index){
		//openWindow("archive/originalhtml/${is_category_id}/"+row.id);
		var category_id = $('#category_id').val();
		return "<a href=\"javacript:void(0);\" onclick=\"openWindow('system/"+category_id+"/comment/html/"+row.id+"');\">"+value+"</a>";
	}
	
	function showValue(value,row,index){
		return "<a href=\"javacript:void(0);\" onclick=\"prePic('"+value+"');\">"+value+"</a>";
	}
	
	function showUrl(value,row,index){
		return "<a href=\""+value+"\" target=\"_blank\" \">"+value+"</a>";
	}
	var posturl;
	function archiveCreate(archivelist){
		var categoryid = $('#category_id').val();
		var row = $('#'+archivelist).datagrid('getSelected');
		if(archivelist=="archivelist"){
			
			$('#archiveadd').window('open').dialog('setTitle', '创建数据');
			 if(row !=null){
				 //$('#archiveadd').window('refresh','system/'+categoryid+'/add/'+row.id);
				 $('#archiveadd').window('refresh','system/'+categoryid+'/add?time='+new Date());
			 }else{
				 $('#archiveadd').window('refresh','system/'+categoryid+'/add?time='+new Date());
			 }
			//$.messager.alert("",categoryid);
		}
		posturl = "system/"+categoryid+"/save";
	}
	function archiveClose(){
		$('#archiveadd').window('close');
	}
	
	function archiveSave(savetype){
		$('#archiveinfo').form('submit',{
		    url:posturl,
		    onSubmit: function(){
		    	return $(this).form('validate');
		    	//$.messager.alert("提示",$('#id').val());
		    	//if($('#is_sequence').val()=='1' && updn=='up'){
		    		//$.messager.alert("提示","已经是第一个");
		    		//return false;
		    	//}
		    	//param.p1 = updn;
		    },
		    success: function(rs){
		    	//console.info(rs.obj);
		    	var rs = eval('(' + rs + ')');
		    	/*
		    	var categoryid = CategoryId(archiveCategory);
		    	*/
		    	if(savetype=="save"){
		    		//console.info(rs.obj);
		    		var categoryid = $('#category_id').val();
		    		$('#archiveadd').window('open').dialog('setTitle', '查看数据');
		    		if(categoryid=="sysuser" || categoryid=="indexvideo" || categoryid=="indexlogo"|| categoryid=="indexpic"|| categoryid=="indexvideo" || categoryid=="indexchoicenesscontent"){
		    			$('#archiveadd').window('close');
		    		}else{
		    			$('#archiveadd').window('refresh','system/'+categoryid+'/view/'+rs.obj.id);
		    		}
		    	}
		    	
		    	$('#archivelist').datagrid('reload');
		    	
		    	$.messager.show({
	                title: "提示",
	                msg: rs.msg
	            });
		    	//$('#inputcardmanage').panel('refresh', 'manage/categoryinputcardhtml/${category_id}?field_id='+rs.obj.id+'&field_seq='+rs.obj.is_sequence);
		    }
		});
		
	}
	
	//功能菜单：修改
	function archiveEdit(archivelist){
		 var rows = $('#'+archivelist).datagrid('getSelections');
		 //alert(rows.length);
		 if(rows.length != 1){
			 $.messager.alert("提示","请选择一条数据");
		 }else{
			var categoryid = $('#category_id').val();
			 var row = $('#'+archivelist).datagrid('getSelected');
			 if(categoryid =="learningcenter" && row.status==0){
				 $.messager.alert("提示","数据被锁定");
			 }else{
				$('#archiveadd').window('open').dialog('setTitle', '修改数据');
				 if(row !=null){
					 $('#archiveadd').window('refresh','system/'+categoryid+'/add/'+row.id);
				 }
				 //posturl = "system/"+categoryid+"/save/"+row.id;
				 posturl = "system/"+categoryid+"/save";
			 }
		 }
	}
	
	//删除
	function archiveRemove(archivelist){
		 var rows = $('#'+archivelist).datagrid('getSelections');
		 //alert(rows.length);
		 if(rows.length > 0){
			 var categoryid = $('#category_id').val();
			 $.messager.confirm('确认', '确定删除当前'+rows.length+'条数据?', function(re) {
		            if (re) {
		            	var ids = [];
		                for (var i = 0; i < rows.length; i++) {
		                    ids.push(rows[i].id);
		                }               
		                $.post('system/'+categoryid+'/delete', {ids: ids.join(',')}, function(r) {
		                    if (r.success) {
		                        $.messager.show({// show error message
		                            title: '提示',
		                            msg: r.msg
		                        });
		                        $('#'+archivelist).datagrid('reload');    // reload the user data
		                    } else {
		                        $.messager.show({// show error message
		                            title: 'Error',
		                            msg: r.msg
		                        });
		                    }
		                }, 'json');
		            }
		        });
		 }
	}
	
	//设置权重
	function archiveWeight(archivelist){
		var rows = $('#'+archivelist).datagrid('getSelections');
		 //alert(rows.length);
		 if(rows.length > 0){
			 var categoryid = $('#category_id').val();
			 
			 $.messager.prompt('设置权重', '请输入整数', function(v){
		            if (v){
		            	var ids = [];
		                for (var i = 0; i < rows.length; i++) {
		                    ids.push(rows[i].id);
		                }               
		                $.post('system/'+categoryid+'/setweight', {is_weight:v, ids: ids.join(',')}, function(r) {
		                    if (r.success) {
		                        $.messager.show({// show error message
		                            title: '提示',
		                            msg: r.msg
		                        });
		                        $('#'+archivelist).datagrid('reload');    // reload the user data
		                    } else {
		                        $.messager.show({// show error message
		                            title: 'Error',
		                            msg: r.msg
		                        });
		                    }
		                }, 'json');
		            }
		        });
		 }else{
			 
		 }
        
    }
	
	//设置是否有效
	function setOverdue(num){
		 var rows = $('#archivelist').datagrid('getSelections');
		 //alert(rows.length);
		 if(rows.length > 0){
			 var statu ;
			 if(num==1){
				 statu='解锁';
			 }else{
				 statu='锁定';
			 }
			 var categoryid = $('#category_id').val();
			 $.messager.confirm('确认', '确定设置当前'+rows.length+'条数据为'+statu+'?', function(re) {
		            if (re) {
		            	var ids = [];
		                for (var i = 0; i < rows.length; i++) {
		                    ids.push(rows[i].id);
		                }               
		                $.post('system/'+categoryid+'/updateStatus/'+num, {ids: ids.join(',')}, function(r) {
		                    if (r.success) {
		                        $.messager.show({// show error message
		                            title: '提示',
		                            msg: r.msg
		                        });
		                        $('#archivelist').datagrid('reload');    // reload the user data
		                    } else {
		                        $.messager.show({// show error message
		                            title: 'Error',
		                            msg: r.msg
		                        });
		                    }
		                }, 'json');
		            }
		        });
		 }
	}
	
	function prePic(url){
		
		$('#prePic_dialog').dialog('open'); 
		$('#pic_url').attr("src",url);
		//alert($('#logo_url').textbox('getValue'));
	}
	
	//详细页面：修改。是个对话框
	function archiveModify(categoryid,archive_id){
		$('#archiveadd').window('open').dialog('setTitle', '修改数据');
		
		$('#archiveadd').window('refresh','system/'+categoryid+'/add/'+archive_id);
	
		//posturl = "archive/archivesave/"+archive_id;
		
		 //$('#archiveadd').window('refresh','admin/'+categoryid+'/add/'+row.id);
		 
		posturl = "system/"+categoryid+"/save";
		 
	}
	

	//条目的修改记录
	function archiveLog(categoryid,archiveid){
		$('#archiveview_dialog').dialog({    
		    title: '修改记录',    
		    width: 600,    
		    height: 500,    
		    closed: false,    
		    cache: false,    
		    href: 'admin/logarchive/listlog/'+categoryid+'-'+archiveid,    
		    modal: true   
		});
		
	}
	
	//当打开新页面时，而不是对话框
	function modifyWidown(categoryid,archiveid){
		window.location.href="system/"+categoryid+"/edit/"+archiveid;
	}
	
	function getLng_Lat(newValue,oldValue){
		var dizhi = $('#cc2').val()+$('#cc3').val()+newValue;
		var baiduAPI_url = "http://api.map.baidu.com/geocoder/v2/?ak=AXspM3ydaBRDkFzYmWGgIP4L&output=json&address="+dizhi;
		
		 $.ajax({             
			 type: "get",             
			 url: baiduAPI_url,             
			 dataType: "jsonp",             
			 jsonp: "callback",             
			 jsonpCallback:"jsonpCallback",             
			 success: function(json){
				 console.info(json);
				 //alert('json:' + json);  
				 $('#longitude').textbox('setValue',json.result.location.lng);
		          $('#latitude').textbox('setValue',json.result.location.lat);
				 
				 },             
			 error: function(){                 
				 alert('fail');             
				 }         
		});
		
	}
	