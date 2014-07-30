<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<%@ page import="java.io.File" %>
<fmt:setBundle basename="web-base/messages"/>

<c:forEach items="${addedProdList }" var="addedProd">

	
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


</c:forEach>