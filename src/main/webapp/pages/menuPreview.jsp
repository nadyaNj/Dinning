<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<%@ page import="java.io.File" %>
<fmt:setBundle basename="web-base/messages"/>

<div class="content" id="rep">
<h1 id="menuDate">MENU FOR <c:out value="${menuDate}"></c:out></h1>
<form id="saveMenu" class="clearfix" name="saveMenu" method="post" action="<%=request.getContextPath()%>/actionServlet">
<input id="date" type="hidden" value="<c:out value="${menuDate}"></c:out>">

<table id="newMenu">
 <tr>
    <th></th>
    <th></th>
    <th><fmt:message key="product"/></th>
    <th><fmt:message key="price"/>(AMD)</th>
    <th><fmt:message key="description"/></th>
    <th><fmt:message key="sort"/></th>
                
 </tr>
<c:forEach items="${addedProdList }" var="addedProd">
<c:forEach items="${menu.menuItemsBeanList }" var="menuItems">
<c:if test="${addedProd.id == menuItems.productId}">
<tr id='<c:out value="${addedProd.id}"/>'>
	<td>
		
	</td>
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
	<td >
		<input type="hidden" name='<c:out value="${addedProd.id}"/>' >
		<c:out value="${addedProd.productTypesString}"></c:out>
	
	</td>
	
</tr>
</c:if>
</c:forEach>
</c:forEach>
</table>
<c:if test="${empty menuId}">
		<div class="button-line fright">
			<a href="#" class="button"  onclick='saveThisMenu("saveMenuFlag");'>SAVE</a>
			<a href="#" class="button"  onclick='cancelSaveMenu();'>CANCEL</a>		
		</div>
</c:if>
</form> 
</div>
