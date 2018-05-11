<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/frame/common/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%
String contextP = request.getContextPath();
%>
<html>
	<head>
	<meta http-equiv="x-ua-compatible" content="IE=EmulateIE8" />
	<title>Agate</title>
	<script>
	function checkLeave(){
		/* $.post('${pageContext.request.contextPath}/frame/main/logoff.jsp'); */
		
		var userAgent = navigator.userAgent.toLowerCase();  
		if(userAgent.indexOf("msie")>-1) { //IE  
			var n = window.event.screenX - window.screenLeft;　　
			var b = n > document.documentElement.scrollWidth - 40;
			if((b && window.event.clientY < 0) || (window.event.screenY > document.body.clientHeight) || window.event.altKey) { 
				$.ajax({ url: '${pageContext.request.contextPath}/authorization/controller/logoff.do', async: false }); //这里可以放置你想做的操作代码 
				//window.event.returnValue = $.ajax({ url: '${pageContext.request.contextPath}/authorization/controller/logoff.do', async: false }); //这里可以放置你想做的操作代码 
			}else{ 
		
			} 
		}else { //FireFox Chrome  
			$.ajax({ url: '${pageContext.request.contextPath}/authorization/controller/logoff.do', async: false }); //这里可以放置你想做的操作代码
		}  
       	
	}
	</script>
<script type="text/javascript">
	var index_tabs;
	var index_tabsMenu;
	var index_layout;
	$(function() {
		index_layout = $('#index_layout').layout({
			fit : true
		});
		/*index_layout.layout('collapse', 'east');*/

		index_tabs = $('#index_tabs').tabs({
			fit : true,
			border : false,
			onContextMenu : function(e, title) {
				e.preventDefault();
				index_tabsMenu.menu('show', {
					left : e.pageX,
					top : e.pageY
				}).data('tabTitle', title);
			}
		});

		index_tabsMenu = $('#index_tabsMenu').menu({
			onClick : function(item) {
				var curTabTitle = $(this).data('tabTitle');
				var type = $(item.target).attr('title');

				if (type === 'refresh') {
					index_tabs.tabs('getTab', curTabTitle).panel('refresh');
					return;
				}

				if (type === 'close') {
					var t = index_tabs.tabs('getTab', curTabTitle);
					if (t.panel('options').closable) {
						index_tabs.tabs('close', curTabTitle);
					}
					return;
				}

				var allTabs = index_tabs.tabs('tabs');
				var closeTabsTitle = [];

				$.each(allTabs, function() {
					var opt = $(this).panel('options');
					if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
						closeTabsTitle.push(opt.title);
					} else if (opt.closable && type === 'closeAll') {
						closeTabsTitle.push(opt.title);
					}
				});

				for ( var i = 0; i < closeTabsTitle.length; i++) {
					index_tabs.tabs('close', closeTabsTitle[i]);
				}
			}
		});
	});
	

	

</script>
</head>
<body onbeforeunload="checkLeave()">

	<div id="index_layout">
		<div data-options="region:'north',href:'<%=contextP %>/frame/main/north.jsp'" style="height: 90px; overflow: hidden;"></div>
		<div id="west" data-options="region:'west',title:'${sessionScope._ROOT_NAME_}',href:'<%=contextP %>/frame/main/_west.jsp'" 
			style="width: 200px; overflow: hidden;display: block;">
		</div>
		<div data-options="region:'center'" title="" style="overflow: hidden;">
			<div id="index_tabs" style="overflow: hidden;">
				<div title="主页" data-options="border:false" style="overflow: hidden;">
				</div>
			</div>
		</div>
	</div>

	<div id="index_tabsMenu" style="width: 120px; display: none;">
		<div title="refresh" data-options="iconCls:'transmit'">更新</div>
		<div class="menu-sep"></div>
		<div title="close" data-options="iconCls:'delete'">关闭</div>
		<div title="closeOther" data-options="iconCls:'delete'">关闭其他</div>
		<div title="closeAll" data-options="iconCls:'delete'">关闭全部</div>
	</div>

</body>
</html>