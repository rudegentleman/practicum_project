<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import=" model.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Data Portal</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<style>
.rwanda_tea{
  width: 340px;
  height: auto;
}
</style>
<body>
	<img class= "rwanda_tea"	
	src="tea.gif" alt="Loading" title="Rwanda Tea" />

	<div class="container">
		<h2>Data Portal</h2>
		<p>this page displays each sensored elements, the name, the
			current reading, the current daily mean, error to the best mean and
			the warning after doing thorough data analytics</p>
		<table class="table">
			<thead>
				<tr>
					<th>Element Name</th>
					<th>current measure</th>
					<th>Mean sofar</th>
					<th>Error</th>
					<th>Warning</th>
				</tr>
			</thead>
			<tbody>
				<%
					// retrieving the session
					ArrayList<Element> elements = (ArrayList) request.getSession().getAttribute("elements");
					Element b;
					response.getWriter();
					String h[] = { "info", "warning", "active", "danger" };
					int i = 0;
					for (Element sensoredElmnt : elements) {
						if (sensoredElmnt.getElementName().equals("oxygen")){
				%>




				<tr class=success>
					<td>
						<%
							out.println(elements.get(i).getElementName());
						%>
					</td>
					<td>
						<%
							out.println(elements.get(i).getCurrMeasure());
						%>
					</td>
					<td>
						<%
							out.println(elements.get(i).getMeanSofar());
						%>
					</td>
				</tr>
				<%
						}
				else{
					
					
					%>
				<tr class="warning">
					<td>
						<%
								out.println(elements.get(i).getElementName());
							%>
					</td>
					<td>
						<%
								out.println(elements.get(i).getCurrMeasure());
							%>
					</td>
					<td>
						<%
								out.println(elements.get(i).getMeanSofar());
							%>
					</td>
				</tr>
				<%
					
				}
					i++;
					}
				%>

			</tbody>
		</table>
	</div>

</body>

</html>

