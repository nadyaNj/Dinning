<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cashier.js">
</script>
<input type="hidden" id="us" name='<c:out value="${user.id}"/>' />
<input type="hidden" name="0" id="allPrice" value="0"/>
<input type="hidden"  id="userID" />
<%@include file='typeList.jsp'%>
	<div class="cashier">
		<table class="cash">
			<tr>
				<td><%@include file='cashierTodayMenu.jsp'%></td>
				<td><%@include file='basket.jsp'%></td>
			</tr>
		</table>
	</div>