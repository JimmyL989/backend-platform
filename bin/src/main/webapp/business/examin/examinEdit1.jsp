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
	
	}
	
	function validMaxSize(){
		if($('#messageBody').val() != undefined)
		//$('#fi').html('已输入' + $('#messageBody').val().length + '个字');
		$('#fi').html('已输入' + $('#messageBody').val().replace(/[^\x00-\xff]/g,'**').length + '个字符');
	
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
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="form" method="post">
	<input name="instanceid" type="hidden"/>
	<input name="sendstate" type="hidden"/>
	<div data-options="region:'north',title:'',border:false" style="height: 60px; overflow: hidden;">
	<table class="table table-hover table-condensed" style="height: 60px">
				<tr>
					<th width="50px">产品</th>
					<td width="80px">
					<input id="cc1" name="cc1" style="width:120px;" class="easyui-combobox" readonly="readonly" data-options="editable:false,required:true, valueField: 'Productid',textField: 'Product_name', 
					url: '${pageContext.request.contextPath}/business/controller/ProductController/queryProductCombo.do', 
							onSelect: function(rec){ 
							var url ='${pageContext.request.contextPath}/business/controller/ProductController/queryRegionTree.do';
							$('#cc').combotree('clear');
           				 	$('#cc').combotree('reload', url);
           				 	  }"/>
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
					
					<select id="cc" name="cc" class="easyui-combotree" style="width:120px;" required="required" readonly="readonly" data-options="onSelect: function(rec){ getProduct5day(rec.id) }"></select>  
					<script>
						$('#cc').combotree({
							onLoadSuccess : function(param) {
								$('#cc').combotree('setValue', '${instance.region_code}');
							}
						});
					</script>
					
           			</td>
           			<th width="50px">当前状态</th>
           			<td width="70px" style="padding-top: 12px"><input id="state" name="state" type="text" class="span1" style="width: 80px;height: 18px" readonly="readonly" value=""></input></td>
				</tr>
			</table>
	</div>

	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<div class="easyui-layout" data-options="fit:true,border:false" >
		 	<div data-options="region:'west',border:false" title="" style="overflow: hidden;">
		 		<table class="table table-hover table-condensed" style="width:100%;">
					<tr>
						<td width="100%" align="right" style="text-align: center;">
							<textarea name="messageBody" placeholder="请编辑短信内容..."  id="messageBody" rows="13" cols="" style="width: 750px;resize: none;font-size:16px; color:#000; background-color: white;" onPropertyChange="validMaxSize()" oninput="validMaxSize()" class="easyui-validatebox" data-options="required:true,validType:'maxCharLength[280]'"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="1" style="text-align: right;"><font id="fi" style="width: 30px">已输入0个字</font></td>
					</tr>
				</table>
		 	</div>
			
					
	</div>
</div>		
		</form> 
</div>
