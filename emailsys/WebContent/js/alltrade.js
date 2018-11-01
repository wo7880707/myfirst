/**
 * Created by huipu on 2016/2/1.
 */
function initProgrom(){
	//refreshData(20,1,10,json.length);
    var pageSize = 5;
    var pageNo = 1;
    builderUQTQueryMsg(getJsonArrayByPageSize(pageSize,pageNo));

    var totalPage = getTotllePage(pageSize);
    var totalRecords = json.length;
    //生成分页控件 根据分页的形式在这里设置
    kkpager.init({
        pno: pageNo,
        //总页码
        total: totalPage,
        //总数据条数
        totalRecords: totalRecords,
        //页面条数
        pageSize: pageSize
    });
    kkpager.generPageHtml();
}

/**
 * 获取总页数
 * @returns {number}
 */
var getTotllePage = function (pageSize) {
    var jsonCount = json.length;
    var shang = jsonCount/pageSize;
    var yushu = jsonCount%pageSize;
    if(yushu >0){
        shang ++;
    }
    return shang;
}
/**
 * 获取分页后的数据
 * @param pageSize
 * @param pageNo
 * @returns {*}
 */
var getJsonArrayByPageSize = function (pageSize,pageNo){
    var jsonCount = json.length;
    var shang = getTotllePage(pageSize);
    var startIndex = (pageNo-1)*pageSize;
    var endIndex = (shang == pageNo)? jsonCount:pageNo*pageSize;
    return json.slice(startIndex,endIndex);
}

/**
 * 刷新页面数据
 * @param pageSize   每页显示条数
 * @param pageNum    第几页
 */
function refreshData(pageSize, pageNo) {
    builderUQTQueryMsg(getJsonArrayByPageSize(pageSize,pageNo));

    var totalPage = getTotllePage(pageSize);
    var totalRecords = json.length;
    //生成分页控件 根据分页的形式在这里设置
    kkpager.init({
        pno: pageNo,
        //总页码
        total: totalPage,
        //总数据条数
        totalRecords: totalRecords,
        //页面条数
        pageSize: pageSize
    });
    kkpager.generPageHtml();

}


/**
 * 构建表格数据
 */
var builderUQTQueryMsg = function (UQTQueryMsg){
    var UQT_detailTable = $('#UQT_detailTable');
    UQT_detailTable.empty();
    
    //修改表头信息
    //--------------------------------------------------
    var th = '<caption>交易信息:</caption><tr class="listheader"><th>编号</th><th>日期</th><th>白昼</th><th>杠上开账户</th><th>交易账户</th><th>策略名</th><th>ID</th><th>合约</th><th>期望收益</th><th>平仓盈亏</th><th>持仓盈亏</th><th>浮动收益</th><th>最新价</th><th>多持仓</th><th>空持仓</th><th>手续费</th></tr>';

    UQT_detailTable.append(th);
    //var tr  ;
    $.each(UQTQueryMsg, function(i,eachData){
       // tr = $('tr');
        var changeStr = ["停用","启用","删除"];
        //建立列对应变量
        //--------------------------------------------------------------------
        var id = eachData.id;
        var time = eachData.time;
        var dn = eachData.dn;
        var gskCount = eachData.gskCount;
        var tradeCount = eachData.tradeCount;
        var clName = eachData.clName;
        var csID = eachData.csID;
        var contract = eachData.contract;
        var qwsy = eachData.qwsy;
        var pcyk = eachData.pcyk;
        var ccyk = eachData.ccyk;
        var fdsy = eachData.fdsy;
        var zxj = eachData.zxj;
        var dcc = eachData.dcc;
        var kcc = eachData.kcc;

        if(eachData.sxf == null){
        	var sxf = "";
        }else{
        	var sxf = eachData.sxf;
        }
        


        //修改每行数据
        //--------------------------------------------------
       /* var strUrl1 = "EmpApproveServlet?employeeid="+employeeId+"&newstatus=1";
 		var strUrl2 = "EmpApproveServlet?employeeid="+employeeId+"&newstatus=2";*/
 		
 		
        UQT_detailTable.append('<tr><td>'+id+'</td>'
							+'<td>'+time+'</td>'
						    +'<td>'+dn+'</td>'
						    +'<td>'+gskCount+'</td>'
						    +'<td>'+tradeCount+'</td>'
						    +'<td>'+clName+'</td>'
						    +'<td>'+csID+'</td>'
						    +'<td>'+contract+'</td>'
						    +'<td>'+qwsy+'</td>'
						    +'<td>'+pcyk+'</td>'
						    +'<td>'+ccyk+'</td>'
						    +'<td>'+fdsy+'</td>'
						    +'<td>'+zxj+'</td>'
						    +'<td>'+dcc+'</td>'
						    +'<td>'+kcc+'</td>'
						    +'<td>'+sxf+'</td></tr>');
  
       // UQT_detailTable.append(tr);
    });
}


/**
*选择左侧checkbox
*
*/
var optionCheckBoxes = function(data){
    var checkType = $(data).is(':checked');
    var trs = $(data).parent().parent().parent().nextAll();
    
    if(checkType){
        //全部选择
        trs.each(function (trNode) {
            var currentCheck = $(this).find('>td span input[type="checkbox"]');
            //alert(currentCheck.is(':checked'));
            currentCheck.attr("checked", true);
        });
    }else{
        //全部取消选择
        trs.each(function (trNode) {
            var currentCheck = $(this).find('>td span input[type="checkbox"]');
            //alert(currentCheck.is(':checked'));
            currentCheck.attr("checked", false);
        });
    }
}