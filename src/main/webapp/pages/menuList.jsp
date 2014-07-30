<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<%@ page import="java.io.File" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<div id="productTen">
<div class="content" id="rep">
<h1>MENU LIST</h1>
<input type="hidden" id="pagingFlag" value="viewMenuListPaging">
<input type="hidden" id="pageFlag" value="menuList">
<input type="hidden" id="todayDate" value='<c:out value="${todaysDate}"></c:out>'>
<c:choose>
	<c:when test="${not empty page.productList}">
<table id="menuList">

	<tr>
		<th>Menu Date</th>
		<th>Creator username</th>

	</tr>
			<c:forEach items="${page.productList}" var="menu">
	<tr>
	
		<td>
			<h2><a href="#" onclick="menuViewEdit(<c:out value="${menu.id}"></c:out>)" id='<c:out value="${menu.id}"></c:out>' name="<c:out value="${menu.menuActualDate}"></c:out>"><c:out value="${menu.menuActualDate}"></c:out></a></h2>
		</td>
			
		<td>
			<i><c:out value="${menu.menuUserName}"></c:out></i>
		</td>
	</tr>
		</c:forEach>
</table>
<!-- Start pagination -->
	<div class="pagination">		
		<%@include file='pagination.jsp'%>       	
	</div>						
	<!-- End pagination -->
	</c:when>
	<c:otherwise>
		<div align="center" style="color: green;">
			<h3>There are not menu created</h3>	
		</div>
	</c:otherwise>
</c:choose>
</div>
</div>
