<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.ry.szq.pojo.Trade" %>

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
<!--导出到各种文件-->
<script src="../js_out/Blob.js"></script>

<script src="../js_out/FileSaver.js"></script>

<script src="../js_out/tableExport.js"></script>
<style>
#aa {color:black;
     font-size:
    text-decoration:none;}
#aa:hover{
    color:black;
    font-weight:normal; 
    text-decoration:none;
}
</style>
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
<c:forEach items="${map}" var="m">

	<table class="listtable" id ="${m.key}">	
			<caption>${m.key}</caption>
			<tr class="listheader" >
			<th>编号</th><th>日期</th><th>白昼</th><th>杠上开账户</th><th>交易账户</th><th>策略名</th><th>ID</th><th>合约</th><th>期望收益</th><th>平仓盈亏</th><th>持仓盈亏</th><th>浮动收益</th><th>最新价</th><th>多持仓</th><th>空持仓</th><th>手续费</th>
			</tr>
			<c:forEach items="${m.value}" var="d">
				<tr>
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
<div>
	<button id="${m.key}bn" style=" margin-left : 630px;width: 90px;height: 30px; font-size: 15px;">打开图表</button>
	<div id="${m.key}export" style="margin-bottom: 10px; margin-top: 10px; display : inline;">
		<span ><button style=" margin-left : 15px;width: 90px;height: 30px; font-size: 15px; "><a data-type="xls" href="javascript:;" style="text-decoration:none; color: black;" id = "aa"> &nbsp;导出excel&nbsp;</a></button></span> 	
		<!-- <button><a data-type="xls" href="javascript:;" style="text-decoration:none; color: black;" id = "aa"> &nbsp;导出excel&nbsp;</a></button>	 -->	
	</div>


</div>

<script>
		var exportLink = document.getElementById('${m.key}export');
		exportLink.addEventListener('click', function(e) {
			e.preventDefault();
			if (e.target.nodeName === "A") {
				tableExport('${m.key}', '浮动收益统计表', e.target.getAttribute('data-type'));
			}
		}, false);
	</script>
<br>
<!-- 图表的位置 -->
 <div id="${m.key}tu" style="min-width:800px;height:400px;"></div>
 
 </c:forEach>
 
 <div id="all1" style = "margin-top:20px;  display: none; "><button id="all2" style="width: 150px;height: 30px; font-size: 15px;">全部图表</button></div>
<br>
<!-- 全部线的表格 -->
 <div id="all3" style="min-width:800px;height:400px;"></div>    
 <!-- 小窗口 --> 
 <a href=# onClick="javascript:window.open('tongji.html','','width=632,height=388,left=500px,toolbar=no,location=no, directories=no,status=no, menubar=no, resizable=no, scrollbars=no');return false;">百度</a>
 
 <span id = "result" style = "margin-left:260px; display: none;font-size : 18px;  ">没 有 查 询 到 相 关 数 据</span>
<div id = "add" style = "margin-top:20px; "><button onclick = "window.history.back();" style="margin-left: 260px; width: 200px; margin-bottom: 10px; height: 40px;font-size:16px ;"> 返 回 </button></div>
</div>
</div>

<div class="page-footer">
</div>
<script type="text/javascript">
			$(".page-header").load("header.html");
			$(".page-sidebar").load("sidebar.html");
			$(".page-footer").load("footer.html");
</script>
<script src="init.js" type="text/javascript" charset="utf-8"></script>
<script>
 <c:forEach items="${table}" var="t"> 
 
 	<c:forEach items="${t.value}" var="b">
 	
	var time = ${b.key}
	
	var value = ${b.value} 
	
	
$('#${t.key}tu').highcharts({                   //图表展示容器，与div的id保持一致  
    chart: {  
        type: 'line'                           
    },  
    title: {  
        text: '浮动收益表'      //指定图表标题  
    },  
    xAxis: {  
        categories:time  
    },  
    yAxis: {  
        title: {  
            text: 'value'  
        },  
        labels: {  
            formatter: function () {  
                return this.value;  
            }  
        },  
         lineWidth: 2  
    },  
    tooltip: {
        crosshairs: true,
        shared: true
    },
    plotOptions: {
       	line: {
    		dataLabels:{
    			enabled:true      //开启数据标签
    		},
    	}  
       
    },
    exporting : {  
        enabled : true,  
        buttons : {  
               exportButton : {  
                      menuItems: [{  
                             text: '导出PNG图片(宽度为250px)',  
                             onclick:function() {  
                                    this.exportChart({  
                                           width:200 //导出报表的宽度  
                                    });  
                             }  
                      }, {  
                             text: '导出PNG图片(宽度为800px)',  
                             onclick:function() {  
                                    this.exportChart();// 800px by default  
                             }  
                      },  
                      null,  
                      null  
                      ]  
               },  
               printButton: {  
                      enabled : false  
               }  
        },
        filename : '平仓盈亏图表'
    },
    credits: {  
        enabled: false  
      }, 
    series:[{  
        name:'浮动收益', 
        color:"#90ed7d",  
        data:value  
        }]  
})
</c:forEach> 
 </c:forEach> 

 
</script>
  <script>
<c:forEach items="${map}" var="m">
	$("#${m.key}tu").hide()
	$("#${m.key}bn").click(function() {
		if($("#${m.key}bn").text() == "打开图表"){
			$("#${m.key}tu").show();
			$("#${m.key}bn").text("关闭图表");
		}else{
			$("#${m.key}tu").hide();
			$("#${m.key}bn").text("打开图表");
		}
	})
</c:forEach>
	
</script> 
<script>  
$(function () {   
    var time=${alltime};  
    
if(time == "0"){
	$('#all3').hide();
	$('#result').show();
    }else{
    $('#all1').show();
 //接收后台传来的数据 
    $('#all3').highcharts({                   //图表展示容器，与div的id保持一致  
        chart: {  
            type: 'line'                           
        },  
        title: {  
            text: '浮动收益表'      //指定图表标题  
        },  
        xAxis: {  
            categories:time  
        },  
        yAxis: {  
            title: {  
                text: 'value'  
            },  
            labels: {  
                formatter: function () {  
                    return this.value;  
                }  
            },  
             lineWidth: 2  
        },  
        tooltip: {
            crosshairs: true,
            shared: true
        },
        plotOptions: {
           	line: {
        		dataLabels:{
        			enabled:true      //开启数据标签
        		},
        	}  
        },
        exporting : {  
            enabled : true,  
            buttons : {  
                   exportButton : {  
                          menuItems: [{  
                                 text: '导出PNG图片(宽度为250px)',  
                                 onclick:function() {  
                                        this.exportChart({  
                                               width:200 //导出报表的宽度  
                                        });  
                                 }  
                          }, {  
                                 text: '导出PNG图片(宽度为800px)',  
                                 onclick:function() {  
                                        this.exportChart();// 800px by default  
                                 }  
                          },  
                          null,  
                          null  
                          ]  
                   },  
                   printButton: {  
                          enabled : false  
                   }  
            },
            filename : '累计收益表'
        },
        credits: {  
            enabled: false  
          }, 
        series:[
         <c:forEach items="${value}" var="b">
                {
                 name:'${b.key}',  
				 data:${b.value}
                },
         </c:forEach> 
            ]  
    });  
    }
});  
$('#all3').hide();
$("#all2").click(function() {
	if($('#all2').text() == "全部图表"){
		$('#all3').show();
		$('#all2').text("关闭图表");
	}else{
		$('#all3').hide();
		$('#all2').text("全部图表");
	}
})
</script>   


	
</body>
</html>