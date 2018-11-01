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

<!-- 表格1 -->
	<table class="listtable" id = "ta1">	
			<caption>策略01</caption>
			<tr class="listheader" id = " tr1">
			<th>编号</th><th>日期</th><th>白昼</th><th>杠上开账户</th><th>交易账户</th><th>策略名</th><th>ID</th><th>合约</th><th>期望收益</th><th>平仓盈亏</th><th>持仓盈亏</th><th>浮动收益</th><th>最新价</th><th>多持仓</th><th>空持仓</th><th>手续费</th>
			</tr>
			<c:forEach items="${GL01table}" var="d">
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
				

<div id="container11" style = " display: none; "><button id="b1" style="width: 90px;height: 30px; font-size: 15px;">打开图表</button></div>
<br>
 <div id="container1" style="min-width:800px;height:400px;"></div>     
 
<!-- 表格2 -->
<table class="listtable" id = "ta2">
<caption>策略02</caption>	
			<tr class="listheader" id = " tr2">
			<th>编号</th><th>日期</th><th>白昼</th><th>杠上开账户</th><th>交易账户</th><th>策略名</th><th>ID</th><th>合约</th><th>期望收益</th><th>平仓盈亏</th><th>持仓盈亏</th><th>浮动收益</th><th>最新价</th><th>多持仓</th><th>空持仓</th><th>手续费</th>
			</tr>
			<c:forEach items="${GH02table}" var="d">
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
	
				<div id="container12" style = " display: none; "><button id="b2" style="width: 90px;height: 30px; font-size: 15px;">打开图表</button></div>
				<br>
 <div id="container2" style="min-width:800px;height:400px; "></div>  <span style="font-family: Arial, Helvetica, sans-serif;"></span>  

<!-- 表格3 -->
<table class="listtable" id = "ta3">	
<caption>策略03</caption>
			<tr class="listheader" id = " tr3">
			<th>编号</th><th>日期</th><th>白昼</th><th>杠上开账户</th><th>交易账户</th><th>策略名</th><th>ID</th><th>合约</th><th>期望收益</th><th>平仓盈亏</th><th>持仓盈亏</th><th>浮动收益</th><th>最新价</th><th>多持仓</th><th>空持仓</th><th>手续费</th>
			</tr>
			<c:forEach items="${GL03table}" var="d">
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
			
				<div id="container13" style = " display: none; "><button id="b3" style="width: 90px;height: 30px; font-size: 15px;">打开图表</button></div>
				<br>
 <div id="container3" style="min-width:800px;height:400px; "></div>     

<!-- 表格4 -->
<table class="listtable" id = "ta4">	
<caption>策略04</caption>
			<tr class="listheader" id = " tr4">
			<th>编号</th><th>日期</th><th>白昼</th><th>杠上开账户</th><th>交易账户</th><th>策略名</th><th>ID</th><th>合约</th><th>期望收益</th><th>平仓盈亏</th><th>持仓盈亏</th><th>浮动收益</th><th>最新价</th><th>多持仓</th><th>空持仓</th><th>手续费</th>
			</tr>
			<c:forEach items="${GL04table}" var="d">
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
				
				<div id="container14" style = " display: none; "><button id="b4" style="width: 90px;height: 30px; font-size: 15px;">打开图表</button></div>
				<br>
 <div id="container4" style="min-width:800px;height:400px; "></div>     

<!-- 表格5 -->
<table class="listtable" id = "ta5">	
				<caption>策略05</caption>
			<tr class="listheader" id = " tr5">
			<th>编号</th><th>日期</th><th>白昼</th><th>杠上开账户</th><th>交易账户</th><th>策略名</th><th>ID</th><th>合约</th><th>期望收益</th><th>平仓盈亏</th><th>持仓盈亏</th><th>浮动收益</th><th>最新价</th><th>多持仓</th><th>空持仓</th><th>手续费</th>
			</tr>
			<c:forEach items="${GL05table}" var="d">
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

				<div id="container15" style = " display: none; "><button id="b5" style="width: 90px;height: 30px; font-size: 15px;">打开图表</button></div>
				<br>
 <div id="container5" style="min-width:800px;height:400px; "></div>     

<!-- 表格6 -->
<table class="listtable" id = "ta6">	
<caption>策略06</caption>
			<tr class="listheader" id = " tr6">
			<th>编号</th><th>日期</th><th>白昼</th><th>杠上开账户</th><th>交易账户</th><th>策略名</th><th>ID</th><th>合约</th><th>期望收益</th><th>平仓盈亏</th><th>持仓盈亏</th><th>浮动收益</th><th>最新价</th><th>多持仓</th><th>空持仓</th><th>手续费</th>
			</tr>
			<c:forEach items="${GL06table}" var="d">
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
			
				<div id = "container16" style = " display: none; "><button id="b6" style="width: 90px;height: 30px; font-size: 15px;">打开图表</button></div>
				<br>
 <div id="container6" style="min-width:800px;height:400px; "></div>     

