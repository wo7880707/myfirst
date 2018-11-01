<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.ry.szq.pojo.Trade" %>
<%@page import="com.ry.szq.common.Pager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="../styles/common.css" />
<script src="../bootstrap/js/jquery.js" type="text/javascript" charset="UTF-8"></script>
<script type="text/javascript" src="../js/main.js" charset="utf-8"></script>
<script src="../js/highcharts.js" type="text/javascript" charset="UTF-8"></script>
<script src="../js/exporting.src.js" type="text/javascript" charset="UTF-8"></script>
<script src="../js/data.src.js" type="text/javascript" charset="UTF-8"></script>
<script type="text/javascript" src="../js/alltrade.js" charset="utf-8"></script>
<script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>

<body>
<div class="page-header">
		</div>
		<div class="page-body">
		<div class="page-sidebar">
			</div>
			<div class="page-content">
				<div class="content-nav">
					交易管理> 交易信息> 图表信息
				</div>
				<!-- 导航栏 -->
				<button id = "all">全选</button>
				<button id = "noall">反选</button>
				<button id = "no">不选</button>
				<button id = "de">删除</button>
				<br /><br />
				<!-- 导航栏结束 -->
				<span id="" style="margin-left: 70px;">开始时间:</span>
				<input type="date" id="startdate" value="2017-01-01" style="height: 30px; width: 130px;"/> 
				<span id="">结束时间:</span>
				<input type="date" id="enddate"   style="height: 30px; width: 130px;"/> 
			
				<button id="deletemails" style=" width: 130px; margin-bottom: 10px; height: 30px;font-size:16px ;">查询</button>
				<br/><br/>
				<!-- 分页 -->
				<select id = "paper">
			<%
			Pager p = (Pager)request.getAttribute("pager");
			for(int i = 1; i <= p.getPageCount(); i ++){
				if(i == p.getPageNum()){
					%>
					<a href="dividePager.action?pageNum=<%=i %>"><option value = "<%=i %>"><%=i %>/<%=p.getPageCount() %></option></a>
					<%
				}else{
			%>
			<a href="dividePager.action?pageNum=<%=i %>"><option value = "<%=i %>"><%=i %>/<%=p.getPageCount() %></option></a>	
				
			<%} }%>
		</select>
				<br/>
				<button id="deletemails" style=" width: 130px; margin-bottom: 10px; height: 30px;font-size:16px ;">功能查询</button>
	<table class="listtable" id = "table">	
			<tr class="listheader"><th></th><th>编号</th><th>日期</th><th>白昼</th><th>杠上开账户</th><th>交易账户</th><th>策略名</th><th>ID</th><th>合约</th><th>期望收益</th><th>平仓盈亏</th><th>持仓盈亏</th><th>浮动收益</th><th>最新价</th><th>多持仓</th><th>空持仓</th><th>手续费</th></tr>
			<c:forEach items="${trade}" var="d">
				
				<tr>
					<td><input type="checkbox" value="${d.id}" name = "delete"/></td>
					<td>${d.id}</td>
					<td>${d.time}</td>
					<td>${d.dn }</td>
					<td>${d.gskCount }</td>
					<td>${d.tradeCount }</td>
					<td>${d.clName }</td>
					<td>${d.csID }</td>
					<td>${d.contract }</td>
					<td>${d.qwsy }</td>
					<td>${d.pcyk }</td>
					<td>${d.ccyk }</td>
					<td>${d.fdsy }</td>
					<td>${d.zxj }</td>
					<td>${d.dcc }</td>
					<td>${d.kcc }</td>
					<td>${d.sxf }</td>
				</tr>
			</c:forEach>
		</table>
		<button id="deletemails" style=" width: 130px; margin-bottom: 10px; height: 30px;font-size:16px ;">上一页</button>
		<button id="deletemails" style=" width: 130px; margin-bottom: 10px; height: 30px;font-size:16px ;">下一页</button>
		<!-- 页码信息 -->
		<!-- 页码信息显示在下拉菜单中 -->
		<%-- <ul class="pagination pull-right">
			<%
			Pager p = (Pager)request.getAttribute("pager");
			for(int i = 1; i <= p.getPageCount(); i ++){
				if(i == p.getPageNum()){
					%>
					<li style = "display:inline" ><a href="dividePager.action?pageNum=<%=i %>"><%=i %></a></li>	
					<%
				}else{
			%>
			<li style = "display:inline"><a href="dividePager.action?pageNum=<%=i %>"><%=i %></a></li>	
				
			<%} }%>
			
			
		</ul> --%>
