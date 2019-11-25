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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>权限管理</title>
<link rel="stylesheet" href="css/houtai.css">
<link rel="stylesheet" type="text/css"
	href="js/layer/skin/default/layer.css">
<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/layer/layer.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$(".nav").find('li:eq(4)').addClass("active");
	})
	//查看用户对应的权限
	function getPermissionByUserId(roleId){
		$.ajax({
			url :  '<%=basePath%>'+'getPermissionByUserId.do?roleId='+roleId,
			type : "post",
			async : false,
			dateType : "json",
			success : function(data) {
				 $("#mainPermission").load("permissionPage.jsp");
			},
			error : function() {
				alert("异常情况");
			}
		});
	}
	/*查看权限*/
	function permissionopenForRole(roleId) {
		getPermissionByUserId(roleId);
		layer.open({
			type : 1,
			title : "权限管理",
			skin : 'layui-layer-demo', //样式类名
			closeBtn : 1, //显示关闭按钮
			anim : 2,
			shadeClose : true, //开启遮罩关闭
			content : $(".lookSet")
		});
	}
	//关闭弹窗
	$(".failbutton").on("click",function(){
	    layer.closeAll()
	  });
	
	//删除用户
	function deleteEdit(roleId){
		if(roleId == 1){
			alert("超级管理员不允许被删除");
		}else{
			$.ajax({
				url :  "<%=basePath%>deleteRole.do?roleId="+roleId,
				type : "post",
				async : false,
				dateType : "json",
				success : function(data) {
					$("#roleShow").load("roleShow.jsp");
				},
				error : function() {
					alert("删除角色失败！");
					$("#roleShow").load("roleShow.jsp");
				}
			});
		}
	}
	
	//编辑用户
	function retainEdit(roleId){
		document.getElementById("roleId").value = roleId;
		layer.open({
			type : 1,
			title : "编辑角色名",
			skin : 'layui-layer-demo', //样式类名
			closeBtn : 1, //显示关闭按钮
			anim : 2,
			shadeClose : true, //开启遮罩关闭
			content : $(".editNew")
		});
	}
	
	//编辑角色提交
	function submitRole(){
		var roleId = document.getElementById("roleId").value;
		var roleCnname = document.getElementById("roleCnname").value;
		$.ajax({
			url :  '<%=basePath%>' + 'updateRole.do',
			type : "post",
			async : false,
			data:{
				"roleId" : roleId,
				"roleCnname" : roleCnname
			},
			success : function(data) {
				$("#roleShow").load("roleShow.jsp");
			},
			error : function() {
				alert("编辑用户失败");
				window.location.href="permissionSet.jsp";
			}
		});
		     layer.closeAll();
	}
</script>
</head>
<body>
	<div id="roleShow" class="contentALL permission">
		<table class="datatable" cellpadding="0" cellspacing="0">
			<tr>
				<td>角色名称</td>
				<td>角色描述</td>
				<td>权限设置</td>
				<td>操作</td>

			</tr>
			<c:forEach items="${sessionScope.roleList }" var="roleList">
				<tr>
					<td>${roleList.roleCnname }</td>
					<td>${roleList.desc }</td>
					<td>
						<button onclick="permissionopenForRole(${roleList.roleId });">查看</button></td>
					<td>
						<a href="javascript:void(0)" onclick="retainEdit(${roleList.roleId});">编辑</a> 
						<a href="javascript:void(0)" onclick="deleteEdit(${roleList.roleId});">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<!-- 编辑角色-->
	<div class="editNew">
		<div class="name">
			<label>角色名</label>
			<input type="text" id="roleCnname" name="roleCnname" value="" />
		</div>
			<input type="hidden" id="roleId" name="roleId" value="" />
		<ul class="btbutn">
			<li><button type="button" onclick="submitRole();">确认编辑</button></li>
			<li><button type="button" class="failbutton">取消</button></li>
		</ul>
	</div>
</body>
</html>
