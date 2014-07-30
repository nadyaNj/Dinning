<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<%@ page import="java.io.File" %>
<fmt:setBundle basename="web-base/messages"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/surpleMenu.js"></script>--%>
</head> 

<body>
<div class="content" id="rep">
<h1>Create Surplus Menu</h1>
<form id="createSurpleMenu" class="clearfix" name="createSurpleMenu" method="post" action="<%=request.getContextPath()%>/actionServlet">
	<p align="center" style="color: red;" id="error-count"> </p>
	<p align="center" style="color: red;" id="error-prod"></p>
	<table id="surpleMenu">
	 	<tr>
			<th></th>
			<th><fmt:message key="product"/></th>
			<th><fmt:message key="price"/>(AMD)</th>
			<th><fmt:message key="description"/></th>
			<th><fmt:message key="count"/></th>
		</tr>
    
			<c:forEach items="${addedProdList}" var="addedProd">
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
					<td>
						<c:forEach items="${menu}" var="menu">
							<c:if test="${addedProd.id == menu.surplusProductId}">
								<input name='<c:out value="${addedProd.id}"/>' value='<c:out value="${menu.surplusProductCount}"/>' class="input-text-short" type="text" size="10" maxlength="8"  />
							</c:if>
						</c:forEach>
						<input name='<c:out value="${addedProd.id}"/>' class="input-text-short" type="text" size="10" maxlength="8"  />
					
					</td>
				<tr>				
			</c:forEach>
		
	</table>
	<div class="button-line fright">
		<a href="#" class="button"  onclick='save();'>Save</a>		
	</div>
</form> 
</div>
</body>
</html>