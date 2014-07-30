<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/productFunctions.js">
</script>

	<%@include file='topMenu.jsp'%>
	
	<c:choose>
		<c:when test="${user.roleId == '5'}">
			<%@include file='cashier/cashierHead.jsp'%>
		</c:when>
		<c:otherwise>
			<div id="replaceWith">
				<%@include file='todayMenu.jsp'%>
	  		</div>
		</c:otherwise>
	</c:choose>
	
	  
	  <%@include file='footer.html'%>
	
	
