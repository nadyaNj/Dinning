<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<body onload="focus();">
	<p id="userName" style="color: green;" align="left"></p>
	<h2 align="center">Basket</h2>
	<p id="error" style="color: red;"></p>
	<table id="basket">
		<tr>
			<td>Name</td>
			<td>Price</td>
			<td>Action</td>
		</tr>
		<tr>
			<td colspan="1">Total</td>
			<td id="total">0</td>
		</tr>
		<tr>
			<td colspan="1">Code</td>
			<td><input type="text" id="codeCashier" onkeypress="return enterKeyPressCode(event)"/></td>
		</tr>
		<tr>
			<td colspan="1"><input type="button" onclick="pay('0');" value="Վճարել կանխիկ"></td>
			<td><input id="employeePay" type="button"  disabled="disabled" onclick="pay('1')"  value="Վճարել կուտակային"></td>		
		</tr>
	</table>
</body>