</body>
<div class="page-footer">
</div>
<script type="text/javascript">
	var date = new Date();
	//获取当前年
	var year=date.getFullYear();
	//获取当前月
	var month=date.getMonth()+1;
	//获取当前日
	var day=date.getDate(); 
	//默认结束时间
	if(month < 10){
		$("#enddate").prop("value",year + "-0" +  month + "-" + day);
	}else{
		$("#enddate").prop("value",year + "-" +  month + "-" + day);
	}
	

		//给select一个onchange事件
		//读取admin是3的人
	$("#paper").change(function() {
		$.ajax({
				type: "post",
				url: "showPager.action",
				data: {"pageNum":$("#paper").val()}
			}).done(function(res) {
					$('#table').empty();
					$('#table').append('<tr class="listheader"><th></th><th>编号</th><th>日期</th><th>白昼</th><th>杠上开账户</th><th>交易账户</th><th>策略名</th><th>ID</th><th>合约</th><th>期望收益</th><th>平仓盈亏</th><th>持仓盈亏</th><th>浮动收益</th><th>最新价</th><th>多持仓</th><th>空持仓</th><th>手续费</th></tr>');
					 for (var i = 0; i < res.length; i++) {
						$('#table').append('<tr><td><input type="checkbox" value=" ' + res[i].id + '" name = "delete"/></td><td>'+ res[i].id+'</td><td>'+res[i].time+'</td><td>'+res[i].dn +'</td><td>'+res[i].gskCount +'</td><td>'+res[i].tradeCount +'</td><td>'+res[i].clName+'</td><td>'+res[i].csID +'</td><td>'+res[i].contract +'</td><td>'+res[i].qwsy +'</td><td>'+res[i].pcyk +'</td><td>'+res[i].ccyk +'</td><td>'+res[i].fdsy +'</td><td>'+res[i].zxj +'</td><td>'+res[i].dcc +'</td><td>'+res[i].kcc+'</td><td>'+res[i].sxf +'</td></tr>')	
					} 
					 /* <td><input type="checkbox" value="${d.id}" name = "delete"/></td>
						<td>${d.id}</td>
						<td>${d.time}</td>
						<td>${d.dn }</td>
						<td>${d.gskCount }</td>
						<td>${d.tradeCount }</td>
						<td>${d.clName }</td>
						<td>${d.csID }</td>
						<td>${d.contract }</td>
						<td>${d.qwsy }</td>
						<td>${d.pcyk }</td>
						<td>${d.ccyk }</td>
						<td>${d.fdsy }</td>
						<td>${d.zxj }</td>
						<td>${d.dcc }</td>
						<td>${d.kcc }</td>
						<td>${d.sxf }</td> */
					/* private int id;
					private String emailName;
					private String emailTime;
					private String emailTheme;
					private String emailContent;
					private String emailFile; */
			})
		})
		
		//全选
		$("#all").click(function(){
			$("input[name='delete']").each(function(){
				   $(this).prop("checked",true);
			})
		})
		//反选
		$("#noall").click(function(){
			$("input[name='delete']").each(function(){
				   $(this).prop("checked",!this.checked);  
			})
		})
		//全不选
		$("#no").click(function(){
			$("input[name='delete']").each(function(){
				   $(this).prop("checked",false);
			})
		})
		//删除
		$("#de").click(function(){
			var result="";
	        $("input[name='delete']:checked").each(function(){
	               result+=$(this).val()+',';
	        });
			$.ajax({
				type: "post",
				url: "deleteTrade.action",
				data: {"id":result}
			}).done(function(res) {
				if(res == 0){
					alert("没有选中项");
				}else{
					location.reload();
				}
			})	
		})
	</script>
<script type="text/javascript">
			$(".page-header").load("header.html");
			$(".page-sidebar").load("sidebar.html");
			$(".page-footer").load("footer.html");
</script>
<script src="init.js" type="text/javascript" charset="utf-8"></script>
</html>