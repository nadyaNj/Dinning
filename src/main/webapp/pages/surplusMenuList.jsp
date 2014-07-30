<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<fmt:setBundle basename="web-base/messages"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="content" id="rep">
	<h1>Surplus Menu List</h1>
	<form id="editSurpleMenu" class="clearfix" name="editSurpleMenu" method="post" action="<%=request.getContextPath()%>/actionServlet" >
		<table>
			<tr>
				<th>Date</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${surplusMenu}" var="surplusMenu">
				<tr>
					<%-- <input type="hidden" name="prodId" value="<c:out value="${date.id}"/>"> --%>
					<td><c:out value="${surplusMenu.surplusMenuActualDate}"/></td>
					<td>
						<c:if test="${surplusMenu.surplusMenuActualDate == todayDate}">
							<a class="edit" onclick='editSurplus(<c:out value='${surplusMenu.id}' />);' href="#" id='<c:out value='${surplusMenu.id}' />' >
								<fmt:message key="edit"/> 
							</a>
						</c:if>
						
						<c:if test="${surplusMenu.surplusMenuActualDate != todayDate}">
							<a class="edit" onclick='editSurplus(<c:out value='${surplusMenu.id}' />);' href="#" id='<c:out value='${surplusMenu.id}' />' >
								View
							</a>
						</c:if>
						
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</div>
</body>
</html>