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
				</div>
				<button id = "all" style = "width: 70px;">全 选</button>&nbsp;
				<button id = "noall" style = "width : 70px;">反 选</button>&nbsp;
				<button id = "no" style = "width : 70px;">不 选</button>&nbsp;
				<button id = "de" style = "width : 70px;">删 除</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				<!-- 分页 -->
				<select id = "paper" style = "width: 100px;">
				</select>&nbsp;&nbsp;&nbsp;
				<select  id = "se1234" >
				<option value="0">所有信息</option>
				<option value="1">精准查询</option>
				<option value="2">日期查询</option>
				<option value="3">同品种查询</option>
				<option value="4">同策略查询</option>
				<option value="5">统计查询</option>
				</select>
		
				<table class="listtable" id = "table" style = "margin-top: 10px;">
				</table>
				<!--<button id="deletemails" style=" width: 130px; margin-bottom: 10px; height: 30px;font-size:16px ;">功能查询</button>-->
		<!-- <button id="up" style=" width: 130px; margin-bottom: 10px; height: 30px;font-size:16px ;">上一页</button>
		<button id="down" style=" width: 130px; margin-bottom: 10px; height: 30px;font-size:16px ;">下一页</button> -->	 
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
	//读取table信息
		$.ajax({
				type: "post",
				url: "showPager.action"
			}).done(function(res) {
					$('#table').empty();
					$('#table').append('<tr class="listheader"><th></th><th>编号</th><th>日期</th><th>白昼</th><th>杠上开账户</th><th>交易账户</th><th>策略名</th><th>ID</th><th>合约</th><th>期望收益</th><th>平仓盈亏</th><th>持仓盈亏</th><th>浮动收益</th><th>最新价</th><th>多持仓</th><th>空持仓</th><th>手续费</th></tr>');
					 for (var i = 0; i < res.length; i++) {
						$('#table').append('<tr><td><input type="checkbox" value=" ' + res[i].id + '" name = "delete"/></td><td>'+ res[i].id+'</td><td>'+res[i].time+'</td><td>'+res[i].dn +'</td><td>'+res[i].gskCount +'</td><td>'+res[i].tradeCount +'</td><td>'+res[i].clName+'</td><td>'+res[i].csID +'</td><td>'+res[i].contract +'</td><td>'+res[i].qwsy +'</td><td>'+res[i].pcyk +'</td><td>'+res[i].ccyk +'</td><td>'+res[i].fdsy +'</td><td>'+res[i].zxj +'</td><td>'+res[i].dcc +'</td><td>'+res[i].kcc+'</td><td>'+res[i].sxf +'</td></tr>')	
					} 
			})
		
	//读取页码
	$.ajax({
		type: "post",
		url: "showPager_change.action"	
	}).done(function(res) {
			$('#paper').empty();
			for (var i = 1; i <= res; i++) {
			$('#paper').append('<option value = "' + i + '"> ' + i + '/' + res + '</option>');
			} 
	})
	//给se1234一个onchange事件
	$("#se1234").change(function() {
		var tem = $("#se1234").val();
		if(parseInt(tem) == 0){
			location.href = "trade.jsp";
		}else if(parseInt(tem) == 1){
			location.href = "mohuchaxun.html";
		}else if (parseInt(tem) == 2){
			location.href = "riqichaxun.html";
		}else if (parseInt(tem) == 3){
			location.href = "tongpinzhong.html";
		}else if (parseInt(tem) == 4){
			location.href = "tongceluo.html";
		}else if (parseInt(tem) == 5){
			location.href = "tongji.html";
		}
		
		})
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
					//移除选中项
					$("input[name='delete']:checked").each(function() { 
						$(this).parent().parent().remove();
        });
					var num = $("#paper").val();
			//页数刷新  补齐当前页数显示的数量
			 $.ajax({
				type: "post",
				url: "showPager.action",
				data: {"pageNum":num}
			}).done(function(res) {
					$('#table').empty();
					$('#table').append('<tr class="listheader"><th></th><th>编号</th><th>日期</th><th>白昼</th><th>杠上开账户</th><th>交易账户</th><th>策略名</th><th>ID</th><th>合约</th><th>期望收益</th><th>平仓盈亏</th><th>持仓盈亏</th><th>浮动收益</th><th>最新价</th><th>多持仓</th><th>空持仓</th><th>手续费</th></tr>');
					 for (var i = 0; i < res.length; i++) {
						$('#table').append('<tr><td><input type="checkbox" value=" ' + res[i].id + '" name = "delete"/></td><td>'+ res[i].id+'</td><td>'+res[i].time+'</td><td>'+res[i].dn +'</td><td>'+res[i].gskCount +'</td><td>'+res[i].tradeCount +'</td><td>'+res[i].clName+'</td><td>'+res[i].csID +'</td><td>'+res[i].contract +'</td><td>'+res[i].qwsy +'</td><td>'+res[i].pcyk +'</td><td>'+res[i].ccyk +'</td><td>'+res[i].fdsy +'</td><td>'+res[i].zxj +'</td><td>'+res[i].dcc +'</td><td>'+res[i].kcc+'</td><td>'+res[i].sxf +'</td></tr>')	
					} 
			}) 
				//页数还的变
			 $.ajax({
		type: "post",
		url: "showPager_change.action"	
	}).done(function(res) {
			$('#paper').empty();
			for (var i = 1; i <= res; i++) {
			$('#paper').append('<option value = "' + i + '"> ' + i + '/' + res + '</option>');
			} 
			$('option[value="' + num +'"]').prop("selected","selected");
	})
	//把paper的选中项变为num
	
				}
			})	
		})
		/* if(parseInt($('#paper').val()) == 1){
			$("#up").prop("disabled","disabled");
		} */
		//up 上一页
		$("#up").click(function() {
		 /* $.ajax({
				type: "post",
				url: "showPager.action",
				data: {"pageNum":parseInt($("#paper").val()) - 1 +""}
			}).done(function(res) {
					$('#table').empty();
					$('#table').append('<tr class="listheader"><th></th><th>编号</th><th>日期</th><th>白昼</th><th>杠上开账户</th><th>交易账户</th><th>策略名</th><th>ID</th><th>合约</th><th>期望收益</th><th>平仓盈亏</th><th>持仓盈亏</th><th>浮动收益</th><th>最新价</th><th>多持仓</th><th>空持仓</th><th>手续费</th></tr>');
					 for (var i = 0; i < res.length; i++) {
						$('#table').append('<tr><td><input type="checkbox" value=" ' + res[i].id + '" name = "delete"/></td><td>'+ res[i].id+'</td><td>'+res[i].time+'</td><td>'+res[i].dn +'</td><td>'+res[i].gskCount +'</td><td>'+res[i].tradeCount +'</td><td>'+res[i].clName+'</td><td>'+res[i].csID +'</td><td>'+res[i].contract +'</td><td>'+res[i].qwsy +'</td><td>'+res[i].pcyk +'</td><td>'+res[i].ccyk +'</td><td>'+res[i].fdsy +'</td><td>'+res[i].zxj +'</td><td>'+res[i].dcc +'</td><td>'+res[i].kcc+'</td><td>'+res[i].sxf +'</td></tr>')	
					} 
			})  */
			/* $('#paper').prop("value",) */
			
		})
		//down 下一页
		$("#down").click(function() {
		/* $.ajax({
				type: "post",
				url: "showPager.action",
				data: {"pageNum":parseInt($("#paper").val()) + 1 +""}
			}).done(function(res) {
					$('#table').empty();
					$('#table').append('<tr class="listheader"><th></th><th>编号</th><th>日期</th><th>白昼</th><th>杠上开账户</th><th>交易账户</th><th>策略名</th><th>ID</th><th>合约</th><th>期望收益</th><th>平仓盈亏</th><th>持仓盈亏</th><th>浮动收益</th><th>最新价</th><th>多持仓</th><th>空持仓</th><th>手续费</th></tr>');
					 for (var i = 0; i < res.length; i++) {
						$('#table').append('<tr><td><input type="checkbox" value=" ' + res[i].id + '" name = "delete"/></td><td>'+ res[i].id+'</td><td>'+res[i].time+'</td><td>'+res[i].dn +'</td><td>'+res[i].gskCount +'</td><td>'+res[i].tradeCount +'</td><td>'+res[i].clName+'</td><td>'+res[i].csID +'</td><td>'+res[i].contract +'</td><td>'+res[i].qwsy +'</td><td>'+res[i].pcyk +'</td><td>'+res[i].ccyk +'</td><td>'+res[i].fdsy +'</td><td>'+res[i].zxj +'</td><td>'+res[i].dcc +'</td><td>'+res[i].kcc+'</td><td>'+res[i].sxf +'</td></tr>')	
					} 
			}) */
		})
	</script>
<script type="text/javascript">
			$(".page-header").load("header.html");
			$(".page-sidebar").load("sidebar.html");
			$(".page-footer").load("footer.html");
</script>
<script src="init.js" type="text/javascript" charset="utf-8"></script>
</script>
	<!--导出到各种文件-->
	<script src="js_out/Blob.js"></script>

	<script src="js_out/FileSaver.js"></script>

	<script src="js_out/tableExport.js"></script>

	<script>
		var exportLink = document.getElementById('export');
		exportLink.addEventListener('click', function(e) {
			e.preventDefault();
			if (e.target.nodeName === "A") {
				tableExport('tables', '测试测试', e.target.getAttribute('data-type'));
			}
		}, false);
	</script>
</html>