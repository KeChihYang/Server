<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
</head>
<body>
	Name <input type="text" id="name" value=""/><br/>
	Age <input type="text" id="age" value=""/><br/>
	<input type="button" id="submit" value="Submit">
	
	<script type="text/javascript">
		$(function(){
			$("#submit").click(function(){
				var data = {
					"name" : $("#name").val(),
					"age" : $("#age").val()
				};
				$.post("/mvc/handleAJAXRequest", data, function(json){
					if(json.result == "true"){
						alert("name :" + json.name + ", age:" + json.age);
					}
				});
			});
		});
	
	</script>
</body>
</html>
