<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<fmt:setBundle basename="web-base/messages"/>
<div id="outer-wrapper">
	<div class="content" id = "content">
	   	<h1  id="title">Create measure</h1>
   	    <div class="additional-block">
	        <div name="measureForm" class="clearfix">
	   	 		<p align="center" style="color:red"> <c:out value="${error}" /> </p>
				<div class="form-item clearfix">
	            	<label>Define Measure</label>
	                <input maxlength="30" type="text" name="measure" id="text" class="input-text" pageflag="resetMeasPage" >
	            </div>
	            <div class="paddingT8 fleft">
	           		<a id="save" href="javascript:void(0)" onclick="javascript:itemSave('measurementPage')" pageNumber class="button">save</a>
	           		<a id="reset" href="javascript:void(0)" onclick="javascript:itemReset('resetMeasPage')" class="button">Cancel</a>
	        
	            </div>
	          
	      </div>
	    </div>
	    <c:if test="${count > 0}">  
	    	<h1 class="marginT10">Measures list</h1>
	    
			<div id="list">
			    		      
			  	<p style="color: red">  <c:out value="${alert}" /> </p>
				<table >
					<tr>
		                <th width="500">Measurement</th>
		                <th width="500">Action</th>
		            </tr>
		           	<c:forEach items="${measurement}" var="allMes">
						<tr>
							<td> <c:out value="${allMes.measurement}" /> </td>
							<td> 
								<a id="edit"   href="javascript:void(0)" onclick="javascript:itemEdit(this.getAttribute('measureID'),this.getAttribute('measurName'),'editMeasurementId', 'Edit Measurement')"    class="edit" measurName="<c:out value="${allMes.measurement}" /> " measureID='<c:out value='${allMes.id}'/>'></a>
								<a id="delete" href="javascript:void(0)" onclick="javascript:itemDelete(this.getAttribute('measureID'),'mesId')" pageflag="deleteMesId"  class="delete" class="edit" measurName="<c:out value="${allMes.measurement}" /> " measureID='<c:out value='${allMes.id}'/>'></a>
								<%-- <a id="delete"  href="<%=request.getContextPath()%>/actionServlet?mesId=<c:out value='${allMes.id}' />" onclick="if (!confirm('Are you sure to delete selected product?')) return false;" class="delete" /> --%>  
						   </td>
					     </tr>
					</c:forEach>
				</table>
			</div>
	
			<div id="typepaging" class="pagination" count='<c:out value="${measurementCount}"  />'><c:if test="${count <= 1}"></c:if>
				<c:if test="${count == 1}">
					<a  href='#' onclick="javascript:typePaging(this.getAttribute('number'),'measurementPage')"   id='typepaging' page='page1'  number='1' class='current'> 1  </a>
				</c:if>
				<c:if test="${count == 2}">
			        <a  href='#' onclick="javascript:typePaging(this.getAttribute('number'),'measurementPage')"   id='typepaging' page='page1'  number='1' class='current'> 1  </a>
			        <a  href='#' onclick="javascript:typePaging(this.getAttribute('number'),'measurementPage')"    id='typepaging' page='page1'  number='2'> 2  </a>
				</c:if>
				<c:if test="${count == 3 }">
			        <a href='#' onclick="javascript:typePaging(this.getAttribute('number'),'measurementPage')"   id='typepaging' page='page1'  number='1' class='current'> 1  </a>
			        <a  href='#' onclick="javascript:typePaging(this.getAttribute('number'),'measurementPage')"    id='typepaging' page='page1'  number='2'> 2  </a>
			        <a  href='#' onclick="javascript:typePaging(this.getAttribute('number'),'measurementPage')"   id='typepaging' page='page1'  number='3'> 3  </a>
				</c:if>
				<c:if test="${count > 3 }">
			        <a href='#' onclick="javascript:typePaging(this.getAttribute('number'),'measurementPage')"   id='typepaging' page='page1'  number='1' class='current'> 1  </a>
			        <a href='#' onclick="javascript:typePaging(this.getAttribute('number'),'measurementPage')"    id='typepaging' page='page1'  number='2'> 2  </a>
			        <a href='#' onclick="javascript:typePaging(this.getAttribute('number'),'measurementPage')"   id='typepaging' page='page1'  number='3'> 3  </a>
			        <a href='#' onclick="javascript:typePaging(this.getAttribute('number'),'measurementPage')"    id='typepaging' page='page1'  number='4'> ...  </a>
			        <a href='#' onclick="javascript:typePaging(this.getAttribute('number'),'measurementPage')"    id='typepaging' page='page1'  number='<c:out value="${count}"/>'><c:out value="${count}"/> </a>
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