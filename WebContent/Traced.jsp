<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Trace Back</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body>

	<%
		ArrayList<ArrayList> data = (ArrayList) request.getSession().getAttribute("data");
		ArrayList<String> temperature = null;
		ArrayList<String> humidity = null;
		double minTemp = 0, maxTemp = 0, minHum = 0, maxHum = 0, meanTemp = 0, meanHum = 0;

		if (data != null) {

			temperature = data.get(0);
			humidity = data.get(1);
			minTemp = MathClass.min(temperature);
			minHum = MathClass.min(humidity);
			maxTemp = MathClass.max(temperature);
			maxHum = MathClass.max(humidity);
			meanTemp = MathClass.mean(temperature);
			meanHum = MathClass.mean(humidity);

		}
	%>

	<div class="container">
		<h2>TRACING</h2>

		<h3>Summary</h3>

		<table class="container">

			<tr>
				<th>Element Name</th>
				<th>min</th>
				<th>max</th>
				<th>mean</th>
			</tr>
			<tr>
				<th>Temperature</th>
				<td><%=minTemp%></td>
				<td><%=maxTemp%></td>
				<td><%=meanTemp%></td>
			</tr>
			<tr>
				<th>humidity</th>

				<td><%=minHum%></td>
				<td><%=maxHum%></td>
				<td><%=meanHum%></td>
			</tr>
		</table>





		<h3>Verbose</h3>
		<p>The next table displays all data recorded on the chosen day the
			color is red when the record is different from the expected one</p>
		<table class="table">
			<thead>
				<tr>
					<th>Temperature</th>
					<th>humidity</th>

				</tr>
			</thead>
			<tbody>
				<%
					// retrieving the session

					String h[] = { "active", "danger" };
					int i = 0;
					String humR;
					for (String record : temperature) {
						humR = humidity.get(i++);

						if (Double.parseDouble(record) < 25)
							h[1] = "danger";
						else
							h[1] = "active";
						//if (sensoredElmnt.getElementName().equals("oxygen")) {
				%>




				<tr class=<%=h[1]%>>
					<td>
						<%
							out.println(record);
						%>
					</td>
					<td>
						<%
							out.println(humR);
						%>
					</td>

				</tr>
				<%
					}
				%>

			</tbody>
		</table>
	</div>

</body>
</html>