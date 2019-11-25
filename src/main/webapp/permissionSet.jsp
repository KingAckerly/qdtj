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
	$(function(){
		/*新增用户*/
		$(".magenewAdd").click(function(){
	         layer.open({
					  type: 1,
					  title:"新增渠道",
					  skin: 'layui-layer-demo', //样式类名
					  closeBtn: 1, //显示关闭按钮
					  anim: 2,
					  shadeClose: true, //开启遮罩关闭
					  content: $(".addNew")
				   });
	         $(".failbutton").on("click",function(){
			     layer.closeAll()
			   });

		})
		});
	//新增用户
	function addUser(){
		//获取account,userName,pwd
		var account = document.getElementById("account").value;
		var userName = document.getElementById("username").value;
		var pwd = document.getElementById("password").value;
		$.ajax({
			url :  "<%=basePath%>addUser.do?account="+account+"&userName="+userName+"&pwd="+pwd,
			type : "post",
			async : false,
			dateType : "json",
			success : function(data) {
				$("#userShow").load("userShow.jsp");
			},
			error : function() {
				alert("新增用户失败");
			}
		});
		layer.closeAll();
	}
	$(function() {
		$(".permm").click(function() {
			$(".magenewAdd").hide();
		});
		$(".usermm").click(function() {
			$(".magenewAdd").show();
		});
		/*删除*/
		$(".deleteEdit").click(function() {
			layer.open({
				type : 1,
				title : "删除角色",
				skin : 'layui-layer-demo', //样式类名
				closeBtn : 1, //显示关闭按钮
				anim : 2,
				shadeClose : true, //开启遮罩关闭
				content : $(".editAct")
			});
			 $(".failbutton").on("click",function(){
			     layer.closeAll()
			   });
		});
		$(".usermm").click(function() {
			//查询用户列表
			$.ajax({
				url :  '<%=basePath%>'+'getUserList.do',
				type : "post",
				async : false,
				dateType : "json",
				success : function(data) {
					$("#userShow").load("userShow.jsp");
				},
				error : function() {
					alert("异常情况");
				}
			});
			$(".usermm").addClass("active");
			$(".permm").removeClass("active");
			$(".permission").addClass("hide");
			$(".usermanage").removeClass("hide");
		});
		$(".permm").click(function() {
			$(".permm").addClass("active");
			$(".usermm").removeClass("active");
			$(".usermanage").addClass("hide");
			$(".permission").removeClass("hide");
		});
	});
	//关闭弹窗
	$(".failbutton").on("click",function(){
	    layer.closeAll()
	  });
	window.onload= function(){
		//查询用户列表
		$.ajax({
			url :  '<%=basePath%>'+'getRoleList.do',
			type : "post",
			async : false,
			dateType : "json",
			success : function(data) {
				$("#roleShow").load("roleShow.jsp");
			},
			error : function() {
				alert("异常情况");
			}
		});
	};
			
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="dateindex">
		<ul class="list">
			<li class="active permm">权限管理</li>
			<li class="usermm">用户管理</li>
		</ul>

		<!-- 新增用户的按钮 -->
		<button class="magenewAdd hide fr">新增</button>

		<div id="roleShow"></div>
		<div id="userShow" class="contentALL usermanage hide"></div>
	</div>
	
	<!-- 查看权限div begin -->
	<div id="mainPermission" class="lookSet hide"
		style="min-height: 300px;"></div>
	<!-- 查看权限div end -->

	<!-- 用户角色div begin -->
	<div id="userRoleShow" class="userSet hide" style="min-height: 300px;">
	</div>
	<!-- 用户角色div end -->

	<!-- 新增用户的弹窗begin -->
	<div class="addNew">
		<div class="name">
			<span>账号</span><input type="text" id="account" name="account"
				value="" />
		</div>
		<div class="name">
			<span>用户名</span><input type="text" id="username" name="username"
				value="" />
		</div>
		<div class="name">
			<span>密码</span><input type="text" id="password" name="password"
				value="" />
		</div>
		<ul class="btbutn">
			<li><button type="submit" onclick="addUser();">确认新增</button></li>
			<li><button type="button" class="failbutton">取消</button></li>
		</ul>
	</div>
	<!-- 新增用户的弹窗end -->

	<%@ include file="footer.jsp"%>
</body>
</html>
