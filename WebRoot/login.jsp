<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getServerName() + ":"
+ request.getServerPort() + path + "/";
String basePath2 = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="<%=basePath2%>resources/jquery.js"></script>
<script>

function Sign_in(){

		var url = '<%=basePath%>msg/login';
		var username = $("#name").val();
		alert(url);
		var password = $("#uid").val();
		alert(password);
		$.ajax({
			type : "post",
			url : url,
			data : {
				"name" : username,
				"uid" : password
			},
			dataType : "json",
			success : function(data) {

			}
		});
	}




</script>
</head>
<body>

<div id="content"></div>
<input type="text"  class="id" name="id" id="id" placeholder="请输入uid"  title="请输入uid"  />
 <input type="text"  class="name" name="name" id="name" placeholder="请输入用户名"  title="请输入用户名"  />
 <button onclick="Sign_in()">登录</button>
</body>
</html>