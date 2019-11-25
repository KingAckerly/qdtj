<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>注册统计</title>
	<link rel="stylesheet" href="css/houtai.css">
	<link rel="stylesheet" href="js/laydate/laydate.css">
	<link rel="stylesheet" href="js/bootstrap/css/bootstrap.min.css">
	
	<script src="js/jquery-2.1.4.min.js" type="text/javascript"></script>
	<script src="js/laydate/laydate.js" type="text/javascript"></script>
	<script src="js/jQuery/jquery-2.1.4.min.js" type="text/javascript"></script>
	<script src="js/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="js/bootstrap/js/bootstrap-paginator.min.js" type="text/javascript"></script>
</head>
<script type="text/javascript">

	$(function() {
		$(".nav").find('li:eq(1)').addClass("active");
	});
	var PAGESIZE = 10;
	var options = {
		currentPage : 1, //当前页数  
		totalPages : 10, //总页数，这里只是暂时的，后头会根据查出来的条件进行更改  
		size : "normal",
		alignment : "center",
		itemTexts : function(type, page, current) {
			switch (type) {
			case "first":
				return "第一页";
			case "prev":
				return "前一页";
			case "next":
				return "后一页";
			case "last":
				return "最后页";
			case "page":
				return page;
			}
		},
		onPageClicked : function(e, originalEvent, type, page) {
			var dataRegisterAgain = $("#dataRegisterAgain").val();
			var dataRegisterEnd = $("#dataRegisterEnd").val();
			var channelNumber = jQuery("#channelNumber  option:selected").val();
			var terminalType = jQuery("#terminalType  option:selected").val();
			var isRealName = jQuery("#isRealName  option:selected").val();
			var isCheckCard = jQuery("#isCheckCard  option:selected").val();
			var userName = $("#userName").val();
			var phone = $("#phone").val();
			buildTable(dataRegisterAgain, dataRegisterEnd, channelNumber, 
					terminalType, isRealName, isCheckCard, userName, phone, page, PAGESIZE);//默认每页最多10条  
		}
	}

	//获取当前项目的路径  
	var urlRootContext = (function() {
		var strPath = window.document.location.pathname;
		var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
		return postPath;
	})();

	//生成表格  
	function buildTable(dataRegisterAgain, dataRegisterEnd, channelNumber, 
			terminalType, isRealName, isCheckCard, userName, 
			phone, pageNumber, pageSize) {
		var url = urlRootContext + "/getRegisterByCondition.do"; //请求的网址  
		var reqParams = {
			'dataRegisterAgain' : dataRegisterAgain,
			'dataRegisterEnd' : dataRegisterEnd,
			'channelNumber' : channelNumber,
			'terminalType' : terminalType,
			'isRealName' : isRealName,
			'isCheckCard' : isCheckCard,
			'userName' : userName,
			'phone' : phone,
			'pageNumber' : pageNumber,
			'pageSize' : pageSize
		};//请求数据
		$(function() {
			$.ajax({
				type : "POST",
				url : url,
				data : reqParams,
				async : false,
				dataType : "json",
				success : successData,
				error : function(e) {
					alert("查询失败:" + e);
				}
			});
		});
	}
	
	function successData(data){
		var dataInfo = eval( '(' + data + ')' );
		var isError = dataInfo.isError;
		var sumDataInfo = dataInfo.sumMap;
// 		var pageCount = dataInfo.pages; //取到pageCount的值(把返回数据转成object类型)
        var currentPage = dataInfo.pageNo == 1 ? dataInfo.pageNo : dataInfo.pageNo - 1; //得到currentPage
		if (dataInfo.isError == false) {
			// options.totalPages = data.pages;  
			var newoptions = {
				currentPage : currentPage, //当前页数  
				totalPages : dataInfo.pages == 0 ? 1
						: dataInfo.pages, //总页数  
				size : "normal",
				alignment : "center",
				itemTexts : function(type, page, current) {
					switch (type) {
					case "first":
						return "第一页";
					case "prev":
						return "前一页";
					case "next":
						return "后一页";
					case "last":
						return "最后页";
					case "page":
						return page;
					}
				},
				onPageClicked : function(e, originalEvent,
						type, page) {
					var dataRegisterAgain = $("#dataRegisterAgain").val();
					var dataRegisterEnd = $("#dataRegisterEnd").val();
					var channelNumber = jQuery("#channelNumber  option:selected").val();
					var terminalType = jQuery("#terminalType  option:selected").val();
					var isRealName = jQuery("#isRealName  option:selected").val();
					var isCheckCard = jQuery("#isCheckCard  option:selected").val();
					var userName = $("#userName").val();
					var phone = $("#phone").val();
					buildTable(dataRegisterAgain, dataRegisterEnd, channelNumber, 
							terminalType, isRealName, isCheckCard, userName, phone, page, PAGESIZE);//默认每页最多10条  
				}
			}
			$('#bottomTab').bootstrapPaginator(
					"setOptions", newoptions); //重新设置总页面数目  
			var dataList = dataInfo.dataList;
			$("#tableBody").empty();//清空表格内容 
			
			$("#sumTableBody").empty();//清空表格内容 
			$(dataInfo).each(
					function() {//重新生成  
						$("#sumTableBody").append('<tr>');
						$("#sumTableBody").append('<td>' + sumDataInfo.registerStatSum + '</td>');
						$("#sumTableBody").append('<td>' + sumDataInfo.realNameSum + '</td>');
						$("#sumTableBody").append('<td>' + sumDataInfo.checkCardSum + '</td>');
						$("#sumTableBody").append('</tr>');
					});
			if (dataList.length > 0) {
				$(dataList)
					.each(
						function() {//重新生成  
							$("#tableBody").append('<tr>');
							$("#tableBody").append('<td>' + this.registerChannel + '</td>');
							$("#tableBody").append('<td>' + this.userName + '</td>');
							$("#tableBody").append('<td>' + this.phone + '</td>');
							$("#tableBody") .append('<td>' + this.isRealName + '</td>');
							$("#tableBody") .append('<td>' + this.isCheckCard + '</td>');
							$("#tableBody").append('<td>' + this.registerDate + '</td>');
							$("#tableBody").append('</tr>');
						});
			} else {
				$("#tableBody").append('<tr><th colspan ="4"><center>查询无数据</center></th></tr>');
			}
		} else {
			alert(dataInfo.errorMsg);
		}
	}

	//渲染完就执行  
	$(function() {

		//生成底部分页栏  
		$('#bottomTab').bootstrapPaginator(options);

		buildTable("","","","","","","","", 1, 10);//默认空白查全部  

		//创建结算规则  
		$("#queryButton").bind("click", function() {
			var dataRegisterAgain = $("#dataRegisterAgain").val();
			var dataRegisterEnd = $("#dataRegisterEnd").val();
			var channelNumber = jQuery("#channelNumber  option:selected").val();
			var terminalType = jQuery("#terminalType  option:selected").val();
			var isRealName = jQuery("#isRealName  option:selected").val();
			var isCheckCard = jQuery("#isCheckCard  option:selected").val();
			var userName = $("#userName").val();
			var phone = $("#phone").val();
			buildTable(dataRegisterAgain, dataRegisterEnd, channelNumber, 
					terminalType, isRealName, isCheckCard, userName, phone, 1, PAGESIZE);
		});
	});
	
	function clearSearchData(){
		$(':input','#form')    
		 .not(':button, :submit, :reset, :hidden')    
		 .val('')    
		 .removeAttr('checked')    
		 .removeAttr('selected');
	}
	
	function download(){
    	//var url="downExcel.do";
    	var url="downExcel.do?columnNames="+encodeURI("注册渠道,用户名,手机号码,实名认证,是否绑卡,注册时间")+"&keys=registerChannel,userName,phone,isRealName,isCheckCard,registerDate&page=registerStatConditionList";
    	window.open(url);
	}
