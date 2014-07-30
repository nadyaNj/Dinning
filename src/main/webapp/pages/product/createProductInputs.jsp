<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<fmt:setBundle basename="web-base/createMessages"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<h1>CREATE PRODUCT/EDIT PRODUCT</h1>
<div class="additional-block">
	<form method="post" class="clearfix" name="createForm" id="createProductForm" action="<%=request.getContextPath()%>/actionServlet" enctype="multipart/form-data">
   	 	<input type="hidden" name="id" value='<c:out value="${edit.id}" />' />
   	 	<input type="hidden" name="picturePath" value='<c:out value="${edit.imgUrl}" />' />
   	 	<div align="center">
       		<c:if test="${errors!=null}">
				<c:forEach items="${errors}" var="err">
					<span style="color: red;"> <c:out value="${err}:" /> </span> 							
				</c:forEach>	
			</c:if>
       	</div>
       	<div class="form-item clearfix">
           	<label><fmt:message key="product"/></label>
        	<input id="name" name="name" type="text" class="input-text" maxlength="32" value='<c:out value="${edit.name}" />'/>
        </div>
        <div class="form-item clearfix">
          	<label><fmt:message key="price"/></label>
            <input id="price" name="price" type="text" class="input-text" maxlength="9" value='<c:out value="${edit.price}" />' />
        </div>
        <div class="form-item clearfix">
        	<label><fmt:message key="sort"/></label>
            <c:choose>
				<c:when test="${empty type}">
					<select name="types" multiple="multiple" size="2">
						<option value="0"> No Types </option>
					</select>
				</c:when>
				<c:otherwise>
					<select name="types" multiple="multiple" size="2">
						<c:forEach items="${type}" var="allTypes">
							<c:forEach items="${edit.productTypes}" var="allId">
								<c:if test="${allId == allTypes.id}">
									<option style="color: black" value='<c:out value="${allTypes.id}" />' selected>
										<c:out value="${allTypes.type }" /> 
									</option>
								</c:if>
							</c:forEach>
						</c:forEach>
						<c:forEach items="${noSelectTypes}" var="allType">
							<option value='<c:out value="${allType.id}" />'>
								<c:out value="${allType.type}" />
						</option>
						</c:forEach>
					</select>
				</c:otherwise>
			</c:choose>
        </div>
        <br clear="all" />
		<div class="form-item clearfix">
        	<label><fmt:message key="code"/></label>
        	<input name="code" id="code" type="text" class="input-text" maxlength="10" value='<c:out value="${edit.code}" />' />
        </div>
        <div class="form-item clearfix">
           	<label><fmt:message key="mesurement"/></label>
	        <select id="measurement" name="measurement">
			<option value="0"> None </option>
			<c:forEach items="${measurement}" var="all">
				<c:choose>
					<c:when test="${edit.measurementId == all.id}">
						<option value='<c:out value="${all.id}" />' selected>
							<c:out value="${all.measurement}" />	
						</option>
					</c:when>
					<c:otherwise>
						<option value='<c:out value="${all.id}" />'>
							<c:out value="${all.measurement}"/>	
						</option>
					</c:otherwise>
				</c:choose>					
			</c:forEach>
			</select>
        </div>
        <div class="form-item clearfix">
        	<label><fmt:message key="img"/></label>
       		<input size="5" class="file" type="file" id="file" value="Choose file" name="file1" />
        </div>
        <br clear="all" />
        <div class="form-item clearfix">
        	<label><fmt:message key="description"/></label>
      		<textarea id="description" name="description" maxlength="200" style="resize:none;"><c:out value="${edit.description}" /></textarea>
       	</div>
       	<div class="form-item clearfix">
        	<label><fmt:message key="discount"/></label>
      		<input type="text" class="input-text" id="discount" name="discount" maxlength="11" value='<c:out value="${edit.discountPrice}" />' />
       	</div>
       	<c:if test="${not empty edit.id}">
	       	<div class="form-item clearfix">
	        	<label><fmt:message key="hidden"/></label>
	        	<c:choose>
	        		<c:when test="${edit.hidden == true}">
	        			<input type="checkbox" id="hiddenFlag" name="hiddenTrue" checked="checked" />
	        		</c:when>
	        		<c:otherwise>
	        			<input type="checkbox" id="hiddenFlag" name="hiddenFalse" id="hiddenFlag" />
	        		</c:otherwise>
	        	</c:choose>
	       	</div>
       	</c:if>
                  
        <br clear="all" />  
        <div class="button-line fright"> 
        <c:choose> 
        	<c:when test="${empty edit.id}">    
	    		<input type="hidden" name="pageFlag" value="createPage" id="pageFlag"/> 
			    <a href="#" id="createProduct" class="button" onclick="submitForm('createProductForm')">Create</a>
			    <a href="#" class="button" onclick="resetCreatePage();">Reset</a>
    		</c:when>
    		<c:otherwise>
    			<input type="hidden" name="pageFlag" value="updatePage" id="pageFlag"/> 
			    <a href="#" id="createProduct" class="button" onclick="submitForm('createProductForm')">Update</a>
    		</c:otherwise>
    	</c:choose>
    	</div>   	
	</form>
</div>	
</body>
</html>