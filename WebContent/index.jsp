<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/adminStyle.css" rel="stylesheet" type="text/css" />
<title>图书管理系统</title>
<script type="text/javascript" src="js/jquery1.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$(".div2").click(
						function() {
							$(this).next("div").slideToggle("slow").siblings(
									".div3:visible").slideUp("slow");
						});
			});
	function openurl(url) {
		var rframe = parent.document.getElementById("rightFrame");
		rframe.src = url;
	}
</script>
<style>
body {
	margin: 0;
	font-family: 微软雅黑;
	background-image: url(images/.jpg);
	background-repeat: no-repea;
	background-size: cover;
	background-attachment: fixed;
	background-color: #BED4D4
}

.top1 {
	position: absolute;
	top: 0px;
	width: 100%;
	height: 20px;
	text-align: center;
	color: #FFFFFF;
	font-size: 17px;
	font-height: 20px;
	font-family: 楷体;
	background-color: #B0B0B0
}

.title {
	float: left;
	margin: -32px 20px;
	font-size: 40px;
	color: #FFFFFF;
	font-height: 55px;
	font-family: 隶书;
}

.top2 {
	position: absolute;
	top: 20px;
	width: 100%;
	height: 77px;
	text-align: center;
	color: #ccffff;
	background-color: #D9AFD9;
	background-image: linear-gradient(0deg, #D9AFD9 0%, #97D9E1 100%);

}

.left {
	position: absolute;
	left: 0px;
	top: 97px;
	width: 200px;
	height: 85%;
	border-right: 1px solid #9370DB;
	color: #000000;
	font-size: 20px;
	text-align: center;
	background-color: #D9AFD9;
	background-image: linear-gradient(180deg, #D9AFD9 0%, #97D9E1 100%);


}

.right {
	position: absolute;
	left: 200px;
	top: 97px;
	width: 85.2%;
	height: 85%;
	border-top: 0px solid #484860;
	font-size: 14px;
	text-align: center;
}

.end {
	position: absolute;
	bottom: 0px;
	width: 100%;
	height: 30px;
	text-align: center;
	color: #556B2F;
	font-size: 17px;
	font-height: 20px;
	font-family: 楷体;
	background-color: #C0C0C0
}

.div1 {
	text-align: center;
	width: 200px;
	padding-top: 10px;
}

.div2 {
	height: 40px;
	line-height: 40px;
	cursor: pointer;
	font-size: 18px;
	position: relative;
	border-bottom: #ccc 0px dotted;
}

.spgl {
	position: absolute;
	height: 20px;
	width: 20px;
	left: 40px;
	top: 10px;
	background: url(images/1.png);
}

.yhgl {
	position: absolute;
	height: 20px;
	width: 20px;
	left: 40px;
	top: 10px;
	background: url(images/4.png);
}

.gggl {
	position: absolute;
	height: 20px;
	width: 20px;
	left: 40px;
	top: 10px;
	background: url(images/8.png);
}

.zlgl {
	position: absolute;
	height: 20px;
	width: 20px;
	left: 40px;
	top: 10px;
	background: url(images/4.png);
}

.pjgl {
	position: absolute;
	height: 20px;
	width: 20px;
	left: 40px;
	top: 10px;
	background: url(images/4.png);
}

.tcht {
	position: absolute;
	height: 20px;
	width: 20px;
	left: 40px;
	top: 10px;
	background: url(images/2.png);
}

.div3 {
	display: none;
	cursor: pointer;
	font-size: 15px;
}

.div3 ul {
	margin: 0;
	padding: 0;
}

.div3 li {
	height: 30px;
	line-height: 30px;
	list-style: none;
	border-bottom: #ccc 1px dotted;
	text-align: center;
}

.a {
	text-decoration: none;
	color: #000000;
	font-size: 15px;
}

.a1 {
	text-decoration: none;
	color: #000000;
	font-size: 18px;
}
</style>
</head>
<body>

	<div class="top2">
		<a href="javascript:void(0);">
			<div class="title">
				<h3>图书管理系统</h3>
			</div>
		</a>
		<div class="fr top-link">
			<a href="javascript:void(0);" title="DeathGhost"><i
				class="adminIcon"></i><span>当前用户:${loginUser.username}</span></a>
		</div>
	</div>

	<div class="left">
		<div class="div1">
			<div class="left_top">
				<img src="images/bbb_01.jpg"><img src="images/bbb_02.jpg"
					id="2"><img src="images/bbb_03.jpg"><img
					src="images/bbb_04.jpg">
			</div>

			<div class="div2">
				<div class="tcht"></div>
				个人信息
			</div>
			<div class="div3">
				<li><a class="a" href="javascript:void(0);"
					onClick="openurl('editPassword.jsp');">修改密码</a></li>
			</div>
            <!-- 管理员登录 -->
			<s:if test="#session.loginUser.auth == 0">
				<div class="div2">
					<div class="yhgl"></div>
					图书管理
				</div>
				<div class="div3">
					<ul>
						<li><a class="a" href="javascript:void(0);"
							onClick="openurl('addBook.jsp');">添加图书</a></li>
						<li><a class="a" href="javascript:void(0);"
							onClick="openurl('book_showAll2');">查看图书</a></li>
						<li><a class="a" href="javascript:void(0);"
							onClick="openurl('data_showAll');">所有借阅</a></li>
					</ul>
				</div>
				
	<%--				<div class="div2">--%>
	<%--					<div class="tcht"></div>--%>
	<%--					罚金设置--%>
	<%--				</div>--%>
	<%--				<div class="div3">--%>
	<%--					<ul>--%>
	<%--						<li><a class="a" href="javascript:void(0);"--%>
	<%--							onClick="openurl('user_showFine');">设置罚金</a></li>--%>
	<%--					</ul>--%>
	<%--				</div>--%>
				
				<div class="div2">
					<div class="tcht"></div>
					用户管理
				</div>
				<div class="div3">
					<ul>
						<li><a class="a" href="javascript:void(0);"
							onClick="openurl('user_showAll');">用户列表</a></li>
					</ul>
				</div>
			</s:if>
			<!-- 普通用户登录 -->
			<s:if test="#session.loginUser.auth == 1">
				<!-- 新加 -->
		           <div class="div2">
					<div class="yhgl"></div>
					图书归还
				</div>
				<div class="div3">
					<ul>
						<li><a class="a" href="javascript:void(0);"
							onClick="openurl('data_showByUser');">归还图书</a></li>
					</ul>
				</div>
				 <!-- 新加 -->
		           <div class="div2">
					<div class="yhgl"></div>
					图书续借
				</div>
				<div class="div3">
					<ul>
						<li><a class="a" href="javascript:void(0);"
							onClick="openurl('data_showByUserAndRenew');">续借图书</a></li>
					</ul>
				</div>
				
				<div class="div2">
					<div class="yhgl"></div>
					图书借阅
				</div>
				<div class="div3">
					<ul>
						<li><a class="a" href="javascript:void(0);"
							onClick="openurl('book_showAll?page=1&differ=1');">图书列表</a></li>
					</ul>
				</div>
		           <!-- 新加 -->
		           <div class="div2">
					<div class="yhgl"></div>
					图书检索
				</div>
				<div class="div3">
					<ul>
						<li><a class="a" href="javascript:void(0);"
							onClick="openurl('book_showAll?page=1&differ=2');">查找图书</a></li>
					</ul>
				</div>
			</s:if>

			<a class="a1" href="user_logout">
				<div class="div2">
					<div class="spgl"></div>
					退出登录
				</div>
			</a>
		</div>
	</div>

	<div class="right">
		<iframe id="rightFrame" name="rightFrame" width="100%" height="100%"
			scrolling="auto" marginheight="0" marginwidth="0" align="center"
			style="border: 0px solid #CCC; margin: 0; padding: 0;"></iframe>
	</div>
</body>
</html>
