<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import=" model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

<meta charset="utf-8">

<title>Information By Graph</title>

<style>
html, body {
	height: 100%
}

body {
	background: #f2f2f2 no-repeat center top;
	min-height: 700px;
	-webkit-box-shadow: inset 0 0 0 5px #f2f2f2, inset 0 0 0 10px #90c0aa,
		inset 0 0 0 15px #f2f2f2;
	-mox-box-shadow: inset 0 0 0 5px #f2f2f2, inset 0 0 0 10px #90c0aa,
		inset 0 0 0 15px #f2f2f2;
	box-shadow: inset 0 0 0 5px #f2f2f2, inset 0 0 0 10px #90c0aa, inset 0 0
		0 15px #f2f2f2;
	margin: 0
}

#graph-wrapper {
	width: 640px;
	margin: 0 auto;
	padding-top: 275px
}
</style>

<link rel="stylesheet" href="css/graph.css">
<meta name="robots" content="noindex,follow" />
</head>

<body>

	<!-- Graph HTML -->
	<div id="graph-wrapper">
		<div class="graph-info">
			<a href="javascript:void(0)" class="visitors">Visitors</a> <a
				href="javascript:void(0)" class="returning">Returning Visitors</a> <a
				href="#" id="bars"><span></span></a> <a href="#" id="lines"
				class="active"><span></span></a>
		</div>

		<div class="graph-container">
			<div id="graph-lines"></div>
			<div id="graph-bars"></div>
		</div>
	</div>
	<!-- end Graph HTML -->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script src="js/jquery.flot.min.js"></script>
	<script>
		/* parsing java data from data base into javascript array for graphing */
	<%ArrayList<Double> currMeasure = (ArrayList) request.getSession().getAttribute("currMeasures");
			ArrayList<Double> means = (ArrayList) request.getSession().getAttribute("means");
			System.out.println(currMeasure);
			System.out.println(means);%>
		
	<%double[] xCor = new double[]{1, 2, 3, 4};
			double size = means.size();%>
		var coordinatesCurr = new Array()
		var coordinatesMeans = new Array()

		var dataSize =
	<%=size%>
		
	<%int k = 0;%>
		var data =
	<%=currMeasure%>
		;
		var data2 =
	<%=means%>
		;

		for (i = 0; i < dataSize; i++) {

			coordinatesCurr[i] = []
			coordinatesCurr[i][0] = i + 1

			coordinatesCurr[i][1] = data[i];

			coordinatesMeans[i] = []
			coordinatesMeans[i][0] = i + 1

			coordinatesMeans[i][1] = data2[i] * 4;
		}

		$(document)
				.ready(
						function() {

							// Graph Data ##############################################
							var graphData = [ {
								// Visits
								data : coordinatesCurr,
								color : '#71c73e'
							}, {
								// Returning Visits
								data : coordinatesMeans,
								color : '#77b7c5',
								points : {
									radius : 4,
									fillColor : '#77b7c5'
								}
							} ];

							// Lines Graph #############################################
							$.plot($('#graph-lines'), graphData, {
								series : {
									points : {
										show : true,
										radius : 5
									},
									lines : {
										show : true
									},
									shadowSize : 0
								},
								grid : {
									color : '#646464',
									borderColor : 'transparent',
									borderWidth : 20,
									hoverable : true
								},
								xaxis : {
									tickColor : 'transparent',
									tickDecimals : 0
								},
								yaxis : {
									tickSize : 10
								}
							});

							// Bars Graph ##############################################
							$.plot($('#graph-bars'), graphData, {
								series : {
									bars : {
										show : true,
										barWidth : .9,
										align : 'center'
									},
									shadowSize : 0
								},
								grid : {
									color : '#646464',
									borderColor : 'transparent',
									borderWidth : 20,
									hoverable : true
								},
								xaxis : {
									tickColor : 'transparent',
									tickDecimals : 0
								},
								yaxis : {
									tickSize : 10
								}
							});

							// Graph Toggle ############################################
							$('#graph-bars').hide();

							$('#lines').on('click', function(e) {
								$('#bars').removeClass('active');
								$('#graph-bars').fadeOut();
								$(this).addClass('active');
								$('#graph-lines').fadeIn();
								e.preventDefault();
							});

							$('#bars').on(
									'click',
									function(e) {
										$('#lines').removeClass('active');
										$('#graph-lines').fadeOut();
										$(this).addClass('active');
										$('#graph-bars').fadeIn().removeClass(
												'hidden');
										e.preventDefault();
									});

							// Tooltip #################################################
							function showTooltip(x, y, contents) {
								$('<div id="tooltip">' + contents + '</div>')
										.css({
											top : y - 16,
											left : x + 20
										}).appendTo('body').fadeIn();
							}

							var previousPoint = null;

							$('#graph-lines, #graph-bars')
									.bind(
											'plothover',
											function(event, pos, item) {
												if (item) {
													if (previousPoint != item.dataIndex) {
														previousPoint = item.dataIndex;
														$('#tooltip').remove();
														var x = item.datapoint[0], y = item.datapoint[1];
														showTooltip(
																item.pageX,
																item.pageY,
																y
																		+ ': measurement '
																		+ (x)
																		+ 'th reading');
													}
												} else {
													$('#tooltip').remove();
													previousPoint = null;
												}
											});

						});
	</script>

</body>

</html>