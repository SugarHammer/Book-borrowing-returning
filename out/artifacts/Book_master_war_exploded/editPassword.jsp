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
 <font color="#777777"><strong>密码修改：</strong></font>
<form action="user_editPass" method="post" class="definewidth m20">
<input type="hidden" name="uid" value="<s:property value="#session.loginUser.uid"/>">
<table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
    <tr>
        <td class="tableleft">用户名</td>
        <td><input type="text" name="username" readonly="readonly" value="<s:property value="#session.loginUser.username"/>"></td>
	</tr>
	<tr>
        <td class="tableleft">新密码</td>
        <td><input type="password" name="password"/>*</td>
    </tr>
	<tr>
        <td class="tableleft">确认密码</td>
        <td><input type="password" name="pass"/>*</td>
    </tr>
	
	
</table>
	<font color="red" size="3"><s:actionmessage/></font>
	<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-primary">提交</button>
</form>
 <img src="" id="img0" > 
 

