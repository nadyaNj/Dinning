<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt'%>
<%@ page language="java" import="java.util.*" %>
<div id=tab>
		<c:choose>
			<c:when test="${ empty errorTodayMenu }">
					<h1 id="messageType">Menu for <c:out value="${menuDate}"/> </h1>
		<table>
	            <tr>
	                <th></th>
	                <th>Name</th>
	                <th>Price<span class="weight-normal">(AMD)</span></th>
	                <th>Discount price</th>
	                <th>Code</th>
	                <th>Action</th>
	            </tr>
	            
	             <c:forEach items="${addedProdList}" var="add">	             
			            <tr onmouseover="ChangeColor(this, true);" onmouseout="ChangeColor(this, false);">
			                <td style="cursor: pointer;" class="center" onclick="addBasket('<c:out value="${add.name }"/>','<c:out value="${add.price }"/>','<c:out value="${add.id }"/>');">
			                	<c:if test="${add.imgUrl == 'noImage.jpg' }">
									<img width="57" height="63" src="<%=request.getContextPath()%>/pictures/<c:out value="${add.imgUrl}"/>"/>
								</c:if>
								<c:if test="${add.imgUrl != 'noImage.jpg' }">
									<img width="57" height="63" src="<%=request.getContextPath()%>/tmp/<c:out value="${add.imgUrl}"/>"/>
								</c:if>
							</td>
			                <td class="overflow" style="cursor: pointer;" onclick="addBasket('<c:out value="${add.name }"/>','<c:out value="${add.price }"/>','<c:out value="${add.id }"/>','<c:out value="${add.discountPrice }"/>');"><p style="font-weight: bold;" title="<c:out value="${add.name }"/>"><c:out value="${add.name }"/></p></td>
			                <td class="overflow" style="cursor: pointer;" onclick="addBasket('<c:out value="${add.name }"/>','<c:out value="${add.price }"/>','<c:out value="${add.id }"/>','<c:out value="${add.discountPrice }"/>');"><p style="font-weight: bold;" title="<c:out value="${add.price }"/>"><c:out value="${add.price }"/></p></td>
			                <td class="overflow" style="cursor: pointer;" onclick="addBasket('<c:out value="${add.name }"/>','<c:out value="${add.price }"/>','<c:out value="${add.id }"/>','<c:out value="${add.discountPrice }"/>');"><p style="font-weight: bold;" title="<c:out value="${add.price }"/>"><c:out value="${add.discountPrice }"/></p></td>
			                <td class="overflow2" style="cursor: pointer;" onclick="addBasket('<c:out value="${add.name }"/>','<c:out value="${add.price }"/>','<c:out value="${add.id }"/>','<c:out value="${add.discountPrice }"/>');"><p style="font-weight: bold;" title="<c:out value="${add.code }"/>"><c:out value="${add.code }"/></p></td>
			            	<td colspan="4" align="left"> <a href="javascript: void(0)" onclick="addBasket('<c:out value="${add.name }"/>','<c:out value="${add.price }"/>','<c:out value="${add.id }"/>','<c:out value="${add.discountPrice }"/>','half');"><b>Կես բաժին</b> </a></td> 
			            </tr> 
	            </c:forEach>
	     </table>
			</c:when>
			<c:otherwise>
				 <p align="center" style="color: red;"><c:out value="${errorTodayMenu}"/></p> 
			</c:otherwise>
		</c:choose>
</div>