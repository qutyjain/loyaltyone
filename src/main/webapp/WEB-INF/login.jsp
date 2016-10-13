<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script> function validateForm() {
    var x = $("#inputParam").val();
    if (x == null || x == "") {
        alert("User Name must be filled out");
        return false;
    }
}
</script>
	<form name="loginForm" action="/demo/login" method="post"
		onsubmit="return validateForm()">
		 Enter user name:<input type="text" name="inputParam" id="inputParam"/>
		<br /> <input type="submit" id="btn" value="DONE" />
	</form>
</body>
</html>