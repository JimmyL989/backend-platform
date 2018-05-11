<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server   
 %>


<meta http-equiv="x-ua-compatible" content="IE=EmulateIE8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui_resource/extBrowser.js" charset="utf-8"></script>

<!-- 引入my97日期时间控件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyui_resource/My97DatePicker4.8b3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<!-- 引入jQuery -->
<script src="<%=request.getContextPath() %>/resources/easyui_resource/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
<!-- 引入bootstrap样式 -->
<link href="<%=request.getContextPath() %>/resources/easyui_resource/bootstrap-2.3.1/css/bootstrap.min.css" rel="stylesheet" media="screen">
<!-- 引入EasyUI <c:out value="${cookie.easyuiThemeName.value}" default="bootstrap"/>-->
<link id="easyuiTheme" rel="stylesheet" href="<%=request.getContextPath() %>/resources/easyui_resource/jquery-easyui-1.3.3/themes/default/easyui.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui_resource/jquery-easyui-1.3.3/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui_resource/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<!-- 引入EasyUI标准样式 -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/easyui_resource/jquery-easyui-1.3.3/themes/icon.css" type="text/css"/>
<!-- 修复EasyUI1.3.3中layout组件的BUG -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui_resource/jquery-easyui-1.3.3/plugins/jquery.layout.js" charset="utf-8"></script>
<!-- 引入EasyUI Portal插件 -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/easyui_resource/jquery-easyui-portal/portal.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui_resource/jquery-easyui-portal/jquery.portal.js" charset="utf-8"></script>
<!-- 扩展EasyUI -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui_resource/extEasyUI.js?v=201305241044" charset="utf-8"></script>
<!-- 扩展EasyUI Icon -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/easyui_resource/style/extEasyUIIcon.css?v=201305301906" type="text/css">
<!-- 扩展jQuery -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/easyui_resource/extJquery.js?v=201305301341" charset="utf-8"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/util.js" charset="utf-8"></script>

<script type="text/javascript">

</script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/myCss.css" type="text/css">