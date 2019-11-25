<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet"  type="text/css" href="css/houtai.css">
	<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
	<title>数据一览</title>
</head>
<script type="text/javascript">
$(function(){
	$(".nav").find('li:eq(0)').addClass("active");
})
</script>
<body>
<%@ include file="header.jsp" %>
	<div class="dateindex">
		<div class="contentALL">
			<table class="datatable" cellpadding="0" cellspacing="0">
				<tr>
					<td>时间</td>
					<td>投资金额</td>
					<td>注册人数</td>
					<td>新增投资人数(首次投资)</td>
				</tr>
				<tr>
					<td>今日</td>
					<td>${dataShow.totalAmountToday}</td>
					<td>${dataShow.totalNumberToday}</td>
					<td>${dataShow.totalInvestmentToday}</td>
				</tr>
			
                <tr>
					<td>昨日</td>
					<td>${dataShow.totalAmountYesterday}</td>
					<td>${dataShow.totalNumberYesterday}</td>
					<td>${dataShow.totalInvestmentYesterday}</td>
				</tr>
				<tr>
					<td>本周</td>
					<td>${dataShow.totalAmountWeek}</td>
					<td>${dataShow.totalNumberWeek}</td>
					<td>${dataShow.totalInvestmentWeek}</td>
				</tr>
				<tr>
					<td>本月</td>
					<td>${dataShow.totalAmountMonth}</td>
					<td>${dataShow.totalNumberMonth}</td>
					<td>${dataShow.totalInvestmentMonth}</td>
				</tr>
			</table>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>