</script>
<body>
	<%@ include file="header.jsp"%>
	<form id="form" action="getRegisterByCondition.do" method="post">
	<div class="dateindex">
		<div class="contentALL">
			 <span>
			 	<span>注册时间：</span>
			 	<input type="text" value="" id="dataRegisterAgain">
			 	<span>至</span></span>
			 	<input type="text" value="" id="dataRegisterEnd">
			</span>
			<span>
			 	<span>渠道名称：</span> 
				<select id="channelNumber" name="channelNumber">
						<option value="">全部</option>
						<c:forEach items="${sessionScope.userChannelList}" var="each" varStatus="st">
							<option value="${each.channelNumber}">${each.channelName}</option>
						</c:forEach>
				</select>
			</span>
			<span>
			 	<span>注册终端：</span>
				<select id="terminalType" name="terminalType">
					<option value="">请选择</option>
					<option value="ANDROID">安卓</option>
					<option value="IOS">ios</option>
					<option value="H5">H5</option>
				</select>
				</span>
			<span>
			 	<span>实名认证：</span>
				<select id="isRealName" name="isRealName">
					<option value ="">请选择</option>
					<option value ="1">是</option>
			  		<option value ="0">否</option>
				</select>
			</span>
			<span>
			 	<span>是否绑卡：</span>
				<select class="mr0" id="isCheckCard" name="isCheckCard">
					<option value ="">请选择</option>
					<option value ="1">是</option>
			  		<option value ="0">否</option>
				</select>
			</span>
			<span>
			 	<span>用户名搜索：</span>
				<input class="user_name" type="text" id="userName" name="userName" placeholder="请输入用户名">
			</span>
			<span>
			 	<span>手机号码搜索：</span>
					<input class="phone" type="text" id="phone" name="phone" placeholder="请输入手机号">
			</span>
				<input type="button" id="queryButton" class="find getout fr" value="查询"/>
				<input type="button" id="clearSearch" onclick="clearSearchData();" class="find getout fr" value="清空"/>
				<input type="button" class="getout fr" onclick="download();" value="导出"/>
			</span>
			<table class="datatable" cellpadding="0" cellspacing="0">
				<thead>
				<tr>
					<td>注册用户量汇总数</td>
					<td>实名认证用户汇总数</td>
					<td> 成功绑卡用户汇总数</td>
				</tr>
				</thead>
				<tbody id="sumTableBody">
				</tbody>
