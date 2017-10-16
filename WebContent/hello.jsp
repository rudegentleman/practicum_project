<%@ page language="java" contentType="application/x-www-form-urlencoded; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%
String w = request.getParameter("w");

String msg = "Hello "+w;

String h []= new String[6];
for (int i=0;i<20;i++)
%>

<%=msg%>