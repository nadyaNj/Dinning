<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<%@ page import="java.io.File" %>
<fmt:setBundle basename="web-base/messages"/>
<head>
</head>
<body>

<c:choose>
	<c:when test="${empty editMenuId }">
		<h1>CREATE MENU</h1>
	</c:when>
	<c:otherwise>
		<h1>EDIT MENU</h1>
	</c:otherwise>
</c:choose>
<form onmousemove='showDatePicker();' id="createMenu" class="clearfix" name="createMenu" method="post" action="<%=request.getContextPath()%>/actionServlet">
<p align="center" style="color: red;" id="error-date"> </p>
<p align="center" style="color: red;" id="error-count"> </p>
<p align="center" style="color: red;" id="error-prod"></p>
<p align="center" style="color: red;" id="error-date"><c:out value="${error}"/></p>
<c:choose>
	<c:when test="${empty editMenuId }">
		<p><label>DATE*: </label><input   size="20" type="text" id="datepicker"  name="date" READONLY value='<c:out value="${menuDate}"></c:out>'>
	</c:when>
	<c:otherwise>
		<p><label>DATE*: </label><input   size="20" type="text" id="date"  name="date" READONLY value='<c:out value="${menuDate}"></c:out>'>
	</c:otherwise>
</c:choose>


<table id="newMenu">
 	<tr>
		
		<th></th>
		<th><fmt:message key="product"/></th>
		<th><fmt:message key="price"/>(AMD)</th>
		<th><fmt:message key="description"/></th>
		<th><fmt:message key="sort"/></th>
 		<th width="80px"><fmt:message key="action"/></th>
	</tr>
    <tr>

	</tr>
    <tr>

<c:forEach items="${addedProdList }" var="addedProd">
<c:forEach items="${menu.menuItemsBeanList }" var="menuItems">
<c:if test="${addedProd.id == menuItems.productId}">
	
<tr id='<c:out value="${addedProd.id}"/>'>

<td class="center">
	<c:if test="${addedProd.imgUrl == 'noImage.jpg' }">
		<img width="57" height="63" src="<%=request.getContextPath()%>/pictures/<c:out value="${addedProd.imgUrl}"/>"/>
	</c:if>
	<c:if test="${addedProd.imgUrl != 'noImage.jpg' }">
		<img width="57" height="63" src="<%=request.getContextPath()%>/tmp/<c:out value="${addedProd.imgUrl}"/>"/>
	</c:if>
</td>
<td title="<c:out value="${addedProd.name}"/>"><p class="overflow"><c:out value="${addedProd.name }"/></p></td>
<td><c:out value="${addedProd.price }"/></td>
<td title="<c:out value="${addedProd.description}"/>"><p class="overflow2"><c:out value="${addedProd.description}"/></td>

<td class="overflow2"><p title="<c:out value="${addedProd.productTypesString }"/>"><c:out value="${addedProd.productTypesString }"/></p>
		<input name='<c:out value="${addedProd.id}"/>' class="input-text-short" type="hidden" size="10" maxlength="8" >
	
</td>
<td width="80px">
	<a class="addToMenu" onclick='removePrFromMenu(<c:out value='${addedProd.id}' />);' href="#" id='<c:out value='${addedProd.id}' />'><b>Remove</b>
	</a>
				
</td>
<tr>
</c:if>
</c:forEach>
</c:forEach>

</tr>
</table>
<c:choose>
	<c:when test="${empty editMenuId }">
		<div class="button-line fright">
			<a href="#" class="button"  onclick='tvar("createMenuFlag");'> Create</a>		
		</div>
	</c:when>
	<c:otherwise>
		<div class="button-line fright">
			<a href="#" class="button"  onclick='saveThisMenu("updateMenuFlag");'> Update</a>		
		</div>
	</c:otherwise>
</c:choose>
		
</form> 
</body>
</html>