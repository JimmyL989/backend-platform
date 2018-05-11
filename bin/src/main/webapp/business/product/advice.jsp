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
		
		var period_id = '<%=request.getParameter("period_id")%>';
	 	var productId = '<%=request.getParameter("productid")%>';
		
		 $('#' + period_id + 'reference').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/ProductController/queryReference.do?period_id=' + period_id + '&productId=' + productId,
			fit : true,
			fitColumns : true,
			border : false,
			pagination : false,
			idField : 'advice_reference_id',
			singleSelect : true,
			sortName : 'advice_reference_id',
			rowStyler: function(index,row){
				return 'border-bottom-color:black; border-bottom-width: 0 0 0px 0; border-right-color:black; border-style: solid;border-width: 1px 1px 1px 1px;';
			},
			sortOrder : 'asc',
			nowrap : false,
			striped: true,
			frozenColumns : [ [ {
				field : 'advice_reference_id',
				title : '编号',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [  {
				field : 'content',
				title : '短信制作内容参考',
				width : 60,
				formatter:function(value, row, index){
					return value.replace(/\n/g,"<br>");
				}
			}  ] ],
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});
		
		 $('#' + period_id + 'relevant').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/ProductController/queryRelevant.do?period_id=' + period_id + '&productId=' + productId,
			fit : true,
			fitColumns : true,
			border : false,
			pagination : false,
			idField : 'advice_relevant_id',
			singleSelect : true,
			sortName : 'advice_relevant_id',
			rowStyler: function(index,row){
				return 'border-bottom-color:black; border-bottom-width: 0 0 0px 0; border-right-color:black; border-style: solid;border-width: 1px 1px 1px 1px;';
			},
			sortOrder : 'asc',
			nowrap : false,
			striped: true,
			frozenColumns : [ [ {
				field : 'advice_relevant_id',
				title : '编号',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [  {
				field : 'content',
				title : '种植注意事项',
				width : 60,
				formatter:function(value, row, index){
					return value.replace(/\n/g,"<br>");
				}
			}  ] ],
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});
		
		 $('#' + period_id + 'analysis').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/ProductController/queryAnalysis.do?period_id=' + period_id + '&productId=' + productId,
			fit : true,
			fitColumns : true,
			border : false,
			pagination : false,
			idField : 'advice_analysis_id',
			singleSelect : true,
			sortName : 'advice_analysis_id',
			rowStyler: function(index,row){
				return 'border-bottom-color:black; border-bottom-width: 0 0 0px 0; border-right-color:black; border-style: solid;border-width: 1px 1px 1px 1px;';
			},
			sortOrder : 'asc',
			striped: true,
			nowrap : false,
			frozenColumns : [ [ {
				field : 'advice_analysis_id',
				title : '编号',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [  {
				field : 'element',
				title : '气象要素',
				width : 60
			},{
				field : 'factor',
				title : '具体因子',
				width : 60
			},{
				field : 'conditions',
				title : '适宜条件',
				width : 80
			},{
				field : 'content1',
				title : '异常条件及灾害',
				width : 160,
				formatter:function(value, row, index){
					return value.replace(/\n/g,"<br>");
				}
			},{
				field : 'content2',
				title : '气象灾害及减灾建议',
				width : 160,
				formatter:function(value, row, index){
					return value.replace(/\n/g,"<br>");
				}
			}  ] ],
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});
		 $('#' + period_id + 'faming').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/ProductController/queryFaming.do?period_id=' + period_id + '&productId=' + productId,
			fit : true,
			fitColumns : true,
			border : false,
			pagination : false,
			striped: true,
			idField : 'advice_farming_id',
			singleSelect : true,
			sortName : 'advice_farming_id',
			rowStyler: function(index,row){
				return 'border-bottom-color:black; border-bottom-width: 0 0 0px 0; border-right-color:black; border-style: solid;border-width: 1px 1px 1px 1px;';
			},
			sortOrder : 'asc',
			nowrap : false,
			frozenColumns : [ [ {
				field : 'advice_farming_id',
				title : '编号',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [  {
				field : 'content',
				title : '详细时段描述',
				width : 60
			}  ] ],
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});
		 $('#' + period_id + 'plant').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/ProductController/queryPlant.do?productId=' + productId + '&period_id=' + period_id,
			fit : true,
			fitColumns : true,
			border : false,
			pagination : false,
			striped: true,
			idField : 'advice_plant_id',
			singleSelect : true,
			sortName : 'advice_plant_id',
			sortOrder : 'asc',
			nowrap : false,
			rowStyler: function(index,row){
				return 'border-bottom-color:black; border-bottom-width: 0 0 0px 0; border-right-color:black; border-style: solid;border-width: 1px 1px 1px 1px;';
			},
			frozenColumns : [ [ {
				field : 'advice_plant_id',
				title : '编号',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [  {
				field : 'type',
				title : '类型',
				width : 60
			},{
				field : 'name',
				title : '病虫害',
				width : 60
			},{
				field : 'conditions',
				title : '病虫害发生条件',
				width : 100
			},{
				field : 'symptom',
				title : '病虫害发生症状',
				width : 160,
				formatter:function(value, row, index){
					return value.replace(/\n/g,"<br>");
				}
			},{
				field : 'measures',
				title : '病虫害防治措施',
				width : 160,
				formatter:function(value, row, index){
					return value.replace(/\n/g,"<br>");
				}
			}  ] ],
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});
		 $('#' + period_id + 'deficiency').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/ProductController/queryDeficiency.do?productId=' + productId,
			fit : true,
			fitColumns : true,
			border : false,
			pagination : false,
			striped: true,
			idField : 'advice_deficiency_id',
			singleSelect : true,
			sortName : 'advice_deficiency_id',
			sortOrder : 'asc',
			nowrap : false,
			rowStyler: function(index,row){
				return 'border-bottom-color:black; border-bottom-width: 0 0 0px 0; border-right-color:black; border-style: solid;border-width: 1px 1px 1px 1px;';
			},
			frozenColumns : [ [ {
				field : 'advice_deficiency_id',
				title : '编号',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [ {
				field : 'name',
				title : '缺素症',
				width : 60
			} ,{
				field : 'content',
				title : '说明',
				width : 360,
				formatter:function(value, row, index){
					return value.replace(/\n/g,"<br>");
				}
			}  ] ],
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});
		 $('#' + period_id + 'cultivation').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/ProductController/queryCultivation.do?productId=' + productId + '&period_id=' + period_id,
			fit : true,
			fitColumns : true,
			border : false,
			pagination : false,
			collapsible: true,
			striped: true,
			idField : 'advice_cultivation_id',
			singleSelect : true,
			sortName : 'advice_cultivation_id',
			sortOrder : 'asc',
			nowrap : false,
			autoRowHeight: true,
			rowStyler: function(index,row){
				return 'border-bottom-color:black; border-bottom-width: 0 0 0px 0; border-right-color:black; border-style: solid;border-width: 1px 1px 1px 1px;border-collapse:collapse;';
			},
			frozenColumns : [ [ {
				field : 'advice_cultivation_id',
				title : '编号',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [   {
				field : 'type',
				title : '类型',
				width : 60
			},{
				field : 'content',
				title : '栽培技术',
				width : 460,
				formatter:function(value, row, index){
					return value.replace(/\n/g,"<br>");
				}
			}  
          ] ],
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
				
			},
			onSelect: function (rowIndex, rowData) {
      			$(this).datagrid('unselectRow', rowIndex);
            }
		});
		toResize();
		
	}); 
	function toResize(){
		var timer = setTimeout(function (){
    		var period_id = '<%=request.getParameter("period_id")%>';
			$('#'+period_id+'relevant').datagrid("resize");
			$('#'+period_id+'analysis').datagrid("resize");
			$('#'+period_id+'faming').datagrid("resize");
			$('#'+period_id+'plant').datagrid("resize");
			$('#'+period_id+'deficiency').datagrid("resize");
			$('#'+period_id+'cultivation').datagrid("resize");
			$('#'+period_id+'reference').datagrid("resize");
			clearTimeout(timer);
		}, 0);
	}

</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div title="" data-options="region:'center',border:false">	
		<div style="float:left;width: 100%;height: 300px;overflow: hidden;" ><table id="<%=request.getParameter("period_id")%>reference"></table></div>
		<div style="float:left;width: 100%;height: 300px;overflow: hidden;" ><table id="<%=request.getParameter("period_id")%>relevant"></table></div>
		<div style="float:left;width: 100%;height: 300px;overflow: hidden;"><table id="<%=request.getParameter("period_id")%>faming"></table></div>
		<div style="float:left;width: 100%;height: 300px;overflow: hidden;"><table id="<%=request.getParameter("period_id")%>analysis"></table></div>
		<div style="float:left;width: 100%;height: 300px;overflow: hidden;"><table id="<%=request.getParameter("period_id")%>plant"></table></div>
		<div style="float:left;width: 100%;height: 300px;overflow: hidden;"><table id="<%=request.getParameter("period_id")%>deficiency"></table></div>
		<div style="float:left;width: 100%;height: 300px;overflow: hidden;"><table id="<%=request.getParameter("period_id")%>cultivation"></table></div>
	</div>
</div>

