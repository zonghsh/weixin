<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>WeUI Demo</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Bootstrap -->
	<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/css/weui.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/weui/css/example.css"/>
	
	<link rel="import" href=""/>
	
	<script src="<%=request.getContextPath() %>/js/jquery-3.1.0.min.js"></script>
    
</head>
<body ontouchstart>
    <div class="container" id="container"></div>
    <%@ include file="./home.jsp"  %>
    <%@ include file="./button.jsp" %>
    <%@ include file="./cell.jsp" %>
    <%@ include file="./toast.jsp" %>
    <%@ include file="./dialog.jsp" %>
    <%@ include file="./progress.jsp" %>
    <%@ include file="./msg.jsp" %>
    <%@ include file="./article.jsp" %>
    <%@ include file="./tab.jsp" %>
    <%@ include file="./navbar.jsp" %>
    <%@ include file="./tabbar.jsp" %>
    <%@ include file="./panel.jsp" %>
    <%@ include file="./actionsheet.jsp" %>
    <%@ include file="./icons.jsp" %>
    <%@ include file="./searchbar.jsp" %>
    
    <script src="<%=request.getContextPath() %>/weui/js/zepto.min.js"></script>
    <script src="<%=request.getContextPath() %>/weui/js/router.min.js"></script>
    <script src="<%=request.getContextPath() %>/weui/js/example.js"></script>
</body>
</html>