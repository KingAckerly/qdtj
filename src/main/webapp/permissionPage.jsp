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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>数据一览</title>
</head>
<script type="text/javascript">
//确认
function confirmSubmit() {
	var roleId = document.getElementById("roleIdEdit").value;
	var permissionArray = document.getElementsByName("permissionArray");
	var permissionS = '';
	for (var i = 0; i < permissionArray.length; i++) {
		if (permissionArray[i].checked) {
			permissionS += permissionArray[i].value + ','; //如果选中，将value添加到变量s中    
		}
	}
	var channelArray = document.getElementsByName("channelArray");
	var channelS = '';
	for (var i = 0; i < channelArray.length; i++) {
		if (channelArray[i].checked) {
			channelS += channelArray[i].value + ','; //如果选中，将value添加到变量s中    
		}
	}
	$.ajax({
		url :  '<%=basePath%>' + 'dealPermissionForRole.do',
		type : "post",
		async : false,
		data : {
			"roleId" : roleId,
			"channelS" : channelS,
			"permissionS" : permissionS
		},
		success : function(data) {
			//关闭弹窗
			closeLayer();
		},
		error : function() {
			alert("操作失败");
		}
	});
	
}

function closeLayer(){
	parent.layer.close(layer.index);
}
</script>
<body>
	<form action="" method="post" class="perform">
		<b>菜单权限管理</b>
		<br />
		<c:forEach items="${sessionScope.permissionList }"
			var="permissionList">
			<c:choose>
				<c:when test="${permissionList.roleId != null }">
					<label>
						<input type="checkbox" name="permissionArray"
							checked="checked" value="${permissionList.permissionId }" />${permissionList.permissionName } 
						</label>
				</c:when>
				<c:otherwise>
					<label> 
						<input type="checkbox" name="permissionArray"
						value="${permissionList.permissionId }" />${permissionList.permissionName }
					</label>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<br />
		<b>渠道权限管理</b>
		<br />
		<c:forEach items="${sessionScope.channelList }"
			var="channelList">
			<c:choose>
				<c:when test="${channelList.roleId != null }">
					<label>
						<input type="checkbox" name="channelArray"
							checked="checked" value="${channelList.channelId }" />${channelList.channelName } 
						</label>
				</c:when>
				<c:otherwise>
					<label> 
						<input type="checkbox" name="channelArray"
						value="${channelList.channelId }" />${channelList.channelName }
					</label>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<input type="hidden" value="${sessionScope.roleId }" id="roleIdEdit" name="roleIdEdit" />
		<ul class="btbutn">
			<li><button type="button" class="failbutton" onclick="javascript:confirmSubmit();">确认</button></li>
			<li><button type="button" class="failbutton" onclick="javascript:closeLayer();">取消</button></li>
		</ul>
	</form>
</body>
</html>