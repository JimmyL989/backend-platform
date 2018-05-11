<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.datagrid-header td,
.datagrid-body td,
.datagrid-footer td {
  border-width: 1px 1px 1px 1px;
  border-style: solid;
  margin: 0;
  padding: 0;
  border-color:black;
}
</style>
<script type="text/javascript">

	 $(function() {
		$("#form input[name=instanceid]").val('${instance.instanceid}');
		parent.$.messager.progress('close');

		$('#form').form({
			url : '${pageContext.request.contextPath}/business/controller/ProductController/addInstance.do',
			onSubmit : function() {
			
				if ($('#state').val() == '审批通过' || $('#state').val() == '已发送') {
					parent.$.messager.alert('错误', '该状态无法修改', 'error');
					return false;
				}
					
			
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$("#form input[name=sendstate]").val(parent.$.modalDialog.sendstate);
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
					getProduct5day($('#cc').combotree('getValue'));
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
		
	}); 
		function NewDate(str) { 
		str = str.split('-'); 
		var date = new Date(); 
		date.setUTCFullYear(str[0], str[1] - 1, str[2].substr(0,2)); 
		date.setUTCHours(0, 0, 0, 0); 
		return date; 
	} 
	function getProduct5day(regionId) {
	
		var productId = $('#cc1').combobox('getValue');
		
		var created_time = '${instance.created_time}';
		var myDate = NewDate(created_time);
		
		$.post('${pageContext.request.contextPath}/business/controller/ProductController/queryMessageBody.do', {
						instanceid : '${instance.instanceid}'
					}, function(result) {
						if (result.success) {
							//parent.$.messager.alert('提示', result.msg, 'info');
							$('#messageBody').val(result.obj.content);
							validMaxSize();
							
							if (result.obj.sendstate == '-2') {
								$('#state').val('待提交');
							} else if (result.obj.sendstate == '-1') {
								$('#state').val('待审批');
							} else if (result.obj.sendstate == '0') {
								$('#state').val('审批通过');
							} else if (result.obj.sendstate == '-3') {
								$('#state').val('被驳回');
							} else if (result.obj.sendstate == '1') {
								$('#state').val('已发送');
							}
							
							if (result.obj.sendstate == '0' || result.obj.sendstate == '1'){
								$('#messageBody').attr('readonly', 'readonly');
							} else {
								$('#messageBody').removeAttr('readonly');
							}
								
						} else {
							$('#state').val('待录入');
							$('#messageBody').val('');
							$('#messageBody').removeAttr('readonly');
						}
						parent.$.messager.progress('close');
					}, 'JSON');
	
		$("#forecastTd").height(210);
        
		$('#forecast').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/ProductController/queryForecast.do?regionId=' + regionId + '&created_time=' + myDate.format("yyyy-MM-dd"),
			fit : true,
			fitColumns : true,
			border : false,
			pagination : false,
			idField : 'urban_forecast_transfer_id',
			singleSelect : true,
			sortName : 'time',
			sortOrder : 'asc',
			nowrap : false,
			frozenColumns : [ [ {
				field : 'urban_forecast_transfer_id',
				title : '编号',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [  {
				field : 'stationname',
				title : '站名',
				width : 60
			} ,{
				field : 'content',
				title : '预报内容',
				width : 260
			} ,{
				field : 'time',
				title : '预报内容',
				width : 60,
				hidden : true
			} ] ],
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
				if($('#state').val() == '待录入') {
				    var r = '';
					for(var i = 0; i < $('#forecast').datagrid('getRows').length; i++) {
						r += $('#forecast').datagrid('getRows')[i].content;
					}
					$('#messageBody').val(r);
					validMaxSize();
				} 
			}
		});
	
		$("#product5dayTd").height(180);
		
		var yesterday_milliseconds=myDate.getTime()-1000*60*60*24*5;       
    	var yesterday = new Date();       
        yesterday.setTime(yesterday_milliseconds); 
        
		$('#product5day').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/ProductController/queryProduct5days.do?maxTime='+myDate.format("yyyy-MM-dd") +'&minTime=' + yesterday.format("yyyy-MM-dd") +'&productId=' + productId +'&regionId=' + regionId,
			fit : true,
			fitColumns : true,
			border : false,
			pagination : false,
			idField : 'Instanceid',
			singleSelect : true,
			sortName : 'Created_time',
			sortOrder : 'desc',
			nowrap : false,
			frozenColumns : [ [ {
				field : 'Instanceid',
				title : '编号',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [  {
				field : 'Created_time',
				title : '生成时间',
				width : 60,
				sortable : true,
				formatter:function(value,row,index){  
					if (value == "" || value == null)
						return "";
					var date = new Date(value);
					return date.format("yyyy-MM-dd hh:mm:ss");
                    }  
			} ,{
				field : 'content',
				title : '产品内容',
				width : 260
			}] ],
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
				
			}
		});
		
		$("#periodTd").height(240);
        
		$('#period').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/ProductController/queryPeriod.do?productId=' + productId +'&regionId=' + regionId,
			fit : true,
			fitColumns : true,
			border : false,
			pagination : false,
			idField : 'RELATION_PERIOD_ID',
			singleSelect : true,
			sortName : 'RELATION_PERIOD_ID',
			sortOrder : 'asc',
			nowrap : false,
			frozenColumns : [ [ {
				field : 'RELATION_PERIOD_ID',
				title : '编号',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [  {
				field : 'PERIOD_NAME',
				title : '发育期',
				width : 100
			},{
				field : 'PERIOD_ID',
				title : '发育期',
				width : 60,
				hidden : true
			},{
				field : 'STIME',
				title : '开始时间',
				width : 160,
				sortable : true,
				formatter:function(value,row,index){  
					if (value == "" || value == null)
						return "";
					var date = new Date(value);
					return date.format("yyyy-MM-dd hh:mm:ss");
                    }  
			},{
				field : 'ETIME',
				title : '结束时间',
				width : 160,
				sortable : true,
				formatter:function(value,row,index){  
					if (value == "" || value == null)
						return "";
					var date = new Date(value);
					return date.format("yyyy-MM-dd hh:mm:ss");
                    }  
			} ] ],
			onLoadSuccess : function(data) {
				parent.$.messager.progress('close');
				var rowData = data.rows;
				for(var i=0;i<rowData.length;i++){
					var s = Number(rowData[i].STIME);
					var e = Number(rowData[i].ETIME);
					var d = new Date().getTime();
					if(d>=s && d<=e){
							var allTabs = $('#tt').tabs('tabs');

								$.each(allTabs, function() {
									var opt = $(this).panel('options');
									if(rowData[i].PERIOD_NAME == opt.title){
										$('#tt').tabs('select', rowData[i].PERIOD_NAME);
										return false;
									}
								});
						break;
					}
				}
			}
		});
		
	}
	
	function validMaxSize(){
		if($('#messageBody').val() != undefined)
		$('#fi').html('已输入' + $('#messageBody').val().length + '个字');
	
	}
	

	function immediately() {
		var element = document.getElementById("mytext");
		if (window.ActiveXObject) {
			element.onpropertychange = webChange;
		} else {
			element.addEventListener("input", webChange, false);
		}
	}
	function webChange() {
		if (element.value) {
			document.getElementById("test").innerHTML = element.value;
		}
	}
	function getAdvice(productid) {
		$.post('${pageContext.request.contextPath}/business/controller/ProductController/getAdvice.do', {
						productId : productid
					}, function(result) {
							var allTabs = $('#tt').tabs('tabs');
								var closeTabsTitle = [];

								$.each(allTabs, function() {
									var opt = $(this).panel('options');
									closeTabsTitle.push(opt.title);
								});
								for ( var i = 0; i < closeTabsTitle.length; i++) {
									$('#tt').tabs('close', closeTabsTitle[i]);
								}
					
					
						if (result.success) {
																
								for(var i = 0;i<result.obj.length;i++){
								
									$('#tt').tabs('add',{
										title: result.obj[i].period_name,
										id:i,
										selected: false,
										cache:true,
										href:'${pageContext.request.contextPath}/business/product/advice.jsp?period_id=' + result.obj[i].period_id + '&productid=' + productid
									});
									
								}	
								
						} else {
						}
						parent.$.messager.progress('close');
					}, 'JSON');
	}
	function toResize(){
		
	}
</script>
<%-- <div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		 <form id="form" method="post">
		 <input name="instanceid" type="hidden"/>
		 <input name="sendstate" type="hidden"/>
			<table class="table table-hover table-condensed">
				<tr>
					<th width="50px">产品</th>
					<td width="80px">
					<input id="cc1" name="cc1" class="easyui-combobox" readonly="readonly" data-options="editable:false,required:true, valueField: 'Productid',textField: 'Product_name', 
					url: '${pageContext.request.contextPath}/business/controller/ProductController/queryProductCombo.do', 
							onSelect: function(rec){ 
							var url ='${pageContext.request.contextPath}/business/controller/ProductController/queryRegionTree.do?productId=' + rec.Productid;
							$('#cc').combotree('clear');
           				 	$('#cc').combotree('reload', url);  }"/>
					</td>
					<script>
						$('#cc1').combobox({
							onLoadSuccess : function() {
								$('#cc1').combobox('select', '${instance.productid}');
							}
						});
					</script>
					<th width="50px">行政区划</th>
					<td width="100px">
					
					<select id="cc" name="cc" class="easyui-combotree" style="width:200px;" required="required" readonly="readonly" data-options="onSelect: function(rec){ getProduct5day(rec.id) }"></select>  
					<script>
						$('#cc').combotree({
							onLoadSuccess : function(param) {
								$('#cc').combotree('setValue', '${instance.region_code}');
							}
						});
					</script>
					<input id="cc1" name="cc1" class="easyui-combobox" data-options=" valueField: 'id',textField: 'text', 
					url: '${pageContext.request.contextPath}/business/controller/DCPController/getDivision.do?id=9999', 
							onSelect: function(rec){ 
							var url ='${pageContext.request.contextPath}/business/controller/DCPController/getDivision.do?id=' + rec.id;
           				 $('#cc2').combobox('reload', url);  }" />   
					<input id="cc2" name="cc2" class="easyui-combobox" data-options="valueField:'id',textField:'text', 
					url: '${pageContext.request.contextPath}/business/controller/DCPController/getDivision.do?id=9999', 
							onSelect: function(rec){ 
							var url ='${pageContext.request.contextPath}/business/controller/DCPController/getDivision.do?id=' + rec.id;
           				 $('#cc3').combobox('reload', url);  }" />   
           				 
           			<input id="cc3" name="cc3" class="easyui-combobox" data-options="valueField:'id',textField:'text'"/> 
           			</td>
           			<th width="50px">当前状态</th>
           			<td width="100px"><input id="state" name="state" type="text" class="span2" readonly="readonly" value=""></input></td>
				</tr>
				<tr>
					<td colspan="2">
					
					<table class="table table-hover table-condensed">
						<tr>
							<th>短信内容</th>
							<td>
							<textarea name="messageBody" id="messageBody" rows="20" cols="" style="width: 230px;resize: none;" onPropertyChange="validMaxSize()" oninput="validMaxSize()"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: right;"><font id="fi" style="width: 30px">已输入0个字</font></td>
						</tr>
					</table>
					</td>
					<td colspan="4">
					<table class="table table-hover table-condensed">
						<tr>
							<td width="270px" id="forecastTd" height="120px"><table id="forecast"></table></td>
						</tr>
						<tr>
							<td width="270px" id="product5dayTd" height="180px"><table id="product5day"></table></td>
						</tr>
						<tr>
							<td width="270px" id="periodTd" height="150px"><table id="period"></table></td>
						</tr>
					</table>
					</td>
				</tr> 
			</table>
		</form> 
	</div>
</div> --%>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="form" method="post">
	<input name="instanceid" type="hidden"/>
	<input name="sendstate" type="hidden"/>
	<div data-options="region:'north',title:'',border:false" style="height: 60px; overflow: hidden;">
	<table class="table table-hover table-condensed">
				<tr>
					<th width="50px">产品</th>
					<td width="80px">
					<input id="cc1" name="cc1" class="easyui-combobox" readonly="readonly" data-options="editable:false,required:true, valueField: 'Productid',textField: 'Product_name', 
					url: '${pageContext.request.contextPath}/business/controller/ProductController/queryProductCombo.do', 
							onSelect: function(rec){ 
							var url ='${pageContext.request.contextPath}/business/controller/ProductController/queryRegionTree.do';
							$('#cc').combotree('clear');
           				 	$('#cc').combotree('reload', url);
           				 	getAdvice(rec.Productid);  }"/>
					</td>
					<script>
						var cpCount = 0;
						$('#cc1').combobox({
							onLoadSuccess : function() {
								cpCount++;
								if(cpCount == 2){
									$('#cc1').combobox('select', '${instance.productid}');
								}
							}
						});
					</script>
					<th width="50px">行政区划</th>
					<td width="100px">
					
					<select id="cc" name="cc" class="easyui-combotree" style="width:200px;" required="required" readonly="readonly" data-options="onSelect: function(rec){ getProduct5day(rec.id) }"></select>  
					<script>
						$('#cc').combotree({
							onLoadSuccess : function(param) {
								$('#cc').combotree('setValue', '${instance.region_code}');
							}
						});
					</script>
					<%-- <input id="cc1" name="cc1" class="easyui-combobox" data-options=" valueField: 'id',textField: 'text', 
					url: '${pageContext.request.contextPath}/business/controller/DCPController/getDivision.do?id=9999', 
							onSelect: function(rec){ 
							var url ='${pageContext.request.contextPath}/business/controller/DCPController/getDivision.do?id=' + rec.id;
           				 $('#cc2').combobox('reload', url);  }" />   
					<input id="cc2" name="cc2" class="easyui-combobox" data-options="valueField:'id',textField:'text', 
					url: '${pageContext.request.contextPath}/business/controller/DCPController/getDivision.do?id=9999', 
							onSelect: function(rec){ 
							var url ='${pageContext.request.contextPath}/business/controller/DCPController/getDivision.do?id=' + rec.id;
           				 $('#cc3').combobox('reload', url);  }" />   
           				 
           			<input id="cc3" name="cc3" class="easyui-combobox" data-options="valueField:'id',textField:'text'"/>  --%>
           			</td>
           			<th width="50px">当前状态</th>
           			<td width="100px"><input id="state" name="state" type="text" class="span2" readonly="readonly" value=""></input></td>
				</tr>
			</table>
	</div>

	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<div class="easyui-layout" data-options="fit:true,border:false" >
			<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
				<div id='tt' class='easyui-tabs' data-options="fit:true"></div>
			</div>
		 	<div data-options="region:'west',border:false" title="" style="overflow: hidden;width: 380px">
		 		<table class="table table-hover table-condensed">
		 			<tr>
						<td width="270px" id="product5dayTd" height="180px" colspan="2"><table id="product5day"></table></td>
					</tr>
					<tr>
						<td>
							<textarea name="messageBody" placeholder="请编辑短信内容..."  id="messageBody" rows="13" cols="" style="width: 350px;resize: none;" onPropertyChange="validMaxSize()" oninput="validMaxSize()" class="easyui-validatebox" data-options="required:true,validType:'length[0,140]'"></textarea>
						</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: right;"><font id="fi" style="width: 30px">已输入0个字</font></td>
					</tr>
				</table>
		 	</div>
			
					
			<div data-options="region:'east',border:false,onCollapse:function(){toResize()},onResize:function(width,height){toResize()}" title=" "  style="overflow: hidden;width:370px">
				<table class="table table-hover table-condensed">
					<tr>
						<td width="270px" id="forecastTd" height="210px"><table id="forecast"></table></td>
					</tr>
					<tr>
						<td width="270px" id="periodTd" height="260px"><table id="period"></table></td>
					</tr>
				</table>
			</div>
	</div>
</div>		
		</form> 
</div>
<script type="text/javascript">
//immediately();
</script>