<!-- 表格7 -->
<table class="listtable" id = "ta7">	
<caption>策略07</caption>
			<tr class="listheader" id = " tr7">
			<th>编号</th><th>日期</th><th>白昼</th><th>杠上开账户</th><th>交易账户</th><th>策略名</th><th>ID</th><th>合约</th><th>期望收益</th><th>平仓盈亏</th><th>持仓盈亏</th><th>浮动收益</th><th>最新价</th><th>多持仓</th><th>空持仓</th><th>手续费</th>
			</tr>
			<c:forEach items="${GL07table}" var="d">
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

				<div id="container17" style = " display: none; " ><button id="b7" style="width: 90px;height: 30px; font-size: 15px;">打开图表</button></div>
				<br>
 <div id="container7" style="min-width:800px;height:400px; "></div>     

<!-- 表格8 -->
<table class="listtable" id = "ta8">	
<caption>策略08</caption>
			<tr class="listheader" id = " tr8">
			<th>编号</th><th>日期</th><th>白昼</th><th>杠上开账户</th><th>交易账户</th><th>策略名</th><th>ID</th><th>合约</th><th>期望收益</th><th>平仓盈亏</th><th>持仓盈亏</th><th>浮动收益</th><th>最新价</th><th>多持仓</th><th>空持仓</th><th>手续费</th>
			</tr>
			<c:forEach items="${GL08table}" var="d">
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

				<div id="container18" style = " display: none; "><button id="b8" style="width: 90px;height: 30px; font-size: 15px;">打开图表</button></div>
			<br>
 <div id="container8" style="min-width:800px;height:400px; "></div>  
 

<div><button onclick = "window.history.back();" style="margin-left: 260px; width: 200px; margin-bottom: 10px; height: 40px;font-size:16px ;"> 返 回 </button></div>
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
$(function () {   
    var time=${GL01time} ;  
     
    if(time ==0){
    	$('#ta1').hide();
    }else{
    	var value=${GL01fdsy} ; 
    	$('#container11').show();
    	//接收后台传来的数据 
        $('#container1').highcharts({                   //图表展示容器，与div的id保持一致  
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
            		//enableMouseTracking:false //关闭鼠标跟踪，对应的提示框，点击事件会失效
            	}  
                /* spline: {
                    marker: {
                        radius: 4,
                        lineColor: '#666666',
                        lineWidth: 1
                    }
                }  */
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
                filename : '停车场停车曲线图'
            },
            credits: {  
                enabled: false  
              }, 
            series:[{  
                name:'浮动收益', 
                color:"#90ed7d",  
                data:value  
                }]  
        });  
    }
 
});  
</script>  
<script>  
$(function () {   
    var time=${GH02time};  
      
if(time ==0){
	$('#ta2').hide();

    }else{
    	var value=${GH02fdsy};
    	$('#container12').show();
 //接收后台传来的数据 
    $('#container2').highcharts({                   //图表展示容器，与div的id保持一致  
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
        		//enableMouseTracking:false //关闭鼠标跟踪，对应的提示框，点击事件会失效
        	}  
            /* spline: {
                marker: {
                    radius: 4,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            }  */
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
            filename : '停车场停车曲线图'
        },
        credits: {  
            enabled: false  
          }, 
        series:[{  
            name:'浮动收益',  
            color:"#90ed7d",  
            data:value  
            }]  
    });  
    }
});  
</script>  
<script>  
$(function () {   
    var time=${GL03time};  
    
if(time == "0"){
	$('#ta3').hide();

    }else{
    	var value=${GL03fdsy};  
    	$('#container13').show();
 //接收后台传来的数据 
    $('#container3').highcharts({                   //图表展示容器，与div的id保持一致  
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
        		//enableMouseTracking:false //关闭鼠标跟踪，对应的提示框，点击事件会失效
        	}  
            /* spline: {
                marker: {
                    radius: 4,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            }  */
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
            filename : '停车场停车曲线图'
        },
        credits: {  
            enabled: false  
          }, 
        series:[{  
            name:'浮动收益',  
            color:"#90ed7d",  
            data:value  
            }]  
    });  
    }
});  
</script>  
<script>  
$(function () {   
    var time=${GL04time};  
   
if(time ==0){
	$('#ta4').hide();
    }else{
    	 var value=${GL04fdsy};  
    	$('#container14').show();
 //接收后台传来的数据 
    $('#container4').highcharts({                   //图表展示容器，与div的id保持一致  
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
        		//enableMouseTracking:false //关闭鼠标跟踪，对应的提示框，点击事件会失效
        	}  
            /* spline: {
                marker: {
                    radius: 4,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            }  */
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
            filename : '停车场停车曲线图'
        },
        credits: {  
            enabled: false  
          }, 
        series:[{  
            name:'浮动收益', 
            color:"#90ed7d",  
            data:value  
            }]  
    });  
    }
});  
</script>  
<script>  
$(function () {   
    var time=${GL05time};  
      
if(time ==0){
	$('#ta5').hide();
    }else{
    	var value=${GL05fdsy}; 
    	$('#container15').show();
 //接收后台传来的数据 
    $('#container5').highcharts({                   //图表展示容器，与div的id保持一致  
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
        		//enableMouseTracking:false //关闭鼠标跟踪，对应的提示框，点击事件会失效
        	}  
            /* spline: {
                marker: {
                    radius: 4,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            }  */
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
            filename : '停车场停车曲线图'
        },
        credits: {  
            enabled: false  
          }, 
        series:[{  
            name:'浮动收益', 
            color:"#90ed7d",  
            data:value  
            }]  
    });  
    }
});  
</script>  
<script>  
$(function () {   
    var time=${GL06time} ;  
    
if(time ==0){
	$('#ta6').hide();
    }else{
    	var value=${GL06fdsy};  
    	$('#container16').show();
//接收后台传来的数据 
    $('#container6').highcharts({                   //图表展示容器，与div的id保持一致  
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
        		//enableMouseTracking:false //关闭鼠标跟踪，对应的提示框，点击事件会失效
        	}  
            /* spline: {
                marker: {
                    radius: 4,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            }  */
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
            filename : '停车场停车曲线图'
        },
        credits: {  
            enabled: false  
          }, 
        series:[{  
            name:'浮动收益',  
            color:"#90ed7d",  
            data:value  
            }]  
    });  
    }
});  
</script>  
<script>  
$(function () {   
    var time=${GL07time};  
     
if(time ==0){
	$('#ta7').hide();
    }else{
    	 var value=${GL07fdsy};
    	$('#container17').show();
 //接收后台传来的数据 
    $('#container7').highcharts({                   //图表展示容器，与div的id保持一致  
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
        		//enableMouseTracking:false //关闭鼠标跟踪，对应的提示框，点击事件会失效
        	}  
            /* spline: {
                marker: {
                    radius: 4,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            }  */
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
            filename : '停车场停车曲线图'
        },
        credits: {  
            enabled: false  
          }, 
        series:[{  
            name:'浮动收益',  
            color:"#90ed7d",  
            data:value  
            }]  
    });  
    }
});  
</script>  
<script>  
$(function () {   
    var time=${GL08time};  
     
if(time ==0){
	$('#ta8').hide();
    }else{
    	var value=${GL08fdsy}; 
    	$('#container18').show();
//接收后台传来的数据 
    $('#container8').highcharts({                   //图表展示容器，与div的id保持一致  
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
        		//enableMouseTracking:false //关闭鼠标跟踪，对应的提示框，点击事件会失效
        	}  
            /* spline: {
                marker: {
                    radius: 4,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            }  */
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
            filename : '停车场停车曲线图'
        },
        credits: {  
            enabled: false  
          }, 
        series:[{  
            name:'浮动收益',  
            color:"#90ed7d",  
            data:value  
            }
       ]  
    });  
    }
});  
</script>  

