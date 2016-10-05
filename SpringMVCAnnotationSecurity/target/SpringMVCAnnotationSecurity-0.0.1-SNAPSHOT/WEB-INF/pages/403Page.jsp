<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page session="false"%>
<html>
<head>
<title>Access Denied</title>
</head>
<body>
<jsp:include page="_menu.jsp"/>
 
    <h3 style="color:red;">${message}</h3>
</body>
</html>