<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/userFunctions.js">
</script>
<!-- start inputs parameters in user page -->
	<div class='content' id='userEdit'>
		<%@include file='userChangeRol.jsp'%> 
	</div>
<!-- end inputs parameters in user page  -->

<!-- start inputs parameters in user page -->
	<div class="content" id="showUsers">
		<%@include file='usersShowPage.jsp'%>
	</div> 
<!-- end inputs parameters in user page  -->	
