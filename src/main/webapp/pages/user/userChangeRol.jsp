<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<h1>Create/Update User Role</h1>
	<div class="additional-block">
		<h3 id="errorMessageRed" align="center" style="color: red;"></h3>
		<form class="clearfix" id="userManageForm" method="post" action="<%=request.getContextPath()%>/actionServlet">
			<input type="hidden" name="id" value='<c:out value="${userBean.id}" />' />
			<div class="form-item clearfix">
				<label>UserName*:</label>
	        	<input value='<c:out value="${userBean.userName}" />' id="username" name="username" type="text" class="input-text" maxlength="19">
			</div>
			
			<div class="form-item clearfix">
				<label>Company*:</label>
				<select id="company" onkeypress="showDepartamentsByCompany();" onchange="showDepartamentsByCompany();" name="company">
					<option value="0">None</option>
					<c:choose>
						<c:when test="${empty dep}">
							<c:forEach items="${comList}" var="allCompany">
								<option value='<c:out value="${allCompany.id}" />'><c:out value="${allCompany.companyName}"/></option>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${comList}" var="allCompany">
								<c:choose>		
									<c:when test="${dep[0].id == allCompany.id}">						
										<option value='<c:out value="${allCompany.id}" />' selected="selected"><c:out value="${allCompany.companyName}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${allCompany.id}" />' selected="selected"><c:out value="${allCompany.companyName}"/></option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:otherwise>
					</c:choose>					
				</select>
			</div>			
			
			<div class="form-item clearfix">
				<label>Department*:</label>
				<select id="dep" name="department" id="departmentReq">
					<%@include file='departamentByCompany.jsp'%>  								
				</select>
			</div>			
			
			<br clear="all" />
			<div class="form-item clearfix">
				<label>Role*:</label>
				<select name="role">	
					<option value="0">None</option>
					<c:choose>
						<c:when test="${userBean.roleId == 2}">
							<option value="2" selected="selected">Cooker</option>
						</c:when>
						<c:otherwise>
							<option value="2">Cooker</option>							
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${userBean.roleId == 3}">
							<option value="3" selected="selected">Hr</option>
						</c:when>
						<c:otherwise>
							<option value="3">Hr</option>							
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${userBean.roleId == 5}">
							<option value="5" selected="selected">Cashier</option>
						</c:when>
						<c:otherwise>
							<option value="5">Cashier</option>						
						</c:otherwise>
					</c:choose>
					
				</select>
			</div>
			<div class="form-item clearfix">	
				<label>Email:</label>
				<input maxlength="28" value='<c:out value="${userBean.userEmail}" />' type="text" class="input-text" name="useremail" />
			</div>	
			<div class="form-item clearfix">	
				<label>Position:</label>
				<input maxlength="28" value='<c:out value="${userBean.userPosition}" />' type="text" class="input-text" name="position" />
			</div>		
			
			<br clear="all" />  
      		<div class="button-line fright">
      			<c:choose>	
      				<c:when test="${empty userBean.id}">
      					<input type="hidden" name="pageFlag" value="createUser"/>
		      			<a href="javascript: void(0)" class="button" onclick="createUserFunction();">Create</a>
						<a href="javascript: void(0)" class="button" onclick="resetForm('userManageForm');">Reset</a>	
					</c:when>
					<c:otherwise>
						<input type="hidden" name="pageFlag" value="updateUser"/>
						<a href="javascript: void(0)" class="button" onclick="updateUserFunction();">Update</a>
					</c:otherwise>
				</c:choose>
			</div>	
        </form>
	</div>
	
</body>
</html>