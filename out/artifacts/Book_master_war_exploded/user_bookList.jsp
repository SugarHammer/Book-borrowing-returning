<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery2.js"></script>
<script type="text/javascript" src="js/jquery2.sorted.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/ckform.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<!-- 弹出层插件 -->
<link href="${pageContext.request.contextPath}/css/popup_layer.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/popup_layer.js"></script>		
<!-- 调用插件弹出层的方法 -->
<script type="text/javascript">
	$(function(){
		//弹出层插件调用
		new PopupLayer({
			trigger:".clickedElement",//触发点 点击谁弹出div
			popupBlk:"#showDiv",//弹出哪个div
			closeBtn:"#closeBtn",//关闭按钮
			useOverlay:true
		});
		
	});
	
	//点击按钮查询某个订单的详情
	function findBookInfoById(id){
		//清理上一次显示的内容覆盖
		$("#showDivTab").html("");
		$("#shodDivID").html("");
		$("#loading").css("display","block");
		//ajax异步访问数据
		$.post(
			"${pageContext.request.contextPath }/book_findBookInfoById",
			{"id":id},
			function(data){//这里的data就相当于后台传来的book
				//隐藏加载图片
				$("#loading").css("display","none");
				var content = "<tr id='showTableTitle'><th width='20%'>图书名称</th><th width='25%'>作者</th><th width='20%'>售价</th><th width='15%'>简介</th><th width='20%'>热度</th></tr>";
				for(var i=0;i<data.length;i++){
					content+="<tr style='text-align: center;'>"+
					"<td>"+data[i].name+"</td>"+
					"<td>"+data[i].author+"</td>"+
					"<td>"+data[i].sale+"</td>"+
					"<td>"+data[i].context+"</td>"+
					"<td>"+data[i].hot+"</td>"+
					"</tr>";
				}
				$("#showDivTab").html(content);
				//订单编号
				$("#shodDivID").html(id);
			},
			"json"
		);
	}
</script>
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
	<font color="#777777"><strong>借阅情况：</strong></font>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>显示卡号</th>
				<th>姓名</th>
				<th>借书限额</th>
				<th>已借数量</th>
				<th>可借数量</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${loginUser.uid}</td>
				<td>${loginUser.username}</td>
				<td>${loginUser.bookquota}</td>
				<td>${loginUser.amountborrowed}</td>
				<td>${loginUser.bookquota-loginUser.amountborrowed}</td>
			 </tr>
		 </tbody>
	</table>
	<font color="#777777"><strong>图书列表：</strong></font>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>图书编号</th>
				<th>图书名称</th>
				<th>类型</th>
				<th>图书数量</th>
				<th>热度</th>
				<th>操作</th>
				<th>查看</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" var="b">
				<tr>
					<td><s:property value="#b.id"/></td>
					<td><s:property value="#b.name"/></td>
					<td><s:property value="#b.type"/></td>
					<td><s:property value="#b.num"/></td>
					<td><s:property value="#b.hot"/></td>
					<td width="10%">
						<s:if test="#b.num > 0">
							<a href="data_add?bid=<s:property value="#b.id"/>">借书</a>
						</s:if>
					</td>
					<td width="10%">
						<input type="button" value="图书详情" class="clickedElement" onclick="findBookInfoById('${b.id}')"/>
					</td>
			    </tr>
		    </s:iterator>
		    <tr>
		        <td colspan="7">共<s:property value="total"/>条记录 | 共<s:property value="pageTotal"/>页 | 当前第<s:property value="page"/>页 | <a href="book_showAll?page=<s:property value="page -1"/>">上一页</a> | <a href="book_showAll?page=<s:property value="page +1"/>">下一页</a>
		        </td>
		    </tr>
		</tbody>
	</table>
	<!-- 弹出层 -->
        <div id="showDiv" class="blk" style="display:none;">
            <div class="main">
                <h2>图书编号：<span id="shodDivID" style="font-size: 13px;color: #999">123456789</span></h2>
                <a href="javascript:void(0);" id="closeBtn" class="closeBtn">关闭</a>
                <div id="loading" style="padding-top:30px;text-align: center;">
                	<img alt="" style="width:100px;height:100px;" src="${pageContext.request.contextPath }/images/loading.gif">
                </div>
				<div style="padding:20px;">
					<table id="showDivTab" style="width:100%">
						
					</table>
				</div>
            </div>
            
        </div>
</body>
</html>
