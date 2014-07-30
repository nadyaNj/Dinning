<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<div class="content">
		<c:choose>
			<c:when test="${ empty errorTodayMenu }">
					<h1>Menu for <c:out value="${menuDate}"/> </h1>
		<table>
	            <tr>
	                <th></th>
	                <th>Name</th>
	                <th>Price<span class="weight-normal">(AMD)</span></th>
	                <th>Type</th>
	                <th>Code</th>
	                <th>Measure</th>
	            </tr>
	             <c:forEach items="${addedProdList}" var="add">
	            
	            <tr>
	                <td class="center">
	                	<c:if test="${add.imgUrl == 'noImage.jpg' }">
							<img width="57" height="63" src="<%=request.getContextPath()%>/pictures/<c:out value="${add.imgUrl}"/>"/>
						</c:if>
						<c:if test="${add.imgUrl != 'noImage.jpg' }">
							<img width="57" height="63" src="<%=request.getContextPath()%>/tmp/<c:out value="${add.imgUrl}"/>"/>
						</c:if>
					</td>
	                <td class="overflow"><p title="<c:out value="${add.name }"/>"><c:out value="${add.name }"/></p></td>
	                <td class="overflow"><p title="<c:out value="${add.price }"/>"><c:out value="${add.price }"/></p></td>
	                <td class="overflow2"><p title="<c:out value="${add.productTypesString }"/>"><c:out value="${add.productTypesString }"/></p></td>
	                <td class="overflow2"><p title="<c:out value="${add.code }"/>"><c:out value="${add.code }"/></p></td>
	                <td class="overflow2">
		                <c:forEach items="${measurement}" var="all">
							<c:if test="${all.id == add.measurementId}">
								<p title="<c:out value="${all.measurement}"/>" class="overflow2"><c:out value="${all.measurement}" />
							</c:if>
						</c:forEach>	
					</td>
	            </tr>
	            </c:forEach>
	     </table>
			</c:when>
			<c:otherwise>
				 <p align="center" style="color: red;"><c:out value="${errorTodayMenu}"/></p> 
			</c:otherwise>
		</c:choose>
	     </div>