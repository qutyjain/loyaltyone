<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@page session="true"%>
<%@ page import="com.demo.model.UserMessageDetail" %>
<html>
<body>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		function saveMessage(msg,user,parentMessageId){
			$.ajax({
				url : "http://localhost:8080/demo/Message?message="+msg+"&username="+user+"&parentMessageId="+parentMessageId,
						method:"Post",
					success:function(resultdata){
						window.location.reload(false);
						},
				error:function(e){
							alert("Something went wrong");
						}
			});

		}

		$(".comment").click(function(){
			var parentMessageId = this.parentElement.className;
			var replyTextMsg = document.getElementById("replyText_"+parentMessageId);
			var msg = replyTextMsg.value;
			var user = $("#username").val();
			saveMessage(msg,user,parentMessageId);

		});



		$("#commentAdd").click(function(){
			var msg = $("#commentText").val();
			var user = $("#username").val();
			var parentMessageId = 0;
			saveMessage(msg,user,parentMessageId);
		});

	});

	</script>

 	<input type="hidden" id="username" value="${username}"/>
	<h1>Hello!! ${username}</h1>
	<TEXTAREA ID="commentText" NAME="message" ROWS="5" ></TEXTAREA>
	<INPUT TYPE="BUTTON" ID="commentAdd" VALUE="Add Message" >
	<h1>Your message list is as below:</h1>
	<table border="1" width="30%" cellpadding="3">
		<c:forEach items="${messages}" var="messageVar">

	<ul id="td_${messageVar.userMessage.messageId}">
		<li id="message_${messageVar.userMessage.messageId}" class="${messageVar.userMessage.messageId}">
			<% for(int hops = 0; hops < ((UserMessageDetail)(pageContext.findAttribute("messageVar"))).getHopCountToRoot() ; hops+=1) { %>
				 &nbsp;--
		 	 <% } %>
		  	<c:out value="${messageVar.userMessage.message}"/>
				<input id="replyText_${messageVar.userMessage.messageId}" name ="replyText" type="text">
				<input id ="replyAdd_${messageVar.userMessage.messageId}" type="BUTTON" value="Add" class="comment">
</li>
	</ul>
		</c:forEach>
	</table>
</body>
</html>