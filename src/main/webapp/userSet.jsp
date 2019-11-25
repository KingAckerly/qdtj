<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>权限管理</title>
<link rel="stylesheet" type="text/css" href="css/houtai.css">
<link rel="stylesheet" type="text/css" href="js/layer/skin/default/layer.css">
<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/layer/layer.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		/*编辑用户名*/
		$(".retainEdit").click(function() {
			layer.open({
				type : 1,
				title : "编辑用户名",
				skin : 'layui-layer-demo', //样式类名
				closeBtn : 1, //显示关闭按钮
				anim : 2,
				shadeClose : true, //开启遮罩关闭
				content : $(".editNew")
			});
			 $(".failbutton").on("click",function(){
			     layer.closeAll()
			   });

		})
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

		})
	});
</script>
</head>
<body>
	<div class="dateindex">
		<div class="contentALL">
			<table class="datatable" cellpadding="0" cellspacing="0">
				<tr>
					<td>用户名</td>
					<td>所属角色</td>
					<td>操作</td>

				</tr>
				<tr>
					<td></td>
					<td></td>
					<td><span class="retainEdit">编辑</span> <span
						class="deleteEdit">删除</span></td>
				</tr>

				<tr>
					<td></td>
					<td></td>
					<td><span class="retainEdit">编辑</span> <span
						class="deleteEdit">删除</span></td>
				</tr>
			</table>
		</div>
	</div>
	<!-- 编辑渠道-->
	<div class="editNew">
		<div class="name">
			<span>用户名称</span><input type="text" name="" value="" placeholder="">
		</div>
		<ul class="btbutn">
			<li><button type="" class="failbutton">确认编辑</button></li>
			<li><button type="" class="failbutton">取消</button></li>
		</ul>
	</div>
	<!-- 删除-->
	<div class="editAct">
		<div class="name">确认删除该角色？</div>
		<ul class="btbutn">
			<li><button type="" class="failbutton">确认删除</button></li>
			<li><button type="" class="failbutton">取消</button></li>
		</ul>
	</div>
</body>
</html>