<!-- 			    <tr> -->
<%-- 					<td>${registerStatSumMap.registerStatSum }</td> --%>
<%-- 					<td>${registerStatSumMap.realNameSum }</td> --%>
<%-- 					<td>${registerStatSumMap.checkCardSum }</td> --%>
<!-- 				</tr> -->
			</table>
			<table class="datatable" cellpadding="0" cellspacing="0">
				<thead>
				<tr>
					<td>注册渠道</td>
					<td>用户名</td>
					<td>手机号码</td>
					<td>实名认证</td>
					<td>是否绑卡</td>
					<td>注册时间</td>
				</tr>
				</thead>
				<tbody id="tableBody">
				</tbody>
<%-- 				<c:forEach items="${registerStatList}" var="registerStatList" varStatus="rvs"> --%>
<!-- 				<tr> -->
<%-- 					<td>${registerStatList.registerChannel }</td> --%>
<%-- 					<td>${registerStatList.userName }</td> --%>
<%-- 					<td>${registerStatList.phone }</td> --%>
<%-- 					<td>${registerStatList.isRealName }</td> --%>
<%-- 					<td>${registerStatList.isCheckCard }</td> --%>
<%-- 					<td>${registerStatList.registerDate }</td> --%>
<!-- 				</tr> -->
<%-- 				</c:forEach> --%>
			</table>
		</div>
	</div>
	<!-- 底部分页按钮 -->
	<div id="bottomTab"></div>
	</form>
<%-- 	<%@ include file="footer.jsp" %> --%>
	<script type="text/javascript">
	//日期选择
		laydate.render({
		  elem: '#dataRegisterAgain',
		  type: 'datetime'
		});
		laydate.render({
			  elem: '#dataRegisterEnd',
			  type: 'datetime'
			});
    </script>
</body>
</html>
