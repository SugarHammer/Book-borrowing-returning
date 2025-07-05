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
	<font color="#777777"><strong>搜索结果：</strong></font>
	<br/>
	<div style="float:right;">
		<form action="book_showByKey" method="post">
			<input type="text" name="name" placeholder="名称查找图书" style="margin-top:10px;">
			<input type="submit" value="Go">
		</form>
	</div>
	<div style="float:right;">
		<form action="book_showByType" method="post">
			<input type="text" name="type" placeholder="类别查找图书" style="margin-top:10px;">
			<input type="submit" value="Go">
		</form>
	</div>
	<div style="float:right;">
		<form action="book_showByHot" method="post">
			<input type="submit" value="查看受欢迎程度" style="margin-top:10px;">
		</form>
	</div>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>姓名</th>
				<th>图书名称</th>
				<th>作者</th>
				<th>售价</th>
				<th>简介</th>
				<th>类型</th>
				<th>图书数量</th>
				<th>热度</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" var="b">
				<tr>
					<td>${loginUser.username}</td>
					<td><s:property value="#b.name"/></td>
					<td><s:property value="#b.author"/></td>
					<td><s:property value="#b.sale"/></td>
					<td><s:property value="#b.context"/></td>
					<td><s:property value="#b.type"/></td>
					<td><s:property value="#b.num"/></td>
					<td><s:property value="#b.hot"/></td>
					<td width="10%">
						<s:if test="#b.num > 0">
							<a href="data_add?bid=<s:property value="#b.id"/>">借书</a>
						</s:if>
					</td>
			    </tr>
		    </s:iterator>
		    <tr>
		        <td colspan="7">共<s:property value="total"/>条记录 | 共<s:property value="pageTotal"/>页 | 当前第<s:property value="page"/>页 | <a href="book_showAll?page=<s:property value="page -1"/>">上一页</a> | <a href="book_showAll?page=<s:property value="page +1"/>">下一页</a>
		        </td>
		    </tr>
		</tbody>
	</table>
</body>
</html>
