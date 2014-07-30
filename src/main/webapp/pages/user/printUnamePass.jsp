<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>

<div id="outer-wrapper">
	<h1  id="title">User Credentials</h1>
  		<div class="additional-block">
		
	<input type="hidden" value="printNow" id="printNow" />
       		<div name="typeForm" class="clearfix" id="password"> 
       		<div class="user">
     	 			<p align="center" style="color:red"> <c:out value="${errorName}" /> </p>
				<div >
		       		<b>User Name:</b> <c:out value="${userPro.userName}" />
		        </div>
		        <div>
		       		<b>Password:</b> <c:out value="${userPro.password}" />
		   		</div >
                <div class="paddingT8">
             		Please use the credentials in order to enter your <a id="save" href="edu.iunetworks.am:8080/dinning-web/" ><b>Dining Room</b></a> at address: <a id="save" href="edu.iunetworks.am:8080/dinning-web/" >edu.iunetworks.am:8080/dinning-web/</a>
             	</div>
             	<div class="paddingT8"> 
             		<a  href="javascript:void(0)" onclick="userPrint('<c:out value='${pageFlag}'/>');" class="button" pageFlag = "'<c:out value='${pageFlag}'/>'">Print</a>
				</div>
            
        	</div>
        	</div>
     	
</div>
</div>








