<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP List Users Records</title>
</head>
<body>
    <sql:setDataSource
        var="myDS"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/mydb"
        user="root" password="secret"
    />
     
    <sql:query var="listUsers"   dataSource="${myDS}">
        SELECT * FROM element_reading;
    </sql:query>
     
    <div align="center">
	<table>
	<c:set var="first_tieId" value="${table[0].tie_id}" />  
	<c:set var="i" value="0" />
	<c:set var="color" value="${color[i]}" />
	    <c:forEach var="record" items="${table}">
	        <c:set var="tieId" value="${record.Id}" />
	                      <c:if test="${tieId!=first_tieId}" >
	                       <c:set var="first_tieId" value="${tieId}" />
	                    <c:set var="color" value="${color[i+1]}" />
	                       <c:set var="i" value="${i+1}" /> 
	                     <c:if test="${i==10}">
	                           <c:set var="i" value="0" />
	                                    </c:if>       
	            </c:if>
	                    <tr>
	                      <td scope="row" bgcolor="<c:out value="${color}"/>">${record.oxygen}</td>
	                      <td scope="row" bgcolor="<c:out value="${color}"/>">${record.carbon}</td>
	                      <td scope="row" bgcolor="<c:out value="${color}"/>">${record.hydrogen}</td>
	                      <td scope="row" bgcolor="<c:out value="${color}"/>">${record.temperature}</td>
	                      <td scope="row" bgcolor="<c:out value="${color}"/>">${record.humidity}</td>
	                   </tr>
	    </c:forEach>
	</table>
    </div>
</body>
</html>