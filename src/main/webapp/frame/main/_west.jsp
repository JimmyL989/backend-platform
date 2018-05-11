<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.platform.frame.config.CONFIG"%>
<%@ page import="java.util.*"%>
<%
	String east_ctxPath_ = request.getContextPath();
	Map secondMap = (Map)request.getSession().getAttribute(CONFIG.SECOND_MENU);
%>
<script type="text/javascript" src="<%=east_ctxPath_%>/resources/js/common/xixi.js"></script>
<script type="text/javascript" src="<%=east_ctxPath_%>/resources/js/common/simpla.jquery.configuration.js"></script>
<link rel="stylesheet" href="<%=east_ctxPath_%>/resources/css/menu_left.css" type="text/css">

<script type="text/javascript">

		//north切换权限时触发此方法
	function collspanNorth()
	{
		
		$(".layout-panel-west").animate({ "width": "200px" }, "slow");
		$(".layout-panel-west").css({ "display": "block" });
		$(".layout-panel-center").first().css({ "display": "block" });
		//var east_width = $(".layout-panel-east").css("width").replace("px","");
		var width = $(window).width();
		var center_width = width - 200; 
		//var center_width = width - east_width - 200; 
			
		$(".layout-panel-center").first().css({ "width": center_width+"px" });
		$("#main").css({"width":center_width+"px"});
		$("#index_tabs").css({"width":(center_width - 10 )+"px"});
		$(".tabs-wrap").css({"width":(center_width - 10 ) - 50 +"px"});
			
		$(".tabs-header").css({"width":(center_width - 10 )+"px"});
		$(".tabs-panels").css({"width":(center_width - 10 )+"px"});
		$(".tabs-panels .panel").css({"width":center_width+"px"});
		$(".tabs-panels .panel .panel-body").css({"width":(center_width - 10 )+"px"});
		$(".layout-panel-center").first().animate({  "left": "200px" }, "slow");
		$("#bar").css({ "display": "none" });
		
		$("#system_title").text("<c:out value='${sessionScope._ROOT_NAME_}' />");
		
		
	}
	
	function addTab(url,text) {
		var iframe = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
		
		var t = $('#index_tabs');
		var opts = {
			title : text,
			closable : true,
			content : iframe,
			border : false,
			fit : true
		};
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
			parent.$.messager.progress('close');
		} else {
			t.tabs('add', opts);
		}
	}
	function mouseIn(obj)
	{
		obj.style.cssText="outline: 1px; color: #861f95;";
	}
	function mouseOut(obj)
	{
		obj.style.cssText=" color: #000001;";
	}
	
	//收缩目录树
	function collspan()
	{
		var west_width = $(".layout-panel-west").css("width").replace("px","");
		//14px 已收缩  200px左侧宽度
		if(west_width != '14')
		{
			//$(".layout-panel-west").animate({ "width": "25px" }, "slow");
			
			$(".layout-panel-west").css({ "display" : "block" });
			$(".layout-panel-center").first().css({ "display" : "block" });
			//var east_width = $(".layout-panel-east").css("width").replace("px","");
			var width = $(window).width();
			//var center_width = width - east_width - 14;
			var center_width = width - 14;
			$(".layout-panel-center").first().css({ "width" : center_width+"px" });
			$("#main").css({"width" : center_width+"px"});
			$("#index_tabs").css({"width" : (center_width - 10 )+"px"});
			//设置横线宽度
			$(".tabs-wrap").css({"width" : center_width - 70 + "px"});
			
			$(".tabs-header").css({"width" : (center_width - 10 )+"px"});
			$(".tabs-panels").css({"width" : (center_width - 10 )+"px"});
			$(".tabs-panels .panel").css({"width" : (center_width - 10 )+"px"});
			$(".tabs-panels .panel .panel-body").css({"width" : (center_width - 10 )+"px"});
			$(".layout-panel-center").first().animate({  "left" : "14px" }, "fast");
			$("#bar").css({ "display": "block" });
			$(".layout-panel-west").css({"width" : "14px"}, "fast");
			//将箭头居中
			var top_height = ($(window).height() - 90 - 14) / 2;
			$("#west_top").css({"height" : top_height + "px" });
			//$(".layout-panel-west").animate({ "width": "25px" }, "slow");
		}
		else
		{
			$(".layout-panel-west").animate({ "width": "200px" }, "slow");
			$(".layout-panel-west").css({ "display": "block" });
			$(".layout-panel-center").first().css({ "display": "block" });
			//var east_width = $(".layout-panel-east").css("width").replace("px","");
			var width = $(window).width();
			var center_width = width - 200; 
			//var center_width = width - east_width - 200; 
			
			$(".layout-panel-center").first().css({ "width": center_width+"px" });
			$("#main").css({"width":center_width+"px"});
			$("#index_tabs").css({"width":(center_width - 10 )+"px"});
			$(".tabs-wrap").css({"width":(center_width - 10 ) - 50 +"px"});
			
			$(".tabs-header").css({"width":(center_width - 10 )+"px"});
			$(".tabs-panels").css({"width":(center_width - 10 )+"px"});
			$(".tabs-panels .panel").css({"width":center_width+"px"});
			$(".tabs-panels .panel .panel-body").css({"width":(center_width - 10 )+"px"});
			$(".layout-panel-center").first().animate({  "left": "200px" }, "slow");
			$("#bar").css({ "display": "none" });
		}
	}


		//点击item事件,点击+所有变-，-变+
	var flagtitle = -1;
	//点击相同item判断是否展开
	var flag = false;
	
	$(".nav-top-item").click( 
		function () {
			var title = $(this).attr("title");
			
			
			//全部+			
			$(".nav-top-item").each(function(index, domEle){

				$(domEle).css("background","url(<%=east_ctxPath_%>/resources/image/left_menu_bg02.png) left center no-repeat");
					
			});
			
			if(title == flagtitle)
			{
				if(flag)
				{
					$(this).css("background","url(<%=east_ctxPath_%>/resources/image/left_menu_bg01.png) left center no-repeat");
					flag = false;
				}
				else
				{
					$(this).css("background","url(<%=east_ctxPath_%>/resources/image/left_menu_bg02.png) left center no-repeat");
					flag = true;
				}
			}
			else
			{
				$(this).css("background","url(<%=east_ctxPath_%>/resources/image/left_menu_bg01.png) left center no-repeat");
				flagtitle = title;
				flag = false;//归0
			}
			
		}
	);
