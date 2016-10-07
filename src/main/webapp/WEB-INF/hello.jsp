<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@page session="true"%>
<html>
<body>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#btn").click(function(){
			var msg = $("#message").val();
			var user = $("#username").val();
			$.ajax({
				url : "http://localhost:8080/demo/Message?message="+msg+"&username="+user,
						method:"Post",
					success:function(resultdata){
						window.location.reload(false);
						},
				error:function(e){
							alert("Something went wrong");
						}
			});
		});
	});

	</script>

 	<input type="hidden" id="username" value="${username}"/>
	<h1>Hello!! ${username}</h1>
	<TEXTAREA ID="message" NAME="message" ROWS="5" ></TEXTAREA>
	<INPUT TYPE="BUTTON" ID="btn" VALUE="Add Message">
	<h1>Your message list is as below:</h1>
	<table>
		<c:forEach items="${messages}" var="messageVar">
			<tr>
			<td><c:out value="${messageVar.message}"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>