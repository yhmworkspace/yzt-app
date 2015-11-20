
var window_width = $(window).width();
var window_height = $(window).height();

var dlg_width = window_width*0.5;
var dlg_height = window_height*0.7;
var dlg_left = window_width*0.25;
var dlg_top = window_height*0.15;

$.fn.extend({
	mySerializeArray: function() {
		var r20 = /%20/g,
	    rbracket = /\[\]$/,
	    rCRLF = /\r?\n/g,
	    rcheckableType = /^(?:checkbox|radio)$/,
	    rsubmitterTypes = /^(?:submit|button|image|reset|file)$/i,
	    rsubmittable = /^(?:input|select|textarea|keygen)/i;
        //jQuery.fn.map
        return this.map(function() {
            // Can add propHook for "elements" to filter or add form elements
            // 如果当前对象具有elements的prop，则使用，反之使用自身
            // elements是原生js中表单所有的input。 如：document.forms[0].elements
            var elements = jQuery.prop( this, "elements" );
            return elements ? jQuery.makeArray( elements ) : this;
        })
        .filter(function() {//jQuery.fn.filter
            var type = this.type;
            // Use .is(":disabled") so that fieldset[disabled] works
            // 过滤掉没有name、disabled的、可以提交的几个标签，如过是可选中的元素，则checked为真
            return this.name && !jQuery( this ).is( ":disabled" ) &&
                rsubmittable.test( this.nodeName ) && !rsubmitterTypes.test( type ) &&
                ( this.checked || !rcheckableType.test( type ) );
        })
        .map(function( i, elem ) {////jQuery.fn.map
            var val = jQuery( this ).val();
            var trim = jQuery( this ).attr('trim');
            //设置value
            return val == null ?
                null :
                jQuery.isArray( val ) ?
                    jQuery.map( val, function( val ) {
                        return { name: elem.name, value: val.replace( rCRLF, "\r\n" ) ,trim: trim};
                    }) :
                    { name: elem.name, value: val.replace( rCRLF, "\r\n" ) ,trim: trim};
        }).get();//转为真正的数组
    }
});
jQuery.extend({
	contextPath: function(){
		 var pathName = document.location.pathname;
		 var index = pathName.substr(1).indexOf("/");
		 return pathName.substr(0,index+1);
	},
	/**
	 * 提交form表单
	 * @param target form表单对象
	 * @param success 成功回调函数
	 * @param error 失败回调函数
	 */
	toSubmit:function(target,success,error){
		var form = $(target);
		var url = $.contextPath() + form.attr("action");
		var param = form.form('getData');
		$.toPost(url,param,function(result){
			if(result.result == false){
				if(result.msg) $.messager.alert('错误', result.msg);
			}
			if(success) success(result);
			else $.messager.alert('提示', result.msg);
		},function(){
			$.messager.alert('错误', "请求服务器失败!");
			if(error) error();
		});
	},
	/**
	 * 带进度条提交 提交完成后自动关闭进度条
	 * @param target
	 * @param success
	 * @param error
	 */
	toPgSubmit:function(target,success,error){
		$.messager.progress();
		$.toSubmit(target, function(data){
			$.messager.progress('close');
			if(success) success(data);
		}, function(){
			$.messager.progress('close');
			if(error) error();
		});
	},
	/**
	 * 带进度条的post请求服务器
	 * @param url
	 * @param param
	 * @param success
	 * @param error
	 */
	toPgPost: function(url, param, success, error){
		$.messager.progress();
		$.toPost(url, param, function(data){
			$.messager.progress('close');
			if(success) success(data);
		}, function(){
			$.messager.progress('close');
			if(error) error();
		});
	},
	/**
	 * post请求服务器
	 * @param url 请求url
	 * @param param 参数
	 * @param success 成功回调
	 * @param error 失败回调
	 */
	toPost: function(url, param, success, error) {
		$.ajax({
			type : 'POST',
			url : url,
			data : param,
			dataType : "json",
			success : function(data) { 
				if(data.result && data.result == '100'){
					doLogin(function(){
						$.toPost(url, param,success,error);
					});
				}
				else if(success)success(data); 
			},error : function(){ if(error) error(); }
		});
	},
	toJsonPost: function(url, param, success, error) {
		$.ajax({
			type : 'POST',
			contentType : 'application/json; charset=utf-8',
			url : url,
			data : JSON.stringify(param),
			dataType : "json",
			success : function(data) {
				if(success)success(data);
			},error : function(){if(error)error();}
		});
	},
	toHtmlPost: function(url, param, success, error) {
		$.ajax({
			type : 'POST',
			url : url,
			data : param,
			dataType : "html",
			success : function(data) {success(data);},
			error : function(){if(error)error();}
		});
	},
	toJson: function(text){
		return eval('('+text+')');
	},
	// 编辑产删除按钮格式化
	operateFormatter: function(value,row,index){
        var editButton = "<img title='编辑' src='../jquery-easyui/themes/icons/pencil.png' style='cursor:pointer;' onclick='editRow("+index+")'></img>";
        var removeButton = "<img title='删除' src='../jquery-easyui/themes/icons/edit_remove.png' style='cursor:pointer;margin-left:10px;' onclick='delRow("+index+")'></img>";
        return editButton + removeButton;
    }
	
});
/**
 * 扩展validatebox校验方法
 */
