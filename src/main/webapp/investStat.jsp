<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>投资统计</title>
<link rel="stylesheet" href="css/houtai.css">
<script src="js/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/laydate/laydate.css">
<script src="js/laydate/laydate.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$(".nav").find('li:eq(2)').addClass("active");
	})
	function download(){
    	//var url="downExcel.do";
    	var url="downExcel.do?columnNames="+encodeURI("用户名,手机号码,投资标的,投资金额,投资时间")+"&keys=userName,phone,invBidType,invAmount,invDate&page=investmentList";
    	window.open(url);
	}
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<form id="form1" action="selectByCondition.do" method="post">
	<div class="dateindex">
		<div class="contentALL">
			<span> <span>投资时间：</span> 
			<input type="text" value="" id="dataInvestAgain" name="dataInvestAgain">
			 	<span>至</span></span>
			 	<input type="text" value="" id="dataInvestEnd" name="dataInvestEnd">
			 <span> <span>渠道名称：</span> 
			<select name="channelNumber">
					<option value="">全部</option>
					<c:forEach items="${userChannelList}" var="each" varStatus="st">
						<option value="${each.channelNumber}">${each.channelName}</option>
					</c:forEach>
			</select>
			</span> <span> <span>投资标的：</span> <select name="invBidType">
					<option value="">全部</option>
					<c:forEach items="${t6211List}" var="each" varStatus="st">
						<option value="${each.id}">${each.type}</option>
					</c:forEach>
			</select>
			</span> 
			<span> <span>用户名搜索：</span> <input class="user_name"
				type="text" id="userName" name="userName" value="" placeholder="请输入用户名">
			</span> <span> <span>手机号码搜索：</span> <input class="phone" type="text"
				id="phone" name="phone" value="" placeholder="请输入手机号">
			</span> <input type="submit" class="find getout fr" value="查询"/> <input type="button" class="getout fr" onclick="download();" value="导出"/>
			<table class="datatable" cellpadding="0" cellspacing="0">
				<tr>
					<td>总投资人次汇总数</td>
					<td>总投资金额汇总数</td>
					<td> 首次投资金额汇总数</td>
					<td>首次投资人数汇总数</td>
				</tr>
			    <tr>
					<td>${investmentSum.invPeoSum}</td>
					<td>${investmentSum.invAmoSum}</td>
					<td>${investmentSum.firstInvAmoSum}</td>
					<td>${investmentSum.firstInvPeoSum}</td>
				</tr>
			</table>
			<table class="datatable" cellpadding="0" cellspacing="0">
				<tr>
					<td>用户名</td>
					<td>手机号码</td>
					<td>投资标的</td>
					<td>投资金额</td>
					<td>投资时间</td>
				</tr>
				<c:forEach items="${investmentList}" var="each" varStatus="st">
					<tr>
						<td>${each.userName}</td>
						<td>${each.phone}</td>
						<td>${each.invBidType}</td>
						<td>${each.invAmount}</td>
						<td>${each.invDate}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	</form>
	<%@ include file="footer.jsp"%>
		<script type="text/javascript">
	//日期选择
		laydate.render({
		  elem: '#dataInvestAgain',
		  type: 'datetime'
		});
		laydate.render({
			  elem: '#dataInvestEnd',
			  type: 'datetime'
			});
    </script>
</body>
</html>