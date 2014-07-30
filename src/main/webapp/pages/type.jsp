<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<fmt:setBundle basename="web-base/messages"/>
<div id="outer-wrapper">
	<div class="content" id = "content">
		<h1 class="marginT10">Create/Edit type</h1>
   		<div class="additional-block">
       		<div name="typeForm" class="clearfix"> 
     	 			<p align="center" style="color:red"> <c:out value="${errorName}" /> </p>
				<div class="form-item clearfix">
		       		<label>Define Type</label>
		            <input maxlength="30" type="text" name="type" id="text" class="input-text" pageflag="createType" >
				</div>
                <div class="paddingT8 fleft">
             		<a id="save" href="javascript:void(0)" onclick="javascript:itemSave('typePage')" class="button">save</a>
             		<a id="reset" href="javascript:void(0)" onclick="javascript:itemReset('createType')" class="button">Cancel</a>
				</div>
            
        	</div>
     	</div>
 		<c:if test="${count > 0}"> 
 			<h1 class="marginT10">Type list</h1>
	     	<div id="list">
				<p style="color: red">  <c:out value="${errorMessage}" /> </p>
				<table>
					<tr>
		            	<th width="500">Type</th>
		               	<th width="500">Action</th>
		           	</tr>
		          
					<c:if test="${empty type}">
						<tr>
							<td align="center" colspan="2"> No types </td>
						</tr>
					</c:if>
					<c:forEach items="${type}" var="alltypes">
						<tr>
							<td> <c:out value="${alltypes.type}" /> </td>
							<td> 
								<a id="edit" href='#' onclick="javascript:itemEdit(this.getAttribute('typeID'),this.getAttribute('typeName'),'editTypePage', 'Edit Type')"  class="edit" typeName="<c:out value='${alltypes.type}' />" typeID='<c:out value="${alltypes.id}" />'></a>
								
								<input type="hidden" value='<c:out value="${alltypes.id}" />' id="editid"/>
								<a id="delete"   class="delete" href="#" onclick="javascript:itemDelete(this.getAttribute('typeID'),'deleteTypeId')" typeName="<c:out value="${alltypes.type}" /> " typeID='<c:out value='${alltypes.id}'/>'></a>						
								<!--<a href="<%=request.getContextPath()%>/actionServlet?deleteTypeId=<c:out value='${alltypes.id}' />" onclick="if (!confirm('Are you sure to delete selected product?')) return false;" class="delete" />  
						   --></td>
					     </tr>
					</c:forEach>
				</table>
			</div>
	
			<div id="typepaging" class="pagination" count='<c:out value="${typeCount}"  />'>
				<c:if test="${count == 1}">
					<a  href='#' onclick="javascript:typePaging(this.getAttribute('number'),'typePage')" id='typepaging' page='page1'  number='1' class='current'> 1  </a>
				</c:if>
				<c:if test="${count == 2}">
			        <a  href='#' onclick="javascript:typePaging(this.getAttribute('number'),'typePage')" id='typepaging' page='page1'  number='1' class='current'> 1  </a>
			        <a  href='#' onclick="javascript:typePaging(this.getAttribute('number'),'typePage')" id='typepaging' page='page1'  number='2'> 2  </a>
				</c:if>
				<c:if test="${count == 3}">
			        <a  href='#' onclick="javascript:typePaging(this.getAttribute('number'),'typePage')" id='typepaging' page='page1'  number='1' class='current'> 1  </a>
			        <a  href='#' onclick="javascript:typePaging(this.getAttribute('number'),'typePage')" id='typepaging' page='page1'  number='2'> 2  </a>
			        <a  href='#' onclick="javascript:typePaging(this.getAttribute('number'),'typePage')" id='typepaging' page='page1'  number='3'> 3  </a>
				</c:if>
				<c:if test="${count > 3 }">
			        <a href='#' onclick="javascript:typePaging(this.getAttribute('number'),'typePage')" id='typepaging' page='page1'  number='1' class='current'> 1  </a>
			        <a href='#' onclick="javascript:typePaging(this.getAttribute('number'),'typePage')" id='typepaging' page='page1'  number='2'> 2  </a>
			        <a href='#' onclick="javascript:typePaging(this.getAttribute('number'),'typePage')" id='typepaging' page='page1'  number='3'> 3  </a>
			        <a href='#' onclick="javascript:typePaging(this.getAttribute('number'),'typePage')" id='typepaging' page='page1'  number='4'> ...  </a>
			        <a href='#' onclick="javascript:typePaging(this.getAttribute('number'),'typePage')" id='typepaging' page='page1'  number='<c:out value="${count}"/>'><c:out value="${count}"/> </a>
			    </c:if>
			</div>
		</c:if>
		<c:if test="${count == 0}">
			<div align="center" style="color: green;">
				<h1><b><fmt:message key="nosearch" /></b></h1>	
			</div>
		</c:if>
	</div>
</div>