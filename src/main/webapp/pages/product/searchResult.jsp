<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<%@ page import="java.io.File" %>
<fmt:setBundle basename="web-base/messages"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/productFunctions.js">
</script>
</head>

<body>
<c:choose>
	<c:when test="${not empty page.productList && not empty page.pageCount }">
	<div class="paddingTB5 clearfix">
		<a href="#" class="move-to-menu fright">Move to menu selected items</a>
	    <a href="#" onclick="deleteAllClicked();" class="delete-all fleft" id="deleteAllButton" name="delete" >Delete all items</a>
	</div>	
	<p style="text-align: center; color: red"><c:out value="${deleteError}" /></p>
	<form name="deleteForm" method="post" action="<%=request.getContextPath()%>/actionServlet">
	<input type="hidden" id="pagingFlag" value="viewPageWithNumber">
	<input type="hidden" name="pageFlag" value="deleteAll" />
	<input id="hiddenDelIds" type="hidden" name="delProductIds" value="" />
		<table id="searchProduct">
            <tr>
                <th width="20"><input id="all" onclick="checkboxChecked();" type="checkbox"/></th>
                <th></th>
                <th><fmt:message key="product"/></th>
                <th><fmt:message key="price"/>(AMD)</th>
                <th><fmt:message key="sort"/></th>
                <th><fmt:message key="mesurement"/></th>
                <th><fmt:message key="code"/></th>
                <th><fmt:message key="description"/></th>
                <th width="200px"><fmt:message key="action"/></th>
            </tr>
            <c:forEach items="${page.productList}" var="add">
            <c:choose>
				<c:when test="${add.hidden == true }">
					<tr style="background:#C0C0C0 ; ">
				</c:when>
				<c:otherwise>
					<tr>
				</c:otherwise>
			</c:choose>
			<td>
				<input class="case" id="check" onclick="uncheck();"  type="checkbox" name='<c:out value="${add.id}"/>'/>
			</td>
			<td class="center">
				<c:if test="${add.imgUrl == 'noImage.jpg' }">
					<img width="57" height="63" src="<%=request.getContextPath()%>/pictures/<c:out value="${add.imgUrl}"/>"/>
				</c:if>
				<c:if test="${add.imgUrl != 'noImage.jpg' }">
					<img width="57" height="63" src="<%=request.getContextPath()%>/tmp/<c:out value="${add.imgUrl}"/>"/>
				</c:if>
			</td>
			<td title="<c:out value="${add.name}"/>">
				<p class="overflow"><c:out value="${add.name }"/></p>
				<c:forEach items="${noDelete}" var="noDel">
					<c:if test="${add.id == noDel}">
						<p style="color: red;">used</p>
					</c:if>
				</c:forEach>
			</td>
			<td><c:out value="${add.price }"/></td>
			<td title="<c:out value="${add.productTypesString }"/>"><p class="overflow" ><c:out value="${add.productTypesString }"/></p></td>
			<td>
				<c:forEach items="${measurement}" var="all">
					<c:if test="${all.id == add.measurementId}">
						<p title="<c:out value="${all.measurement}"/>" class="overflow2"><c:out value="${all.measurement}" />
					</c:if>
				</c:forEach>				
			</td>
			<td><c:out value="${add.code }"/></td>
			<td title="<c:out value="${add.description}"/>"><p class="overflow2"><c:out value="${add.description}"/></td>
			<td width="200px">
				<a class="edit" onclick='edit(<c:out value='${add.id}' />);' href="#" id='<c:out value='${add.id}' />'><fmt:message key="edit"/></a>
				<a class="delete" onclick='deletePr(<c:out value='${add.id}' />);' href="javascript: void(0)" id='<c:out value='${add.id}' />'><fmt:message key="delete"/></a>
				<c:choose>
					<c:when test="${add.hidden == false }">
						<a class="hide" onclick='hide(<c:out value='${add.id}' />, <c:out value='${add.hidden}' />);' id='hide' name="<c:out value='${add.id}' />" href="javascript: void(0);" ><fmt:message key="hide"/></a>
					</c:when>
					<c:otherwise>
						<a class="hide" onclick='hide(<c:out value='${add.id}' />, <c:out value='${add.hidden}' />);' id='hide' name="<c:out value='${add.id}' />"   href="javascript: void(0);" ><fmt:message key="unhide"/></a>
					</c:otherwise>
				</c:choose>	
			</td>
			</tr>
			</c:forEach>	
		</table>
	</form>	
		
	<!-- Start pagination -->
	<div class="pagination">		
	   	<%@include file='../pagination.jsp'%>       	
	</div>						
	<!-- End pagination -->	 
	
	</c:when>
	<c:otherwise>
		<div align="center" style="color: green;">
			<h3><fmt:message key="nosearch" /></h3>	
		</div>
	</c:otherwise>
</c:choose>	
</body>
</html>