$.extend($.fn.validatebox.defaults.rules, {
	notNull: {
		validator : function(value) {
			value = $.trim(value);
			return typeof value != "undefined" && null != value && '' != value;
		},
		message : '该项不能为空！'
	},
	number : {
		validator : function(value) {
			return /^[0-9]*$/.test(value);
		},
		message : '请输入正确的数字.'
	},
	phone: {
		validator : function(value) {
			return /^(1[3458])[0-9]{9}$/.test(value);
		},
		message : '请输入正确的手机号码.'
	},
	equals : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '两次输入{1}不匹配.'
	},
	currency: {// 验证货币
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '货币格式不正确'
    },/**
	 * 时间验证 验证开始时间不能超过结束时间
	 * 开始时间输入框设置为data-options="validType:'time[\'#endTime\',\'填报\']'"
	 * #endTime = 结束时间输入框ID \'填报\' = 提示XX开始时间不能超过XX结束时间
	 */
	time: {
		validator: function(value, param){
			return value >  $(param[0]).timespinner('getValue');
		},
		message: '结束{1}不能小于开始{1}！'
	},
	/**
	 * 时间验证 验证开始时间不能超过结束时间
	 * 结束时间输入框设置为data-options="validType:'datebox[\'#startDay\',\'有效\']'"
	 * #endTime = 结束时间输入框ID \'填报\' = 提示XX开始时间不能超过XX结束时间
	 */
	date: {
		validator: function(value, param){
			return value >=  $(param[0]).datebox('getValue');
		},
		message: '结束{1}不能小于开始{1}！'
	}

});


/**
 * 获取当前月最后一天
 * @param monthOffset 月偏移量 默认0 上一月 -1;下一页1
 * @returns yyyy-MM-dd
 */
function lastDay(monthOffset){
	if(!monthOffset) monthOffset = 0;
	var day = new Date();
	var year = day.getFullYear();
	var month = day.getMonth() + 1 + monthOffset;
	var day2 = new Date(year,month,0);
	var lastdate = day2.getDate();
	return year+'-'+month+'-'+lastdate;
}

/**
 * 获取当前月第一天
 * @param monthOffset 月偏移量 默认0 上一月 -1;下一页1
 * @returns yyyy-MM-dd
 */
function firstDay(monthOffset){
	if(!monthOffset) monthOffset = 0;
	var day = new Date();
	var year = day.getFullYear();
	var month = day.getMonth() + 1 + monthOffset;
	var day2 = new Date(year,month,1);
	var lastdate = day2.getDate();
	return year+'-'+month+'-'+lastdate;
}
/**
 * 获取当前时间
 * @returns yyyy-MM-dd
 */
function toDay(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	if(month.toString().length==1)month="0"+month;
	var day = date.getDate();
	if(day.toString().length==1)day="0"+day;
	return year+'-'+month+'-'+day;
}
