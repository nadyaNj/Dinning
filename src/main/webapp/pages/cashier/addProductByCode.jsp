<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>

<c:if test="${not empty product}">
	<tr name='<c:out value="${product.discountPrice}"/>' onclick="removeBasketItem(this);" id='<c:out value="${product.id}"/>' title='<c:out value="${product.price}"/>'>
	<input type="hidden" id="addItemByCode" name='<c:out value="${product.id}"/>' value='<c:out value="${product.price}"/>' title='<c:out value="${product.discountPrice}"/>'/>
		<td><c:out value="${product.name}"/></td>
		<td id="disPrice"><c:out value="${product.price}"/></td>
		<td><a class="delete" href="javascript: void(0)"> </a></td>
	 </tr>
</c:if>

<input type="hidden" id="userName" name='<c:out value="${getUser.id }"/>' value='<c:out value="${getUser.userName }"/>' />
