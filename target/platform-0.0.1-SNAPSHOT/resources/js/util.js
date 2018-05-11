	Date.prototype.format =function(format)
    {
        var o = {
        "M+" : this.getMonth()+1, //month
		"d+" : this.getDate(),    //day
		"h+" : this.getHours(),   //hour
		"m+" : this.getMinutes(), //minute
		"s+" : this.getSeconds(), //second
		"q+" : Math.floor((this.getMonth()+3)/3),  //quarter
		"S" : this.getMilliseconds() //millisecond
        }
        if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
        (this.getFullYear()+"").substr(4- RegExp.$1.length));
        for(var k in o)if(new RegExp("("+ k +")").test(format))
        format = format.replace(RegExp.$1,
        RegExp.$1.length==1? o[k] :
        ("00"+ o[k]).substr((""+ o[k]).length));
        return format;
    }
	function hback()
	{
	    //Stop Ctrl+N
	    if ((event.ctrlKey) && (event.keyCode==78))
	    {
	              event.returnValue=false;
	    }
	    //Stop Ctrl+R
	    if ((event.ctrlKey) && (event.keyCode==82))
	    {
	         event.keyCode=0;
	         event.returnValue=false;
	    }
	    //Stop Shift+F10
	    if ((event.shiftKey) && (event.keyCode==121))
	    {
	         event.returnValue=false;
	    }
	    //Stop SHIFT+CLICK
	    if ((event.srcElement.tagName=="A") && (event.shiftKey))
	    {
	         event.returnValue = false;
	    }
	    //Stop F5 And F11
	    if (event.keyCode==116 || event.keyCode==122)
	    {
	         event.keyCode=0;
	         event.returnValue = false;
	    }
	    //Stop BackSpace
	    if (event.keyCode == 8) {
	         if ((event.srcElement.type=="text" || event.srcElement.type=="password" || event.srcElement.type=="file" || event.srcElement.type == "textarea") && (!event.srcElement.readOnly) && (!event.srcElement.disabled)){
	              event.returnValue=true;
	         } else {
	              event.returnValue=false;
	         }
	    }
	    //Covert Enter To Tab
	    if (event.keyCode == 13) {
	         if (event.srcElement.type=="text" || event.srcElement.type == "password"){
	              event.keyCode = 9;
	         }
	    }
	    //Stop Alt+backSpace
	    if (event.altKey && event.keyCode==37) {
	         event.returnValue=false;
	    }
		if((window.event.altKey)&&((window.event.keyCode==37)||(window.event.keyCode==39)))
		{
			//alert("Do not use Alt + -> or <- ");
			event.returnValue=false;
		}
		if (window.event.srcElement.tagName == "A" && window.event.shiftKey)
			window.event.returnValue = false;
		if ((window.event.altKey)&&(window.event.keyCode==115))
		{
		  window.showModelessDialog("about:blank","","dialogWidth:1px;dialogheight:1px");
		  return false;
		}
	}
	function returnValueFalse()
	{
		   event.returnValue=false;
	}
	function returnValueTrue()
	{
		event.returnValue=true;
	}
	function returnFalse()
	{
			return false;
	}
	
	//js获取项目根路径，如： http://localhost:8083/uimcardprj
	function getRootPath(){
	    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	    var curWwwPath=window.document.location.href;
	    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	    var pathName=window.document.location.pathname;
	    var pos=curWwwPath.indexOf(pathName);
	    //获取主机地址，如： http://localhost:8083
	    var localhostPaht=curWwwPath.substring(0,pos);
	    //获取带"/"的项目名，如：/uimcardprj
	    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	    return projectName;
	}
	
	
    function Save_Excel(dataGrid) {//导出Excel文件
    	
        //getExcelXML有一个JSON对象的配置，配置项看了下只有title配置，为excel文档的标题
        var data = dataGrid.datagrid('getExcelXml', { title: 'datagrid import to excel' }); //获取datagrid数据对应的excel需要的xml格式的内容
        //用ajax发动到动态页动态写入xls文件中
        var url = getRootPath() + "/business/controller/DataQueryController/createFile.do"; //如果为asp注意修改后缀
       // window.location = url; //执行下载操作
       // return;
        
     /*    $.ajax({ url: url, data: { data: data }, type: 'POST', dataType: 'text',
            success: function (result) {
                alert('导出excel成功！');
                alert(result.msg);
                window.location = "${pageContext.request.contextPath}/business/controller/DataQueryController/downFile.do?filePath=" + result.msg; //执行下载操作
            },
            error: function (xhr) {
                alert('动态页有问题\nstatus：' + xhr.status + '\nresponseText：' + xhr.responseText)
            }
        }); */
        
        $.post(url, {
					data : data
				}, function(result) {
					if (result.success) {
						//parent.$.messager.alert('提示', result.msg, 'info');
						
                		window.location = getRootPath() + "/business/controller/DataQueryController/downFile.do?filePath=" + result.msg; //执行下载操作
					}
					parent.$.messager.progress('close');
				}, 'JSON');
        return false;
    }
    
    function validateCombobox(combo){
    	var regionId2Boolean = false;
		if(combo.combobox('getValue') == '') {
			regionId2Boolean = true;
		} else {
			var regionId2Check = combo.combobox('getData');
	
			for(var i=0;i<regionId2Check.length;i++) {
				var id = regionId2Check[i].id;
				if(combo.combobox('getValue') == id){
					regionId2Boolean = true;
				}
			}
		}
		return regionId2Boolean;
    }
