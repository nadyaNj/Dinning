<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<c:choose>
		<c:when test="${page.pageCount < 4}">
			<c:forEach begin="1" end="${page.pageCount}" var="i">
				<c:choose>
					<c:when test="${i == page.pageNumber}">
						<a id="paging" name="pagingAction" title='<c:out value="${i}" />' href="javascript: void(0)" class='current'><c:out value="${i}" /></a>
					</c:when>
					<c:otherwise>
						<a id="paging" name="pagingAction" title='<c:out value="${i}" />' href="javascript: void(0)"><c:out value="${i}" /></a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:when>
		<c:otherwise>			 
			<c:set var="beg" value="1"/>
			<c:set var="go" value="3"/>
			<c:set var="num" value="${page.pageNumber}"/>	
			<c:set var="page1" value="${num-beg}"/>
			<c:set var="page2" value="${page1-beg}"/>
			<c:set var="page3" value="${num+beg}"/>
			<c:set var="going" value="${num+go}"/>
			<c:set var="backIng" value="${num-go}"/>	
			
				
			<c:choose>
				<c:when test="${num > 2}">
					<a id="paging" href="javascript: void(0)" title='1'>1</a>
					<a id="paging" href="javascript: void(0)" title='<c:out value="${backIng}" />'>...</a>
					<c:choose>
						<c:when test="${num == page.pageCount}">
							<a id="paging" href="javascript: void(0)" title='<c:out value="${page2}" />'><c:out value="${page2}" /></a>
							<a id="paging" href="javascript: void(0)" title='<c:out value="${page1}" />'><c:out value="${page1}" /></a>
							<a class='current' id="paging" href="javascript: void(0)" title='<c:out value="${num}" />'><c:out value="${num}" /></a>
						</c:when>
						<c:otherwise>
							<a id="paging" href="javascript: void(0)" title='<c:out value="${page1}" />'><c:out value="${page1}" /></a>
							<a class='current' id="paging" href="javascript: void(0)" title='<c:out value="${num}" />'><c:out value="${num}" /></a>
							<a id="paging" href="javascript: void(0)" title='<c:out value="${page3}" />'><c:out value="${page3}" /></a>
						</c:otherwise>
					</c:choose>												
					<c:if test="${page3 < page.pageCount}">
						<a id="paging" href="javascript: void(0)" title='<c:out value="${going}" />'>...</a>
						<a id="paging" href="javascript: void(0)" title='<c:out value="${page.pageCount}" />'><c:out value="${page.pageCount}" /></a>										
					</c:if>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${num == 2}">
							<a id="paging" href="javascript: void(0)" title='1'>1</a>
							<a id="paging" class="current"  href="javascript: void(0)" title='2'>2</a>
							<a id="paging" href="javascript: void(0)" title='3'>3</a>				
						</c:when>
						<c:otherwise>
							<a id="paging" class="current" href="javascript: void(0)" title='1'>1</a>
							<a id="paging" href="javascript: void(0)" title='2'>2</a>
							<a id="paging" href="javascript: void(0)" title='3'>3</a>							
						</c:otherwise>								
					</c:choose>
					<a id="paging" href="javascript: void(0)" title='<c:out value="${going}" />'>...</a>
					<a id="paging" href="javascript: void(0)" title='<c:out value="${page.pageCount}" />'><c:out value="${page.pageCount}" /></a>						
				</c:otherwise>
			</c:choose>						
		</c:otherwise>
	</c:choose>	
	<input type="hidden" id="pageEnd" value='<c:out value="${page.pageCount}" />' />	
</body>
</html>