</script>
<style>
	.itemover
	{ 
		width:180px; height:40px;display:block; text-decoration:none; 
		background:url(<%=east_ctxPath_%>/resources/image/images/left_menu_bg01.gif) left center no-repeat; color:#fff;
	}
</style>
<!-- left bar -->
<div id="bar" style="height: 100%;background: #fff; width: 14px; display: none;">
	<div id="west_top"></div>
	<a href="javascript:collspan();">
		<img src="<%=east_ctxPath_%>/resources/image/right.png"/>
	</a>
</div>
<!-- 当前用户  -->
<div style="background-image: url(<%=east_ctxPath_%>/resources/image/left_bottom_bg.png);height:55px;margin-left:2px;margin-right:2px;margin-top:2px;margin-bottom:2px">
	<div style="height: 10px"></div>
	<table>
		
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td valign="top"><img src="<%=east_ctxPath_%>/resources/image/admin_img.png"/></td>
			
			<td align="left">
				<font style="font-family: microsoft yahei;font-weight: bold;" color="#ffffff" size="2" style="text-decoration: none">
					您好：<c:out value="${sessionScope.logon_sysuser.username}"  />
				</font>
				<br/>
				<a href="${pageContext.request.contextPath}/authorization/controller/logoff.do" style="text-decoration: none">
					<font style="font-family: microsoft yahei;" color="#ffce5a"  >[注销]</font>
				</a>&nbsp;&nbsp;
				<a href="javascript:editCurrentUserPwd();" onclick="javascript:editCurrentUserPwd();" style="text-decoration: none">
					<font style="font-family: microsoft yahei;" color="#ffce5a" style="text-decoration: none" >[修改密码]</font>
				</a>&nbsp;&nbsp;
				
			</td>
		</tr>
	</table>
