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