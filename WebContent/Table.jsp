<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import=" model.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Main Data Portal</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script>
	function showhide(id) {
		var e = document.getElementById(id);
		e.style.display = (e.style.display == 'block') ? 'none' : 'block';
	}
</script>



</head>

<style>
.rwanda_tea {
	width: 340px;
	height: auto;
}
</style>
<body>
	<%
		if (request.getSession().getAttribute("allowed") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			//servlet2 is the url-pattern of the second servlet  

			rd.forward(request, response);//method may be include or forward  

		}
	%>
	<img class="rwanda_tea" src="tea.gif" alt="Loading" title="Rwanda Tea" />
	<img src="Logo2.png" alt="Loading" title="Rwanda Tea" />
	<img src="rmt_logo2.png" alt="Loading" title="Rwanda Tea" />
	

	<footer>

		<span style="position: absolute; top: 0pt; right: 1pt;"> <a
			target="blank" href="https://www.rwandamountaintea.com/">
				<h2>Rwanda Mountain Tea</h2>
		</a> <a href="javascript:showhide('seBestRecord')">
				<h4>Set new Best record</h4>
		</a>
			<div id="seBestRecord" style="display: none;">
				<p>follow the Data format provided</p>

				<form action="SetBestRecord" method="POST">
					<input type="date" id="myDate" name="bestDate" value="2014-30-09">

					<input type="submit" value="Submit">

				</form>
				<script>
					function myFunction() {
						var x = document.getElementById("myDate").value;
						document.getElementById("demo").innerHTML = x;
					}
				</script>


			</div> <a href="javascript:showhide('traceBack')">
				<h4>Trace back</h4>

				<div id="traceBack" style="display: none;">

					<p>follow the Data format provided</p>

					<form action='' method="POST">
						<input type="date" id="myDate" name="bestDate" value="2014-30-09">

						<input type="submit" value="Submit">

					</form>
				</div>

		</a> <a href="javascript:showhide('uniquename')">
				<h4>Inspect an element</h4>
		</a>

			<div id="uniquename" style="display: none;">
				<form action="SelectElementToAnalyse" method="POST">
					<select name="elements">
						<option value="oxygen">Oxygen</option>
						<option value="hydrogen">Hydrogen</option>
						<option value="carbon">Carbon dioxide</option>
						<option value="temperature">Temperature</option>
						<option value="humidity">Humidity</option>
					</select> <input type="submit" value="Submit">
				</form>


			</div> <a href="DataLoad">
				<h4>Refresh the page</h4>
		</a>

		</span>

	</footer>

	<div class="container">
		<h2>Data Portal</h2>
		<p>Welcome, this page displays each sensored elements, the name,
			the current reading, the current daily mean, error to the best mean
			and the warning after doing thorough data analytics</p>
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
						if (sensoredElmnt.getElementName().equals("oxygen")) {
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
					<td>
						<%
							out.println(elements.get(i).getError());
						%>
					</td>
					<td>
						<%
							out.println(elements.get(i).getWarninng());
						%>
					</td>
				</tr>
				<%
					} else {
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
					<td>
						<%
							out.println(elements.get(i).getError());
						%>
					</td>
					<td>
						<%
							out.println(elements.get(i).getWarninng());
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

