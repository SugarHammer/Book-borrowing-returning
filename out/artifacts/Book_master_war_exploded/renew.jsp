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
	<font color="#777777"><strong>还书列表：</strong></font>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>ID</th>
				<th>图书ID</th>
				<th>图书名称</th>
				<th>借书人</th>
				<th>借书时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<s:iterator value="list" var="d">
			<tr>
			<td><s:property value="#d.id"/></td>
			<td><s:property value="#d.bid"/></td>
			<td><s:property value="#d.name"/></td>
			<td><s:property value="#d.user"/></td>
			<td><s:date name="#d.createDate"/></td>
			<td width="10%">
				<a href="data_updateDate?id=<s:property value="#d.id"/>">续借</a>
			</td>
		</tr>
		</s:iterator>
	</table>
	
</body>
</html>
