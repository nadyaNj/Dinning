<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<fmt:setBundle basename="web-base/messages"/>
<div id="outer-wrapper">
	<div class="content" id = "content">

		<h1  id="title">Create user</h1>
   		<div class="additional-block">
       		<div name="typeForm" class="clearfix"> 
       		<div class="user">
     	 		<p align="center" style="color:red" id="error"> <c:out value="${errorName}" /> </p>
				<div class="form-item">
		       		<label>Username*</label>
		            <input maxlength="30" type="text" name="username" id="username" class="input-text"  />
		        </div>
		        <div  class="form-item">
		            <label>Email</label>
		            <input maxlength="30" type="text" name="email" id="email" class="input-text" />
		           
		        </div>
		           <div   class="form-item" id="company">
		            <label>Company*</label>
		            <select name="company" id="company" onchange="javascript:getdepartment()" >
		            	<option value=''>Select Company</option>
		   		       <c:forEach items="${companyNameList}" var="companyNameList">
		            		<option value='<c:out value="${companyNameList.id}" />'>
							<c:out value="${companyNameList.companyName}"/>	
						</option>            
		       		</c:forEach>
		            
		            </select>
		        </div>
		       
		        <div   class="form-item" id="departments">
		            <label>Departement*</label>
		            <select name="departements" id="departments" >
		            
		   		       <c:forEach items="${departaments}" var="departaments">
		            		<option value='<c:out value="${departaments.id}" />'>
							<c:out value="${departaments.departamentName}"/>	
						</option>            
		       		</c:forEach>
		       		<c:if test="${empty departaments}">
		            	<option value='<c:out value="${departament.id}" />'><c:out value="${departament.departamentName}"/></option>
		            	<input type="hidden" id="hidcompid" value='<c:out value="${companyName.id}" />' />
		            	
		           </c:if>
		            
		            </select>
		        </div>
		       
		         <div  class="form-item">
		            <label>Position</label>
		            <input maxlength="30" type="text" name="position" id="position" class="input-text"  >
		         </div>
		        <div  class="form-item">
		            <label>Discount Code</label>
		            <input maxlength="30" type="text" name="dicountcode" id="dicountcode" class="input-text"  >
		           
				</div>
                <div class="paddingT8 fright">
             		<a id="save" href="javascript:void(0)" onclick="javascript:userSave()" class="button" pageflag="printUser">Save</a>
             		<a id="reset" href="javascript:void(0)" onclick="javascript:userReset()" class="button">Reset</a>
             
				</div>
            </div>
        	</div>
     	</div>
 	
	      	<h1 class="marginT10">User list</h1>
	     	<div id="list">
				<p style="color: red">  <c:out value="${errorMessage}" /> </p>
				<table>
					<tr>
			
		               	<th width="50">Status</th>
		            	<th width="500">User Name</th>
		               	<th width="500">Action</th>
		           	</tr>
		          
					<c:if test="${empty user}">
						<tr>
							<td align="center" colspan="2"> No Users </td>
						</tr>
					</c:if>
					<c:forEach items="${users}" var="allUsers">
						<c:if test="${allUsers.roleId == 4}"><tr>
						<td><c:if test="${allUsers.stateId != 2}"><c:choose>
	        		<c:when test="${allUsers.stateId == 1}">
	        			<input type="checkbox" id="userStatusID" name="active" checked="checked" onchange="javascript:userDelete(<c:out value='${allUsers.id}'/>,'deleteUserPage')"/>
	        		</c:when>
	        		<c:otherwise>
	        			<input type="checkbox" id="userStatusID" name="inactive" onchange="javascript:userDelete(<c:out value='${allUsers.id}'/>,'deleteUserPage')"/>
	        		</c:otherwise>
	        	</c:choose> 
							</c:if><c:if test="${allUsers.stateId == 2}">Pending
							</c:if></td>
							
							<td> <c:out value="${allUsers.userName}" /></td>
							<td>  
								<a id="edit" href='#' onclick="javascript:userEdit('<c:out value='${allUsers.id}'/>','<c:out value='${allUsers.userName}'/>','<c:out value='${allUsers.userEmail}'/>', '<c:out value='${allUsers.userPosition}'/>', '<c:out value='${allUsers.discountCode}'/>', '<c:out value='${allUsers.userDepId}'/>','updateUserPage', 'Edit User')"  class="edit" ></a></a>
								
								
								<a href='#' onclick="javascript:changepassword ('<c:out value='${allUsers.id}'/>','<c:out value='${allUsers.userName}'/>', '<c:out value='${allUsers.userEmail}'/>','setPasswordPage')"  name="password"  class="input-text" > set new passowrd</a>					
								</td>
					     </tr></c:if>
					</c:forEach>
				</table>
			</div>
	
			<div id="typepaging" class="pagination" count='<c:out value="${userCount}"  />'>
				<c:if test="${count == 1}">
					<a  href='#' onclick="javascript:typePaging(this.getAttributtexte('number'),'showUserPage')" id='typepaging' page='page1'  number='1' class='current'> 1  </a>
				</c:if>
				<c:if test="${count == 2}">
			        <a  href='#' onclick="javascript:typePaging(this.getAttribute('number'),'showUserPage')" id='typepaging' page='page1'  number='1' class='current'> 1  </a>
			        <a  href='#' onclick="javascript:typePaging(this.getAttribute('number'),'showUserPage')" id='typepaging' page='page1'  number='2'> 2  </a>
				</c:if>
				<c:if test="${count == 3}">
			        <a  href='#' onclick="javascript:typePaging(this.getAttribute('number'),'showUserPage')" id='typepaging' page='page1'  number='1' class='current'> 1  </a>
			        <a  href='#' onclick="javascript:typePaging(this.getAttribute('number'),'showUserPage')" id='typepaging' page='page1'  number='2'> 2  </a>
			        <a  href='#' onclick="javascript:typePaging(this.getAttribute('number'),'showUserPage')" id='typepaging' page='page1'  number='3'> 3  </a>
				</c:if>
				<c:if test="${count > 3 }">
			        <a href='#' onclick="javascript:typePaging(this.getAttribute('number'),'showUserPage')" id='typepaging' page='page1'  number='1' class='current'> 1  </a>
			        <a href='#' onclick="javascript:typePaging(this.getAttribute('number'),'showUserPage')" id='typepaging' page='page1'  number='2'> 2  </a>
			        <a href='#' onclick="javascript:typePaging(this.getAttribute('number'),'showUserPage')" id='typepaging' page='page1'  number='3'> 3  </a>
			        <a href='#' onclick="javascript:typePaging(this.getAttribute('number'),'showUserPage')" id='typepaging' page='page1'  number='4'> ...  </a>
			        <a href='#' onclick="javascript:typePaging(this.getAttribute('number'),'showUserPage')" id='typepaging' page='page1'  number='<c:out value="${count}"/>'><c:out value="${count}"/> </a>
			    </c:if>
			</div>

		<c:if test="${count == 0}">
			<div align="center" style="color: green;">
				<h1><b><fmt:message key="nosearch" /></b></h1>	
			</div>
		</c:if>
	</div>
</div>