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
<div id="productTen">
<form name="usersForm" method="post" action="<%=request.getContextPath()%>/actionServlet">
	<span id="errorRed">
		<c:if test="${not empty errors}">
			<span style="color: red;" id="errorMessage">
				<c:forEach items="${errors}" var="allErrors">
					<c:out value="${allErrors}" />
				</c:forEach>
			</span>
		</c:if>
	</span>
	<table>
		<tr height="10px">
			<th>UserName</th>
			<th>Role</th>
			<th>Email</th>
			<th>Company</th>
			<th>Department</th>
			<th>Position</th>
			<th>state</th>	
			<th>Action</th>		
		</tr>
		
		<c:forEach items="${page.productList}" var="allUser">
			<tr height="10px">
				<td title="<c:out value="${allUser.userName}" />"><p class="overflow"><c:out value="${allUser.userName}" /></p></td>
				<td>
					<c:if test="${allUser.roleId == 2}">
						cooker
					</c:if>		
					<c:if test="${allUser.roleId == 3}">
						hr
					</c:if>
					<c:if test="${allUser.roleId == 4}">
						user
					</c:if>		
					<c:if test="${allUser.roleId == 5}">
						cashier
					</c:if>				
				</td>
				<td title="<c:out value="${allUser.userEmail}" />"><p class="overflow2"><c:out value="${allUser.userEmail}" /></p></td>
				<td title="<c:out value="${allUser.companyName}" />"><p class="overflow"><c:out value="${allUser.companyName}" /></p></td>
				<td title="<c:out value="${allUser.departmentName}" />"><p class="overflow"><c:out value="${allUser.departmentName}" /></p></td>
				<td title="<c:out value="${allUser.userPosition}" />"><p class="overflow"><c:out value="${allUser.userPosition}" /></p></td>
				<td id='<c:out value="${allUser.id}" />'>
					<p class="overflow">
						<c:if test="${allUser.stateId == 1}">
							Active
						</c:if>
						<c:if test="${allUser.stateId == 2}">
							Pending
						</c:if>
						<c:if test="${allUser.stateId == 3}">
							InActive
						</c:if>
					</p>
				</td>
				<td>
					<a class="edit" href="#" onclick="editUser(<c:out value="${allUser.id}" />);">
						edit
					</a>
					<c:choose>
						<c:when test="${allUser.stateId == 3}">
							<a id='<c:out value="${allUser.id}" />' onclick="activateUser(<c:out value="${allUser.id}" />);" class="delete" href="javascript: void(0)">
								active
							</a>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${allUser.stateId == 2}">
									<a id='<c:out value="${allUser.id}" />' onclick="deleteUser(<c:out value="${allUser.id}" />);" class="delete" href="javascript: void(0)">
										delete
									</a>		
								</c:when>
								<c:otherwise>
									<a id='<c:out value="${allUser.id}" />' onclick="inActiveUser(<c:out value="${allUser.id}" />);" class="delete" href="javascript: void(0)">
										inActive
									</a>
								</c:otherwise>
							</c:choose>					
						</c:otherwise>
					</c:choose>	
					<a class="edit" href="javascript: void(0)" class="" onclick="setNewPassword(<c:out value="${allUser.id}" />);">
						new
					</a>													
				</td>				
			</tr>
		</c:forEach>
		
	</table>
	<input type="hidden" name="pageFlag" id="pageFlag" value="" />
	<input type="hidden" id="pagingFlag" value="showUsersWithPageNumber" />
		
	<!-- Start pagination -->
	<div class="pagination">		
		<%@include file='../pagination.jsp'%>       	
	</div>						
	<!-- End pagination -->
	
</form>
</div>
</body>
</html>