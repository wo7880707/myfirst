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
    var th = '<caption>邮件信息:</caption><tr class="listheader"><th>编号</th><th>时间</th><th>主题</th><th>正文内容</th><th>附件</th></tr>';

    UQT_detailTable.append(th);
    //var tr  ;
    $.each(UQTQueryMsg, function(i,eachData){
       // tr = $('tr');
        var changeStr = ["停用","启用","删除"];
        //建立列对应变量
        //--------------------------------------------------------------------
        var id = eachData.id;
        //var emailName = eachData.emailName;
        var emailTime = eachData.emailTime;
        var emailTheme = eachData.emailTheme;
        var emailContent = eachData.emailContent;
        var emailFile = eachData.emailFile;
        /* private int id;
		private String emailName;
		private String emailTime;
		private String emailTheme;
		private String emailContent;
		private String emailFile; */
        //修改每行数据
        //--------------------------------------------------
       /* var strUrl1 = "EmpApproveServlet?employeeid="+employeeId+"&newstatus=1";
 		var strUrl2 = "EmpApproveServlet?employeeid="+employeeId+"&newstatus=2";*/
        if(emailFile ==""){
        	var strUr = "";
        }else{
        	 var strUr = '<a href = "email/download.action?id='+id+'">'+ '下载' + '</a>';
        }
        UQT_detailTable.append('<tr><td>'+id+'</td>'
						    +'<td>'+emailTime+'</td>'
						    +'<td>'+emailTheme+'</td>'
						    +'<td>'+emailContent+'</td>'
						    +'<td>' + strUr + '</td></tr>');
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