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
<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/layer/layer.js" type="text/javascript"></script>
<script type="text/javascript">
	//确认
	function confirm() {
		var roleIds = document.getElementsByName("roleIds");
		var roleId = '';
		for (var i = 0; i < roleIds.length; i++) {
			if (roleIds[i].checked) {
				roleId += roleIds[i].value + ','; //如果选中，将value添加到变量s中    
			}
		}
		//alert(roleId);
		//执行后台操作
		$.ajax({
		url :  '<%=basePath%>' + 'updateUserRole.do',
			type : "post",
			async : false,
			data : {
				"roleIds" : roleId
			},
			success : function(data) {
			},
			error : function() {
				alert("查询角色列表失败");
			}
		});
		//关闭弹窗
		closeLayer();
	}
	//关闭弹窗
	function closeLayer() {
		layer.closeAll();
		$(".userall").addClass("hide");
		$("#userRoleShow").addClass("hide");
	}
</script>
</head>
<body>
	<div class="userall">
		<form action="" method="get" class="perform">
			<c:forEach items="${sessionScope.userRoleList}" var="each"
				varStatus="st">
				<c:choose>
					<c:when test="${each.id != null }">
						<label><input name="roleIds" type="checkbox"
							checked="checked" value="${each.roleId}" />${each.roleCnname}</label>
					</c:when>
					<c:otherwise>
						<label><input name="roleIds" type="checkbox"
							value="${each.roleId}" />${each.roleCnname}</label>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</form>
		<ul class="btbutn">
			<li><button type="button" onclick="confirm();">确认</button></li>
			<li><button type="button" onclick="closeLayer();">取消</button></li>
		</ul>
	</div>
</body>
</html>