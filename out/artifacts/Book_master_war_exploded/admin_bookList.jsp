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
	<font color="#777777"><strong>图书列表：</strong></font>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>ID</th>
				<th>名称</th>
				<th>作者</th>
				<th>简介</th>
				<th>类别</th>
				<th>数量</th>
				<th>热度</th>
				<th>操作</th>
			</tr>
		</thead>
		<s:iterator value="list" var="b">
			<tr>
			<td><s:property value="#b.id"/></td>
			<td><s:property value="#b.name"/></td>
			<td><s:property value="#b.author"/></td>
			<td><s:property value="#b.context"/></td>
			<td><s:property value="#b.type"/></td>
			<td><s:property value="#b.num"/></td>
			<td><s:property value="#b.hot"/></td>
			<td width="10%">
				<a href="book_delete?id=<s:property value="#b.id"/>"><font color="red">删除</font></a>
			</td>
		</tr>
		</s:iterator>
	</table>
	
</body>
</html>
