<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.js">
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/help.js">
</script>
<title>Dinner Room</title>
</head>

<body>
	<div id="outer-wrapper">
		
    	<h1 class="logo"><a href="#"></a></h1>
	</div>
	   
        <form action="<%=request.getContextPath()%>/actionServlet" method="post" class="login clearfix" id="loginForm">
        	<input  type="hidden" name="pageFlag" value="loginPage" id="loginPageFlag"/>
        	<div>
        		<p style="color: red;" align="center">
        			<c:out value="${errors.loginError }"/>
        		</p>
        	</div>
        	<div class="form-item clearfix">
            	<label>username</label>
                <input type="text" class="input-text" name="username" value='<c:out value="${user.userName}"/>'/>
            </div>
            <div class="form-item clearfix">
            	<label>password</label>
                <input type="password" class="input-text" name="password" onkeypress="return enterKeyPress(event);" />
            </div>
			<div class="paddingR10">
			
				<a href="#" class="button marginT10 fright" onclick="submitForm('loginForm')">Login</a> 
				
            </div>
        </form>
    </div>
	
</body>
</html>