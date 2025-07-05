<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="images/favicon.ico" rel="shortcut icon">
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js">
</script>
<title>图书管理系统</title>
<style type="text/css">
.div{
	position:absolute;
	top:25%;
	left:45%;
}
</style>
</head>
<body style="background-color: #4158D0;
background-image: linear-gradient(43deg, #4158D0 0%, #C850C0 46%, #FFCC70 100%);
">
	<div align="center" class="div">
		<h2>图书管理系统</h2>
		<form action="user_add" method="post">
		<table>
			<tr><td><input type="text" name="username" placeholder="用户名" style="width:200px;height:25px;"><br><br></td></tr>
			<tr><td><input type="password" name="password" placeholder="密码" style="width:200px;height:25px;"><br><br></td></tr>
			<tr><td><input type="password" name="pass" placeholder="确认密码" style="width:200px;height:25px;"><br><br></td></tr>
			<tr><td><input type="submit" value="提交注册" style="width:200px;height:25px;"></td></tr>
			<tr><td><font color=red><s:actionmessage/></font></td></tr>
		</table>
		</form>
	</div>
</body>
</html>