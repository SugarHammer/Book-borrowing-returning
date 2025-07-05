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
    <script type="text/javascript" src="js/jquerypicture.js"></script>
    
    <style type="text/css">
        body {font-size: 20px;
            padding-bottom: 40px;
            background-color:#BED4D4;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
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
<br>
 <font color="#777777"><strong>添加图书：</strong></font>
<form action="book_add" method="post" class="definewidth m20">
<table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
   <tr>
        <td class="tableleft">名称</td>
        <td><input type="text" name="name"/></td>
    </tr>
    <tr>
        <td class="tableleft">作者</td>
        <td><input type="text" name="author"/></td>
    </tr>
    <tr>
        <td class="tableleft">售价</td>
        <td><input type="text" name="sale"/></td>
    </tr>
    <tr>
        <td class="tableleft">简介</td>
        <td><input type="text" name="context"/></td>
    </tr>
    <tr>
        <td class="tableleft">类型</td>
        <td>
        	<select name="type">
        		<option value="文学">文学</option>
        		<option value="科学">科学</option>
        		<option value="经济">经济</option>
        		<option value="教育">教育</option>
        	</select>
        </td>
    </tr>
    <tr>
        <td class="tableleft">数量</td>
        <td><input type="text" name="num"/></td>
    </tr>
</table>
<font color="red"><s:actionmessage/></font>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-primary">添加</button>
</form>
 <img src="" id="img0" > 
</body>
</html>
