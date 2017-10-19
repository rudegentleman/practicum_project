<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Simple Ajax Example</title>
<script language="Javascript">
	function xmlhttpPost(strURL) {
		var xmlHttpReq = false;
		var self = this;
		// Mozilla/Safari
		if (window.XMLHttpRequest) {
			self.xmlHttpReq = new XMLHttpRequest();
		}
		// IE
		else if (window.ActiveXObject) {
			self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
		}
		self.xmlHttpReq.open('POST', strURL, true);
		self.xmlHttpReq.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		self.xmlHttpReq.onreadystatechange = function() {
			if (self.xmlHttpReq.readyState == 4) {
				updatepage(self.xmlHttpReq.responseText);
			}
		}
		self.xmlHttpReq.send(getquerystring());
	}

	function getquerystring() {
		var form = document.forms['f1'];
		var word = form.word.value;
		qstr = 'w=' + escape(word); // NOTE: no '?' before querystring
		return qstr;
	}

	function updatepage(str) {
		document.getElementById("result").innerHTML = str;
	}
</script>



</head>
<body>
	<form name="f1">
		<p>
			Your name: <input name="word" type="text"> <input value="Go"
				type="button" onclick='xmlhttpPost("hello.jsp")'>
		</p>
		<div id="result"></div>
	</form>
	
</body>





</html>