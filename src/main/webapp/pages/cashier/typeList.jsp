<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
	$(function() {
		$( "#tabs" ).tabs();
	});
</script>

<form method="post" class="clearfix" id="cashierPage" action="<%=request.getContextPath()%>/actionServlet" enctype="multipart/form-data">
<input type="hidden" name="pageFlag" value="loginPage">
<c:choose>
<c:when test="${not empty size}">
<div class="content">
<div id="tabs">
	<ul>
		<li><a onclick="showTodayMenuInCashierPage();" id="tabTodayMenu" href="#tab">Today Menu</a></li>
		<c:choose>
			<c:when test="${size < 5}">
				<c:forEach items="${type}" var="all">
					<li><a id="tabType" name='<c:out value="${all.id}" />' href="#tab"><c:out value="${all.type}" /></a></li>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach var='i' begin='0' end="4">
					<li><a id="tabType" name='<c:out value="${type[i].id}" />' href="#tab"><c:out value="${type[i].type}" /></a></li>
				</c:forEach>
				<span id="topMenu" class="cols sbros">
					<li class="sublnk ui-state-default">	
						<a href="#">&#62;&#62;</a>					
						<ul style="display: none;">
							<c:forEach begin="5" end="${size - 1}" var="i">
								<li class="ui-state-default ui-corner-top"><a id="tabType" name='<c:out value="${type[i].id}" />' href="#tab"><c:out value="${type[i].type}" /></a></li>
							</c:forEach>
						</ul>
					</li>
				</span>				
			</c:otherwise>			
		</c:choose>
	</ul>			
</div>	
</div>
</c:when>
<c:otherwise>
<script>
	$(function() {
		document.getElementById('cashierPage').submit();
	});
</script>
</c:otherwise>
</c:choose>
</form>
