<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery2.js"></script>
<script type="text/javascript" src="js/jquery2.sorted.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/ckform.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<style type="text/css">
body {font-size: 20px;
	padding-bottom: 40px;
	background-color: #BED4D4;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
</head>
<body>
	<font color="#777777"><strong>用户列表：</strong></font>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>用户名</th>
				<th>可用额度</th>
				<th>注册日期</th>
				<th>管理菜单</th>
			</tr>
		</thead>
		<s:iterator value="list" var="u">
			<tr>
			<td><s:property value="#u.username"/></td>
			<td><s:property value="#u.account"/></td>
			<td><s:date name="#u.createDate"/></td>
			<td width="10%">
				<a href="javascript:;" onclick="del('<s:property value="#u.uid"/>');"><font color="red">删除</font></a>
			</td>
		</tr>
		</s:iterator>
	</table>
</body>
<script type="text/javascript">
	function del(uid){
		if(confirm("确认删除？")){
			window.location.href="user_delete?uid=" + uid;
		}
	}
</script>
</html>
