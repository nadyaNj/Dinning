<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.js">
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/help.js">
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.min.js">
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/manageCompany.js">
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/manageDepartment.js">
</script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
</head>
<body>
	<div class="logoContent">
		<table>
        	<tr>
                <td style="border: 0px;">        
                   <div class="header">
                    	<h1 class="logo"><a href="#"></a></h1>
                    </div>        
                </td>
                <td style="border: 0px;">
                    <div align="right">
	
					<c:if test="${empty user.roleId}">
							<h4 style="font-style: italic;"><a href="<%=request.getContextPath()%>/actionServlet?pageFlag=loginPage"> Login </a></h4>
							
							<div class="menu">
							   	<ul class="menu-inner clearfix">
							       	<li><a href="#">Today's Menu</a></li>
							    </ul>
						    </div>
						</c:if>
						<c:if test="${not empty user.roleId}">
							<h4 style="font-style: italic;"><a href="<%=request.getContextPath()%>/actionServlet?pageFlag=logoutPage"> Logout </a></h4>
							<div class="menu">
							   	<ul id="topmenu" class="cols sbros">
							        <c:if test="${user.roleId == '1'}">
							        	<li class="sublnk"><a href="#" id ="menu" class ="current">Menu</a>
								        	<ul>
								        		<li><a href="javascript: void(0)" id="menuLink" name="todaysMenu">Today's Menu</a></li>
								       			<li><a href="javascript: void(0)" id="menuLink" name="createMenuPage">Create Menu</a></li>
								       			<li><a href="javascript: void(0)" id="menuLink" name="menuListPageFlag">Edit Menu</a></li>
								       		</ul>
							       		</li>
							            <li><a href="javascript: void(0)" id="menuLink" name="welcomePage">Search Product</a></li>
							            <li class="sublnk"><a href="#" id = "menu" >Manage Products</a>
								            <ul>
									            <li><a href="javascript: void(0)" id="menuLink" name="createPageView">Create Product</a></li>
									            <li><a href="javascript: void(0)" id="menuLink" name="measurementPage">Create Measure</a></li>
									            <li><a href="javascript: void(0)" id="menuLink" name="typePage">Create Type</a></li>
									        </ul>
								        </li>
								        <li class="sublnk"><a href="#" id = "menu">Manage Company</a>
								            <ul>
									            <li><a href="javascript: void(0)" id="menuLink" name="manageCompanyName">Manage Company Name</a></li>
									            <li><a href="javascript: void(0)" id="menuLink"  name="manageDepartament">Manage Departament</a></li>
									            <li><a href="javascript: void(0)" id="menuLink" name="showRolePage">User Change Roles</a></li>
									     	</ul>
								        </li>
							       	</c:if>
							       	
							       	<c:if test="${user.roleId == '2'}">
							       		<li><a href="javascript: void(0)" id="menuLink" class="current" name="todaysMenu">Today's Menu</a></li>
							       		<li><a href="javascript: void(0)" id="menuLink" name="createMenuPage">Create Menu</a></li>
							       		<li><a href="javascript: void(0)" id="menuLink" name="menuListPageFlag">Edit Menu</a></li>
							            <li><a href="javascript: void(0)" id="menuLink" name="welcomePage">Search Product</a></li>
							            <li><a href="javascript: void(0)" id="menuLink" name="createPageView">Product Managment</a></li>
							       	</c:if>
							       	
							      	<c:if test="${user.userName == '4'}">
							      		<li><a href="#" id="menuLink" class="current" name="todaysMenu">Today's Menu</a></li>
							      	</c:if>
							      	
							      	<c:if test="${user.userName == '3'}">
							      		<li><a href="#" id="menuLink" class="current" name="todaysMenu">Today's Menu</a></li>
							            <li><a href="javascript: void(0)" id="menuLink" name="showUserPage">Users</a></li>
							        </c:if>
							    </ul>
							</div>
						</c:if>					
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div id="replaceWith">			
		<%@include file='createProduct.jsp'%>						
	</div>	
	<%@include file='../footer.html'%>	
</body>
</html>