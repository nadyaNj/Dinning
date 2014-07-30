<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dinner room</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.js">
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/help.js">
</script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
</head>
<body>
	<div id="outer-wrapper">
		<h1 class="logo"><a href="#"></a></h1>     	
	</div>
	<div class="content" style="width:400px;">
		<span style="color:red;"><c:out value="${error}" /></span>
		<form action="<%=request.getContextPath()%>/actionServlet" id="userUpdate" method="post">
			<table style="width:400px;">
				<tr>
					<th style="text-align: center" colspan="2" style="">Update User</th>				
				</tr>
				<tr>
					<td>UserName:</td>
					<td><c:out value="${user.userName}" /></td>				
				</tr>
				<tr>
					<td>Company:</td>
					<td><c:out value="${user.companyName}" /></td>
				</tr>
				<tr>
					<td>Department:</td>
					<td><c:out value="${user.departmentName}" /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><c:out value="${user.userEmail}" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input class="input-text" type="password" name="newPassword" /></td>
				</tr>
				<tr>
					<td>Confirm Password:</td>				
					<td><input class="input-text" type="password" name="confirmPassword" /></td>
				</tr>			
			</table>
			<br class="clearall">
			<a href="#" class="button" onclick="submitForm('userUpdate');">Update</a>
			<input type="hidden" name="pageFlag" value="updateUserPassword" />
			<input type="hidden" name="userId" value='<c:out value="${user.id}" />' />
		</form>			
	</div>
	<div class="footer" style="width:420px;">
	   	IU Networks. All Rights Reserved.
	</div>
</body>
</html>