<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>渠道管理</title>
<link rel="stylesheet" href="css/houtai.css">
<link rel="stylesheet" href="js/layer/skin/default/layer.css">
<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/layer/layer.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$(".nav").find('li:eq(3)').addClass("active");
	/*新增渠道*/
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
/**
 * 修改渠道
 */
function editChannelName(channelId){
	document.getElementById("channelId").value = channelId;
	layer.open({
		  type: 1,
		  title:"编辑渠道",
		  skin: 'layui-layer-demo', //样式类名
		  closeBtn: 1, //显示关闭按钮
		  anim: 2,
		  shadeClose: true, //开启遮罩关闭
		  content: $(".editNew")
	   });
$(".failbutton").on("click",function(){
   layer.closeAll()
 });
return false;
};
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<form id="form1" action="getChannelList.do" method="post">
		<div class="dateindex">
			<div class="contentALL">

				<span> <span>渠道名称：</span>  <input type="text" id="channelName" name="channelName" />
				</span>

				<input type="submit" value="搜索" class="find getout" /> <span
					class="getout  magenewAdd">新增</span>
				<table class="datatable" cellpadding="0" cellspacing="0">
					<tr>
						<td>渠道ID</td>
						<td>渠道名称</td>
						<td>渠道识别号</td>
						<td>选择</td>
					</tr>

					<c:forEach items="${channelList}" var="each" varStatus="st">
						<tr>
							<td>${each.channelId}</td>
							<td>${each.channelName}</td>
							<td>${each.channelNumber}</td>
							<td><a href="javascript:void(0)"
								onclick="editChannelName(${each.channelId});">编辑</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</form>
	<%@ include file="footer.jsp"%>
	<!-- 新增渠道-->
	<form id="form2" action="addChannel.do" method="post">
		<div class="addNew">
			<div class="name">
				<span>渠道名称</span><input type="text" id="channelName"
					name="channelName" value="" placeholder="">
			</div>
			<div class="num">
				<span>识别号</span><input type="text" id="channelNumber"
					name="channelNumber" value="" placeholder="">
			</div>
			<ul class="btbutn">
				<li><button type="submit" class="failbutton">确认新增</button></li>
				<li><button type="button" class="failbutton">取消</button></li>
			</ul>
		</div>
	</form>
	<!-- 编辑渠道-->
	<form id="form3" action="updateChannel.do" method="post">
		<div class="editNew">
			<div class="name">
				<span>渠道名称</span><input type="text" id="channelName"
					name="channelName" value="" placeholder="">
			</div>
			<div class="num">
				<span>识别号</span><input type="text" id="channelNumber"
					name="channelNumber" value="" placeholder="">
			</div>
			<input type="hidden" id="channelId" name="channelId" value=""
				placeholder="">
			<ul class="btbutn">
				<li><button type="submit" class="failbutton">确认编辑</button></li>
				<li><button type="button" class="failbutton">取消</button></li>
			</ul>
		</div>
	</form>
</body>
</html>