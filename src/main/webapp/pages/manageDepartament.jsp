<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<fmt:setBundle basename="web-base/createMessages"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/manageDepartment.js">
</script>
</head>
<body>
<div id="outer-wrapper">
	<div class="content" id="rep">
		<c:choose>
			<c:when test="${not empty errorNameUpdate }">
    			<h1 id="title">Edit departament</h1>
    		</c:when>
    		<c:otherwise>
    			<h1 id="title">Manage departament</h1>
    		</c:otherwise>
    	</c:choose>
		<div class="additional-block">
		<div class="clearfix">
			<c:choose>
				<c:when test="${not empty errorNameUpdate }">
					<input type="hidden" value="updateDepartament" name='<c:out value="${errorNameUpdate}"/>' id="flag" />
				</c:when>
				<c:otherwise>
					<input type="hidden" value="createDepartament" name='<c:out value="${errorName}"/>' id="flag" />
				</c:otherwise>
			</c:choose>
			<input type="hidden" value="" name='' title="" id="hid"/>
			<c:choose>
				<c:when test="${not empty errorName }">
							<p align="center" style="color: red;"><c:out value="${errorName}"/></p>
				</c:when>
				<c:otherwise>
					<c:if test="${not empty errorNameUpdate }">
						<p align="center" style="color: red;"><c:out value="${errorNameUpdate}"/></p>
					</c:if>
				</c:otherwise>
			</c:choose>
				<div class="form-item clearfix">
					<label>Departament *</label>
					<c:choose>
						<c:when test="${empty errorName}">
							<input type="text" maxlength="40"   class="input-text" id="departamentName" />
						</c:when>
						<c:otherwise>
							<input type="text" maxlength="40"  class="input-text" id="departamentName" value='<c:out value="${bean.departamentName}"/>'  />
						</c:otherwise>
					</c:choose>
				</div>
					<div class="form-item clearfix">
			           	<label>Company name *</label>
				        <select id="company">
							<c:forEach items="${companyNameList}" var="company">
								<c:choose>
									<c:when test="${not empty errorName || not empty errorNameUpdate && bean.companyId == company.id }">
										<option value='<c:out value="${company.id}" />' selected="selected">
											<c:out value="${company.companyName}"/>	
										</option>
									</c:when>	
									<c:otherwise>
										<option value='<c:out value="${company.id}" />'>
											<c:out value="${company.companyName}"/>	
										</option>
									</c:otherwise>
								</c:choose>			
							</c:forEach>
						</select>
			     	</div>
				<div class="paddingT8 fleft">	
                    <a href="javascript:void(0)" class="button" onclick="javascript:createDepartament();">
                		<c:choose>
               		<c:when test="${not empty errorNameUpdate }">
       	        			update	
               		</c:when>
               		<c:otherwise>
               				<span id="update">create</span>
               		</c:otherwise>
               	</c:choose>
                	</a>
                	<c:if test="${empty errorNameUpdate }">
                	    <a href="javascript:void(0)" id="depReset" class="button" onclick="departamentReset();">Reset</a>
                	</c:if>
                </div>
                </div>
		</div>
		<c:if test="${not empty departaments }">
		<h1 class="marginT10">Department List</h1>
	<table>
		<tr>
			<th>Department name</th>
			<th>Company name</th>
            <th>Action</th>
		</tr>
		<c:forEach items="${departaments}" var="departament">
		<tr>	
			<td>
				<c:out value="${departament.departamentName}" />
			</td>
				<c:forEach items="${companyNameList}" var="comp">
				<c:if test="${comp.id == departament.companyId }">
						<td>
							<c:out value="${comp.companyName }"/>
						</td>
					</c:if>
				</c:forEach>
			 <td>
               	<a href="javascript:void(0)" class="edit" name='<c:out value="${departament.departamentName}" />' onclick="javascript:editDepartament(<c:out value="${departament.id}" />,this.getAttribute('name'),'updateDepartament','Edit departament',<c:out value="${departament.companyId}" />);">
               				edit
               	</a>
              	<a href="javascript:void(0)" class="delete" onclick="javascript:deleteDepartament(<c:out value="${departament.id}"/>);">delete</a>
             </td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
	</div>
</div>
</body>
</html>