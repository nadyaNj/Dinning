<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<option value="0">None</option>
	<c:choose>
		<c:when test="${not empty dep}">			
			<c:forEach items="${dep}" var="allDepartaments">
				<c:choose>
					<c:when test="${userBean.userDepId == allDepartaments.id}">
						<option value='<c:out value="${allDepartaments.id}" />' selected="selected"><c:out value="${allDepartaments.departamentName}" /></option>
					</c:when>
					<c:otherwise>
						<option value='<c:out value="${allDepartaments.id}" />'><c:out value="${allDepartaments.departamentName}" /></option>
					</c:otherwise>
				</c:choose>				
			</c:forEach>					
		</c:when>
		<c:otherwise>
			<c:forEach items="${departament}" var="allDep">
				<option value='<c:out value="${allDep.id}" />'><c:out value="${allDep.departamentName}" /></option>
			</c:forEach>			
		</c:otherwise>
	</c:choose>
</body>
</html>