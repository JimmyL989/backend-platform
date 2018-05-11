<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" charset="utf-8">
	function changeThemeFun(themeName) {
		if ($.cookie('easyuiThemeName')) {
			$('#layout_north_pfMenu').menu('setIcon', {
				target : $('#layout_north_pfMenu div[title=' + $.cookie('easyuiThemeName') + ']')[0],
				iconCls : 'emptyIcon'
			});
		}
		$('#layout_north_pfMenu').menu('setIcon', {
			target : $('#layout_north_pfMenu div[title=' + themeName + ']')[0],
			iconCls : 'tick'
		});

		var $easyuiTheme = $('#easyuiTheme');
		var url = $easyuiTheme.attr('href');
		var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
		$easyuiTheme.attr('href', href);

		var $iframe = $('iframe');
		if ($iframe.length > 0) {
			for ( var i = 0; i < $iframe.length; i++) {
				var ifr = $iframe[i];
				try {
					$(ifr).contents().find('#easyuiTheme').attr('href', href);
				} catch (e) {
					try {
						ifr.contentWindow.document.getElementById('easyuiTheme').href = href;
					} catch (e) {
					}
				}
			}
		}

		$.cookie('easyuiThemeName', themeName, {
			expires : 7
		});

	};

	function logoutFun(b) {
		window.location.href="<%=request.getContextPath()%>/logonAction.do?method=logoff";
	}
	function editCurrentUserPwd() {
		var paw = parent.$.modalDialog({
			title : '修改密码',
			width : 500,
			height : 350,
			href : '${pageContext.request.contextPath}/frame/authorization/user/changePasswd.jsp',
			buttons : [ {
				text : '修改',
				handler : function() {
					var f = parent.$.modalDialog.handler.find('#editCurrentUserPwdForm');
					f.submit();
				}
			}, {
				text : '返回',
				handler : function() {
					paw.window('close');
				}
			} ]
		});
	}
	
	/**
	 * 
	 * 切换权限树js方法
	 * 
	 * @param id
	 *            根权限ID
	 */
	function secBoard(id,name){
		var rootid = id;
		ajaxsession(rootid,name);
		
		
	}
	function ajaxsession(rootid,name){
		$.post('${pageContext.request.contextPath}/authorization/controller/setRootId?rootid='+rootid,{name:name}, function(result) {
			if (result.success) {
				$('#west').panel('setTitle',result.msg);
				$('#west').panel('refresh');
				
				if ($('#index_layout').layout('panel','west').panel('options').collapsed)
					$('#index_layout').layout('expand','west');
		    	//触发西侧权限树变化
		    	//window.setTimeout("collspanNorth()",500);
				//重新加载west高度
				//window.setTimeout("reloadWest()",200);
				
			} else {
				$.messager.alert('错误', result.msg, 'error');
			}
			parent.$.messager.progress('close');
		}, "JSON");
	   <%--  $.ajax({
		    url: '<%=request.getContextPath()%>/authorization/controller/setRootId?rootid='+rootid,
		    type: 'POST',
		    success: function(){
		    	alert('1');
		    	$('#west').panel('refresh');
		    	//触发西侧权限树变化
		    	window.setTimeout("collspanNorth()",500);
				//重新加载west高度
				window.setTimeout("reloadWest()",200);
   		 	}	
		}); --%>
	}
	$(".system").hover(
		function(){
			$(this).addClass("imgover");	
		},
		function(){
			$(this).removeClass();	
		}
	);	
	
	function setOnLineCount(){
	
		$.post('${pageContext.request.contextPath}/authorization/controller/getOnLineCount.do', {
					}, function(result) {
						if (result.success) {
								$('#onLineCount').html(result.msg);								
								window.setTimeout("setOnLineCount()", 3000);
						} else {
						}
					}, 'JSON');
	}
		setOnLineCount();
</script>
<div style="width: 100%;height:88px">	
	
	<div style="width:572px;height:90px; position: absolute; left: 0px; top: 1px;">
		
	</div>
	<div style="width:7px;height:90px; position: absolute; right: 0px; top: 1px;">
		
	</div>
	<div style="width:572px;height:90px; position: absolute; right: 0px; top: 1px;">
		
	</div>
	<div id="sessionInfoDiv" style="position: absolute; right: 10px; top: 0px;" >
	在线人数：
	<font id="onLineCount"></font>
	人
	</div>
	<!-- 
	<div id="sessionInfoDiv" style="position: absolute; right: 0px; top: 0px;" >
		<c:if test="${sessionScope.logon_sysuser != null}">
			[<strong><c:out value="${sessionScope.logon_sysuser.username}"  /></strong>]，欢迎你！您使用
			[<strong><c:out value="${sessionScope.logon_sysuser.remoteIP}"/></strong>]IP登录！
		</c:if>
	</div>
	 -->
	<!-- 每个图片的间距 -->
	<c:set var="width" value="20"></c:set>
	<div style="<%= request.getContextPath()%>/resource/image/top_menu_bg.png;">
		<c:forEach items="${sessionScope._ROOTMENU_}" var="root">
			<c:if test="${root.url!=null && root.url!=''}" >
				<div style="position: absolute; right: <c:out value='${width }' />px; top: 60px;" >
					<a href="javascript:secBoard('<c:out value='${root.sys_privilege_id }' />','<c:out value='${root.name }'/>');" title="<c:out value='${root.name }'/>" >
						${root.name }
					</a> 
					
				</div>
				<c:set var="width" value="${width + 80}"></c:set>
			</c:if>
		</c:forEach>
	</div>
<!-- 	<div style="position: absolute; right: 0px; bottom: 0px;">
		<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'cog'">更换皮肤</a> 
		<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'cog'">控制面板</a>
	</div>
	<div id="layout_north_pfMenu" style="width: 120px; display: none;">
		<div onclick="changeThemeFun('default');" title="default">default</div>
		<div onclick="changeThemeFun('gray');" title="gray">gray</div>
		<div onclick="changeThemeFun('metro');" title="metro">metro</div>
		<div onclick="changeThemeFun('bootstrap');" title="bootstrap">bootstrap</div>
		<div onclick="changeThemeFun('black');" title="black">black</div>
		<div class="menu-sep"></div>
		<div onclick="changeThemeFun('cupertino');" title="cupertino">cupertino</div>
		<div onclick="changeThemeFun('dark-hive');" title="dark-hive">dark-hive</div>
		<div onclick="changeThemeFun('pepper-grinder');" title="pepper-grinder">pepper-grinder</div>
		<div onclick="changeThemeFun('sunny');" title="sunny">sunny</div>
		<div class="menu-sep"></div>
		<div onclick="changeThemeFun('metro-blue');" title="metro-blue">metro-blue</div>
		<div onclick="changeThemeFun('metro-gray');" title="metro-gray">metro-gray</div>
		<div onclick="changeThemeFun('metro-green');" title="metro-green">metro-green</div>
		<div onclick="changeThemeFun('metro-orange');" title="metro-orange">metro-orange</div>
		<div onclick="changeThemeFun('metro-red');" title="metro-red">metro-red</div>
	</div> -->
	 <!-- 
	<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
		<div onclick="editCurrentUserPwd();">修改密码</div>
		<div onclick="logoutFun(true);">退出系统</div>
	</div>
	 -->
	 
</div>

