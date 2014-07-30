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
</head>
<body>
<div id="outer-wrapper">    
<div class="content" id="rep">
    <h1>Search product</h1>
<div class="additional-block">
<form id="searchForm" class="clearfix" name="searchForm"  method="post" action="<%=request.getContextPath()%>/actionServlet">
<input type="hidden" name="pageFlag" value="searchPage" id="pageFlag" />
<input type="hidden" id="pageNumber" name="pageNumber">
<c:if test="${empty type}">
	<p style="color: red;" align="center" > Please create type or/and measurement </p>
</c:if>
<div class="form-item clearfix">
    <label><fmt:message key="sort"/></label>
    <c:choose>
	<c:when test="${empty type}">
		<select name="type" multiple="multiple" size="4">
			<option> No Types </option>
		</select>
	</c:when>
	<c:otherwise>
	<c:choose>
		<c:when test="${not empty save.typeIdes }">
		<select name="type" multiple="multiple" size="4">
		<c:forEach items="${type}" var="allTypes">
			<c:forEach items="${save.typeIdes}" var="allId">
				<c:if test="${allId == allTypes.id}">
					<option style="color: #999999;" value='<c:out value="${allTypes.id}" />' selected>
						<c:out value="${allTypes.type }" />
					</option>
				</c:if>
			</c:forEach>
		</c:forEach>
		<c:forEach items="${noSelectTypes}" var="allType">
			<option value='<c:out value="${allType.id}" />'>
				<c:out value="${allType.type}" />
			</option>
		</c:forEach>
		</select>
		</c:when>
		<c:otherwise>
			<select name="type" multiple="multiple" size="4">
				<c:forEach items="${type}" var="allTypes">
					<option value='<c:out value="${allTypes.id}" />' >
						<c:out value="${allTypes.type }" />
					</option>
				</c:forEach>
			</select>
		</c:otherwise>
		</c:choose>
	</c:otherwise>
	</c:choose>
</div>
<div class="form-item clearfix">
   	<label><fmt:message key="price"/></label>
	<input class="input-text-short" type="text" size="5" maxlength="10" id="priceMin" name="priceMin"/>
	<input class="input-text-short" type="text" size="5" maxlength="10" id="priceMax" name="priceMax"/>
</div>
<div class="form-item clearfix">
   	<label><fmt:message key="code"/></label>
	<input class="input-text"   type="text" id="code" name="code"/>
</div>
<br clear="all" />
	<div class="form-item clearfix">
		<label><fmt:message key="product"/></label>
		<input class="input-text" type="text" id="name" name="name" size="32" />
	</div>
          <div class="form-item clearfix">
          	<label><fmt:message key="description"/></label>
			<input class="input-text" type="text" maxlength="200" id="description" name="description" />	
         </div>
         <div class="form-item clearfix">
         	<label>Hidden Products</label>					
			<input type="checkbox" id="hidden" name="hidden"/> Hidden<br />				
         </div>
         <br clear="all" />
        <div class="button-line fright">
			<a href="#" class="button" onclick='searchProduct();' id="searchProduct" >Search</a>
            <a href="#" class="button"  onclick="resetSearchPage();">Reset</a>
		</div>                
</form>
</div>
</div>

<div class='content'>
	<div id="productTen">	
		<%@include file='searchResult.jsp'%>
	</div>
</div>
</div>
</body>
</html>