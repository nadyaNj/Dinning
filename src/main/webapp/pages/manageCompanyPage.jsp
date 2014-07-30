<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<fmt:setBundle basename="web-base/createMessages"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/manageCompany.js">
</script>
</head>
<body>
<div id="outer-wrapper">
	<div class="content" id="rep">
		<c:choose>
			<c:when test="${not empty errorNameUpdate }">
    			<h1 id="title">Edit Company Name</h1>
    		</c:when>
    		<c:otherwise>
    			<h1 id="title">Manage Company Name</h1>
    		</c:otherwise>
    	</c:choose>
		<div class="additional-block">
		<div class="clearfix">
			<c:choose>
				<c:when test="${not empty errorNameUpdate }">
					<input type="hidden" value="updateCompanyName"  name='<c:out value="${errorNameUpdate}"/>' id="flag" />
				</c:when>
				<c:otherwise>
					<input type="hidden" value="createCompanyName" id="flag" />
				</c:otherwise>
			</c:choose>
			<input type="hidden" value="" name='' id="hid"/>
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
					<label>Company Name *</label>
					<c:choose>
						<c:when test="${empty errorName}">
							<input type="text"   maxlength="40"   class="input-text" id="companyName" />
						</c:when>
						<c:otherwise>
							<input type="text"  maxlength="40"  class="input-text" id="companyName" value='<c:out value="${companyNameBean.companyName}" />'/>
						</c:otherwise>
					</c:choose>
				</div>
				
				<div class="paddingT8 fleft">
                	<a href="javascript:void(0)" class="button" onclick="createCompanyName();">
                	<c:choose>
		               		<c:when test="${not empty errorNameUpdate }">
		       	        			update	
		               		</c:when>
		               		<c:otherwise>
		               				<span  id="update">create</span>
		               		</c:otherwise>
               	</c:choose>
               	</a>
               	<c:if test="${empty errorNameUpdate}">
                    <a id="reset" href="javascript:void(0)" class="button" onclick="companyNameReset();">Reset</a>
               </c:if>
                </div>
                </div>
		</div>
		
		<c:if test="${not empty companyNameList}">
		<h1 class="marginT10">Company name List</h1>
	<table>
		<tr>
			<th>Company name</th>
            <th>Action</th>
		</tr>
		<c:forEach items="${companyNameList}" var="companyName">
		<tr>
			<td>
				<c:out value="${companyName.companyName}" />
			</td>
			 <td>
               	<a href="javascript:void(0)" class="edit" onclick="javascript:editCompanyName(<c:out value="${companyName.id}" />,this.getAttribute('name'),'updateCompanyName','Edit company name');" name='<c:out value="${companyName.companyName}" />'>edit</a>
              	<a href="javascript:void(0)" class="delete" onclick="javascript:deleteCompanyName(<c:out value="${companyName.id}" />);">delete</a>
             </td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
	</div>
</div>
</body>
</html>