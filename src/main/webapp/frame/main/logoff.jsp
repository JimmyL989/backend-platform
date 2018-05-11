<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.platform.frame.config.CONFIG"%>
<%
if (session.getAttribute(CONFIG.LOGON_USER)!=null)
{
	System.out.println("--------------------------------销毁session----------------------------------");
	session.invalidate();
}
%>
