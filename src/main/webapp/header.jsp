<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	var pathName = window.location.pathname.substring(1);
	var webName = pathName == '' ? '' : pathName.substring(0, pathName
			.indexOf('/'));
	var BaseUrl = window.location.protocol + '//' + window.location.host + '/'
			+ webName + '/';
	//退出系统方法
	function logout() {
		var r = confirm("您确定要退出本系统吗?");
		if (r == true) {
			location.href = BaseUrl + 'logout.do';
		}
	}
</script>
<div class="nav">
	<div class="midden">
		<ul>
			<c:if test="${sessionScope.activeUser.menus!=null }">
				<c:forEach items="${sessionScope.activeUser.menus }" var="menu">
					<li><a href="${menu.url }">${menu.name }</a></li>
				</c:forEach>
			</c:if>
		</ul>
		<div>
			<span class="daotalk">欢迎您,<span>${sessionScope.username }</span></span>
			<a id="loginOut" href="javascript:logout();">退出系统</a>
		</div>
	</div>
</div>