<!--  <script>  
$(function () {   
     i = ${list01};  
    json = JSON.parse(i);

	$(function() {
			initProgrom();
		})     
}) -->
</script>  
<script>
$('#container1').hide(); 
$('#container2').hide();
$('#container3').hide();
$('#container4').hide();
$('#container5').hide();
$('#container6').hide();
$('#container7').hide();
$('#container8').hide();


	$("#b1").click(function() {
		if($('#b1').text() == "打开图表"){
			$('#container1').show();
			$('#b1').text("关闭图表");
		}else{
			$('#container1').hide();
			$('#b1').text("打开图表");
		}
	})
	$("#b2").click(function() {
		if($('#b2').text() == "打开图表"){
			$('#container2').show();
			$('#b2').text("关闭图表");
		}else{
			$('#container2').hide();
			$('#b2').text("打开图表");
		}
	})
	$("#b3").click(function() {
		if($('#b3').text() == "打开图表"){
			$('#container3').show();
			$('#b3').text("关闭图表");
		}else{
			$('#container3').hide();
			$('#b3').text("打开图表");
		}
	})
	$("#b4").click(function() {
		if($('#b4').text() == "打开图表"){
			$('#container4').show();
			$('#b4').text("关闭图表");
		}else{
			$('#container4').hide();
			$('#b4').text("打开图表");
		}
	})
	$("#b5").click(function() {
		if($('#b5').text() == "打开图表"){
			$('#container5').show();
			$('#b5').text("关闭图表");
		}else{
			$('#container5').hide();
			$('#b5').text("打开图表");
		}
	})
	$("#b6").click(function() {
		if($('#b6').text() == "打开图表"){
			$('#container6').show();
			$('#b6').text("关闭图表");
		}else{
			$('#container6').hide();
			$('#b6').text("打开图表");
		}
	})
	$("#b7").click(function() {
		if($('#b7').text() == "打开图表"){
			$('#container7').show();
			$('#b7').text("关闭图表");
		}else{
			$('#container7').hide();
			$('#b7').text("打开图表");
		}
	})
	$("#b8").click(function() {
		if($('#b8').text() == "打开图表"){
			$('#container8').show();
			$('#b8').text("关闭图表");
		}else{
			$('#container8').hide();
			$('#b8').text("打开图表");
		}
	})
</script>
</body>
</html>