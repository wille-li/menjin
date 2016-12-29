//提示框
function showmessage(title,msg){
		$.messager.show({
			title:title,
			msg:msg,
			timeout:5000,
			showType:'slide'
		});
}

//移除前后空格
String.prototype.trim= function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
};

var _default_options = {
		type: "GET",
	    url: "/",
	    cache: false,
	    async: false,
	    data: {},
	    processData: true,
	    contentType: "application/xml",
	    dataType: "xml"
	};

var mergeOptions = function(destOpt, srcOpt) {
    for (var name in srcOpt) {
        if (typeof srcOpt[name] == 'object') {
            destOpt[name] = ((srcOpt[name] && srcOpt[name].constructor == Array) ? [] : (!!destOpt[name] ? destOpt[name] : {}));
            typeof destOpt[name] == 'object' && mergeOptions(destOpt[name], srcOpt[name]);
        } else 
            destOpt[name] = srcOpt[name];
    }
};
//封装ajax提交方法
var getJSONData = window.getJSONData = function (options) {
	var returnXML = null;
	var _options = {};
	if(!!options){
		mergeOptions(_options, _default_options);
		mergeOptions(_options, options);
		contentType = options.contentType ? options.contentType : (!!options.type && options.type.toUpperCase() == "POST") ? "application/x-www-form-urlencoded;charset=UTF-8" : "text/json;charset=UTF-8";
		$.ajax({
			type: _options.type,
	        url: _options.url,
	        cache: _options.cache,
	        async: _options.async,
	        data: _options.data,
	        processData: _options.processData,
	        contentType: contentType,
	        dataType: "json",
	        success: function(xmlData){
				if (!!xmlData) {
					returnXML = xmlData;
					if (!!options.callback && isFunction(options.callback)) {
						options.callback(xmlData);
					}
				}
	        },
	        error:function(jqXHR, status, errorThrown){
	        	if (!!options.errcallback && isFunction(options.errcallback)) {
					options.errcallback(jqXHR, status, errorThrown);
				}
	        }
	    });
	}
	return returnXML;
};

Date.prototype.format = function (format) {  
    var o = {  
        "M+": this.getMonth() + 1, // month  
        "d+": this.getDate(), // day  
        "h+": this.getHours(), // hour  
        "m+": this.getMinutes(), // minute  
        "s+": this.getSeconds(), // second  
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter  
        "S": this.getMilliseconds()  
        // millisecond  
    }  
    if (/(y+)/.test(format))  
        format = format.replace(RegExp.$1, (this.getFullYear() + "")  
            .substr(4 - RegExp.$1.length));  
    for (var k in o)  
        if (new RegExp("(" + k + ")").test(format))  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
    return format;  
}  
function formatDatebox(value) {  
    if (value == null || value == '') {  
        return '';  
    }  
    var dt;  
    if (value instanceof Date) {  
        dt = value;  
    } else {  
        dt = new Date(value);  
    }  
  
    return dt.format("yyyy-MM-dd hh:mm:ss");
}

function formatYMDatebox(value){
	if (value == null || value == '') {  
        return '';  
    }  
    var dt;  
    if (value instanceof Date) {  
        dt = value;  
    } else {  
        dt = new Date(value);  
    }  
  
    return dt.format("yyyy-MM-dd");
}
