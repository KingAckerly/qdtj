<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	// 获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	// 将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。 
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>用户角色局部引用</title>
<link rel="stylesheet" href="css/houtai.css">
</head>
<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/layer/layer.js" type="text/javascript"></script>
<script type="text/javascript">
//删除用户
function deleteEdit(id){
	$.ajax({
		url :  "<%=basePath%>deleteUser.do?id="+id,
		type : "post",
		async : false,
		dateType : "json",
		success : function(data) {
			$("#deleteUser").load("userShow.jsp");
		},
		error : function() {
			alert("删除用户失败！");
			window.location.href="userShow.jsp";
		}
	});
}
//编辑用户
function retainEdit(id){
	document.getElementById("id").value = id;
	layer.open({
		type : 1,
		title : "编辑用户名",
		skin : 'layui-layer-demo', //样式类名
		closeBtn : 1, //显示关闭按钮
		anim : 2,
		shadeClose : true, //开启遮罩关闭
		content : $(".editNew")
	});
}
//编辑用户提交
function submitUser(){
	var id = document.getElementById("id").value;
	var userName = document.getElementById("userName").value;
	var pwd = document.getElementById("pwd").value;
	$.ajax({
		url :  '<%=basePath%>' + 'updateUser.do',
		type : "post",
		async : false,
		data:{
			"id" : id,
			"userName" : userName,
			"pwd" : pwd
		},
		success : function(data) {
			$("#deleteUser").load("userShow.jsp");
		},
		error : function() {
			alert("编辑用户失败");
			window.location.href="userShow.jsp";
		}
	});
	     layer.closeAll();
}
//查询角色列表
function usersetopen(id){
	$.ajax({
		url :  "<%=basePath%>getUserRoleList.do?id="+id,
		type : "post",
		async : false,
		dateType : "json",
		success : function(data) {
			$("#userRoleShow").load("userRoleShow.jsp");
		},
		error : function() {
			alert("查询角色列表失败");
		}
	});
	layer.open({
		type : 1,
		title : "角色管理",
		skin : 'layui-layer-demo', //样式类名
		closeBtn : 1, //显示关闭按钮
		anim : 2,
		shadeClose : true, //开启遮罩关闭
		content : $(".userSet")
	});
}
//关闭弹窗
$(".failbutton").on("click",function(){
    layer.closeAll();
  });
</script>
<body>
	<div id="deleteUser">
		<table class="datatable" cellpadding="0" cellspacing="0">
			<tr>
				<td>用户ID</td>
				<td>用户名称</td>
				<td>角色设置</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${userList}" var="each" varStatus="st">
				<tr>
					<td>${each.id}</td>
					<td>${each.userName}</td>
					<td><button onclick="usersetopen(${each.id});">查看</button></td>
					<td><a href="javascript:void(0)"
						onclick="retainEdit(${each.id});">编辑</a> <a
						href="javascript:void(0)" onclick="deleteEdit(${each.id});">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<!-- 编辑用户-->
		<div class="editNew">
			<div class="name">
				<span>用户名</span><input type="text" id="userName" name="userName"
					value="" placeholder="">
			</div>
			<div class="pwd">
				<span>密码</span><input type="text" id="pwd" name="pwd" value=""
					placeholder="">
			</div>
			<input type="hidden" id="id" name="id" value="" placeholder="">
			<ul class="btbutn">
				<li><button type="button" onclick="submitUser();">确认编辑</button></li>
				<li><button type="" class="failbutton">取消</button></li>
			</ul>
		</div>
	</div>
</body>
</html>