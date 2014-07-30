<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<fmt:setBundle basename="web-base/messages"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="content" id="rep">
    		    	
    	<!-- start create form -->
    
    		<%@include file='createProductInputs.jsp'%> 	
      
        <!-- end create form -->
        
     </div>         
       
	 <div class="content">   			
		<div id="productTen">
			<%@include file='searchResult.jsp'%> 	
		</div>	           
	</div> 			
</body>
</html>