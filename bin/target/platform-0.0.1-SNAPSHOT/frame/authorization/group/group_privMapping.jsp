<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	var resourceTree;
	$(function() {
		resourceTree = $('#resourceTree').tree({
			url : '${pageContext.request.contextPath}/authorization/controller/PrivController/allTree.do?sys_group_id=' + "${id}",
			parentField : '_parentId',
			lines : true,
			checkbox : true,
			animate : true,
			onClick : function(node) {
			},
			onLoadSuccess : function(node, data) {
				var ids = $.stringToList('${ids}');
/* 				var ids = $.stringToList('${role.resourceIds}'); */
				if (ids.length > 0) {
					for ( var i = 0; i < ids.length; i++) {
						if (resourceTree.tree('find', ids[i])) {
							resourceTree.tree('check', resourceTree.tree('find', ids[i]).target);
						}
					}
				}
				/* $('#roleGrantLayout').layout('panel', 'west').panel('setTitle', $.formatString('[{0}]角色可以访问的资源', '${role.name}')); */
				parent.$.messager.progress('close');
			},
			cascadeCheck : true
		});

		$('#form').form({
			url : '${pageContext.request.contextPath}/authorization/controller/PrivController/grantPriv.do?sys_group_id=' + "${id}",
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				var checknodes = resourceTree.tree('getChecked');
				var ids = [];
				if (checknodes && checknodes.length > 0) {
					for ( var i = 0; i < checknodes.length; i++) {
						ids.push(checknodes[i].id);
					}
				}
				$('#resourceIds').val(ids);
				
				
				var checknodes_ = resourceTree.tree('getChecked','indeterminate');
				var ids_ = [];
				if (checknodes_ && checknodes_.length > 0) {
					for ( var i = 0; i < checknodes_.length; i++) {
						ids_.push(checknodes_[i].id);
					}
				}
				$('#resourceIds_').val(ids_);
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					/* resourceTree.tree('reload'); */
					parent.$.messager.alert('提示','授权成功');
				}
			}
		});
	});

	function checkAll() {
		var nodes = resourceTree.tree('getChecked', 'unchecked');
		if (nodes && nodes.length > 0) {
			for ( var i = 0; i < nodes.length; i++) {
				resourceTree.tree('check', nodes[i].target);
			}
		}
	}
	function uncheckAll() {
		var nodes = resourceTree.tree('getChecked');
		if (nodes && nodes.length > 0) {
			for ( var i = 0; i < nodes.length; i++) {
				resourceTree.tree('uncheck', nodes[i].target);
			}
		}
	}
	function checkInverse() {
		var unchecknodes = resourceTree.tree('getChecked', 'unchecked');
		var checknodes = resourceTree.tree('getChecked');
		if (unchecknodes && unchecknodes.length > 0) {
			for ( var i = 0; i < unchecknodes.length; i++) {
				resourceTree.tree('check', unchecknodes[i].target);
			}
		}
		if (checknodes && checknodes.length > 0) {
			for ( var i = 0; i < checknodes.length; i++) {
				resourceTree.tree('uncheck', checknodes[i].target);
			}
		}
	}
</script>
<div id="roleGrantLayout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center'" title="系统资源" style="padding: 1px;">
		<div class="well well-small">
			<form id="form" method="post">
				<input name="id" type="hidden" class="span2" value="${role.id}" readonly="readonly">
				<ul id="resourceTree"></ul>
				<input id="resourceIds" name="resourceIds" type="hidden" />
				<input id="resourceIds_" name="resourceIds_" type="hidden" />
			</form>
		</div>
	</div>
<%-- 	<div data-options="region:'center'" title="" style="overflow: hidden; padding: 10px;">
		<div class="well well-small">
			<span class="label label-success">${role.name}</span>
			<div>${role.remark}</div>
		</div>
		<div class="well well-large">
			<button class="btn btn-success" onclick="checkAll();">全选</button>
			<br /> <br />
			<button class="btn btn-warning" onclick="checkInverse();">反选</button>
			<br /> <br />
			<button class="btn btn-inverse" onclick="uncheckAll();">取消</button>
		</div>
	</div> --%>
</div>