<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
	response.setHeader("Pragma","no-cache"); //HTTP 1.0    
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server   
	String ctxPath = request.getContextPath();
%>
<html>
<head>
	<title></title>
	<link href="<%=ctxPath%>/resources/css/login.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		function onLogin() {
			document.forms[0].submit();
		}

		function hiddenPass(e) {
			e = e ? e : window.event;
			var kcode = e.which ? e.which : e.keyCode;
			var pass = document.getElementById("passwd1");
			var j_pass = document.getElementById("password");
			if (kcode != 8) {
				var keychar = String.fromCharCode(kcode);
				j_pass.value = j_pass.value + keychar;
				j_pass.value = j_pass.value.substring(0, pass.length);
			}
		}
		function checkKey()
		{
		  if(13 == window.event.keyCode)
		  {
		  	onLogin();
		  }
		}
		document.onkeydown=checkKey;
	</script>
</head>
<body >
	<form id="logonForm" method="post" action="<%=ctxPath%>/authorization/controller/logon.do">
	<div class="login">
		<div class="input">
			<ul>
			<li class="notice">
			<c:if test="${ message != '' }">
				<c:out value="${ message }"></c:out>
			</c:if>
			</li>
				<li>
				<img src="<%=ctxPath%>/resources/image/font1.png" width="30" height="14" />
				<input class="user_in" name="usercode" id="usercode" type="text" />
				</li>
				<li>
				<img src="<%=ctxPath%>/resources/image/font2.png" width="30" height="14" />
				
				
				<input type="text" id="passwd1" class="user_in" onkeypress="javascript:hiddenPass(event)" onkeyup="this.value=this.value.replace(/./g,'*');"/></li>
				<input id="password" type="hidden" name="password"/>
				
				<li class="but_login">
				<input type="button" onclick="onLogin()" style="background:url(<%=ctxPath%>/resources/image/button.png) left top no-repeat; width:65px; height:26px;  border:0px; cursor:pointer;" value="" />
				</li>
			</ul>
			
			
		</div><!--input end -->
								
	</div><!--login end -->
</form>
</body>
</html>