<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="/css/houtai.css">
</head>
<body>
	<form action="dologin.do" method="post">
		<div class="login">
			<div class="midden">
				<span class="title"><img src="images/tcx_logo.png">欢迎登录渠道统计管理后台</span><br>
				<input type="text" name="account" value=""><br> 
				<input type="password" name="pwd" value=""><br>
				<button type="submit">确认登录</button>
			</div>
		</div>
	</form>
</body>
</html>