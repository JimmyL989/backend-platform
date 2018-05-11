<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%
Map map = (TreeMap)application.getAttribute("CODE_TABLE_CONTENT");
TreeMap inperiodMap = (TreeMap)map.get("inperiodMap");
request.setAttribute("inperiodMap", inperiodMap);
%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/business/controller/InperiodController/editCrop.do',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
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
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});

		$('#period').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/ProductController/queryPeriod.do?productId=' + '${ crops.productid }' +'&regionId=' + '${ crops.region_code }',
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
				field : 'STIME',
				title : '开始时间',
				width : 40,
				sortable : true,
				formatter:function(value,row,index){  
					if (value == "" || value == null)
						return "";
					var date = new Date(value);
					return date.format("yyyy-MM-dd");
                    }  
			},{
				field : 'ETIME',
				title : '结束时间',
				width : 40,
				sortable : true,
				formatter:function(value,row,index){  
					if (value == "" || value == null)
						return "";
					var date = new Date(value);
					return date.format("yyyy-MM-dd");
                    }  
			} ] ],
			onLoadSuccess : function(data) {
				parent.$.messager.progress('close');
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" title="" style="overflow: hidden;height: 40px">
		<form id="form" method="post">
		<input name="regionId" type="hidden" value="${ crops.region_code }"/>
		<input name="productId" type="hidden" value="${ crops.productid }"/>
			<table class="table table-hover table-condensed">
				<tr>
					<th width="70px">产品</th>
					<td width="100px">
					<input id="cc1" name="cc1" class="easyui-combobox" readonly="readonly" data-options="editable:false,required:true, valueField: 'Productid',textField: 'Product_name', 
					url: '${pageContext.request.contextPath}/business/controller/ProductController/queryProductCombo.do', 
							onSelect: function(rec){ 
							 }"/>
					</td>
					<script>
						var cpCount = 0;
						$('#cc1').combobox({
							onLoadSuccess : function() {
								cpCount++;
								if(cpCount == 2){
									$('#cc1').combobox('select', '${crops.productid}');
								}
							}
						});
					</script>
					<th width="70px">行政区划</th>
					<td width="120px">
					
					<select id="cc" name="cc" class="easyui-combotree" style="width:200px;" required="required" readonly="readonly" data-options="onSelect: function(rec){  },url: '${pageContext.request.contextPath}/business/controller/ProductController/queryRegionTree.do'"></select>  
					<script>
						$('#cc').combotree({
							onLoadSuccess : function(param) {
								$('#cc').combotree('setValue', '${crops.region_code}');
							}
						});
					</script>
					<th>状态</th>
					<td><select name="type" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<c:forEach items="${inperiodMap}" var="inperiodMap">
								<option value="${inperiodMap.key}" 
								<c:if test="${inperiodMap.key == crops.type}">
								selected="selected"
								</c:if>
								>${inperiodMap.value}</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<table id="period"></table>
	</div>
</div>