</div>
 <!-- 菜单项 -->
<%-- <div  style="background-image: url(<%=east_ctxPath_%>/resources/image/left_menu_bg_top.png);height:42px;margin-left:1px;margin-right:2px">
	<div style="height: 10px"></div>
		<table>
			<tr>
				<td>&nbsp;&nbsp;</td>
				<td align="left">
					<img src="<%=east_ctxPath_%>/resources/image/left_menu_top_icon.gif">
				</td>
				<td width="140px">
					<font color="#ffffff" style="font-family: microsoft yahei;font-weight: bold;" size="3">
						<div  id="system_title"><c:out value="${sessionScope._ROOT_NAME_}"  /></div>
					</font>
				</td>
				<td align="right">
					<a href="javascript:collspan();" >
						<img src="<%=east_ctxPath_%>/resources/image/left_menu_leftArrow.png">
					</a>
				</td>
			</tr>
	</table>
</div> --%>
<div style="background:#00a3ff url(<%=east_ctxPath_%>/resources/image/left_bottom_bg.png) no-repeat bottom; overflow-y: auto; overflow-x:hidden;margin-left:2px;margin-right:2px;">
	<div id="sidebar">
		<div id="scrollBar" style="width:200px;">
	    	<div id="sidebar-wrapper" class="menu_left">                  
	        	<ul id="main-nav">
	        		 <%
	        		  if("null".equals(session.getAttribute("_ROOT_FLAG_")))
	        		   		return;
	                  int index = Integer.parseInt(session.getAttribute("_ROOT_FLAG_")+"");
	                  List secondList = (List)(secondMap.get(index));
	                  for (int i = 0; i < secondList.size(); i++) { 
	                  	int parentid = Integer.parseInt(String.valueOf(((Map)secondList.get(i)).get("sys_privilege_id")));
	                  %>
		             <li style=" padding-left:15px;">
		             	<a href="#" class="nav-top-item" style="padding-right:25px;text-align: left" title="<%=String.valueOf(((Map)secondList.get(i)).get("name")) %>">
		             		&nbsp;&nbsp;
		             		<%=String.valueOf(((Map)secondList.get(i)).get("name"))%>
		             	</a>
		               	<ul>
                       		<li style="width:186px;margin-left:1px; margin-bottom:-5px; font-size:0; line-height:9px; height:9px; background:url(<%=request.getContextPath()%>/resources/image/sub_bg1.png) no-repeat;">
                       		</li>
		                  	<%
		                  		List jList = (List)secondMap.get(parentid);
		                  		if(jList!=null) {
			                 	for(int q = 0; q < jList.size(); q++) {
			                 		Map qMap = (Map)(jList).get(q);	      
			                 		
		                  	%>
		                	<li style="margin-left:1px">
		                		<a onclick="javascript:addTab('<%=east_ctxPath_+qMap.get("url")%>','<%=qMap.get("name")%>');"  
		                			href="#" style="width:186px; font-size:13px; font-weight:normal; height:26px; padding:0; margin:0 0 0 4px; line-height:26px; text-align:left;">
		                			<span style="margin-left:10px "><img src="<%=request.getContextPath()%>/resources/image/u56_normal.png"></span>
		                			<%=qMap.get("name")%>
		                		</a>
		                	</li>
		                  	<%
		                  			}
		                  		}
		                  	%>
                            <li style="width:186px;margin-left:1px; line-height:9px; font-size:0; height:9px; background:url(<%=request.getContextPath()%>/resources/image/sub_bg3.png) no-repeat;">
                            </li>
		                  </ul>
		             </li>
	                  <%
	                  } 
	                  %>
	        	</ul>
	        </div>
	    </div>
	</div>
</div>

 