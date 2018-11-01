package com.ry.szq.controller;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.synth.SynthSpinnerUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.szq.common.Pager;
import com.ry.szq.pojo.Admin;
import com.ry.szq.pojo.Email;
import com.ry.szq.pojo.Query_info;
import com.ry.szq.pojo.Trade;
import com.ry.szq.service.EmailService;
import com.ry.szq.service.Query_infoService;
import com.ry.szq.service.TradeService;

@Controller
@RequestMapping("/trade")
public class TradeAction {
		@Value("${url}")
		private String bb;
		@Autowired
		private TradeService tradeService;

		public TradeService getTradeService() {
			return tradeService;
		}
		public void setTradeService(TradeService tradeService) {
			this.tradeService = tradeService;
		}
		@Autowired
		private EmailService emailService;
		
		public EmailService getEmailService() {
			return emailService;
		}
		public void setEmailService(EmailService emailService) {
			this.emailService = emailService;
		}
		@Autowired
		private Query_infoService query_infoService ;

		public Query_infoService getQuery_infoService() {
			return query_infoService;
		}

		public void setQuery_infoService(Query_infoService query_infoService) {
			this.query_infoService = query_infoService;
		}
		@RequestMapping("/aaa.action")
		public @ResponseBody String aaa() {
			System.out.println("bb============" + this.bb);
			return bb;
		}
		// 查找全部
		@RequestMapping("/showAll.action")
		public @ResponseBody ArrayList<Trade> selectAll() {
			return tradeService.selectAll();
		}	
		//分页显示
		@RequestMapping("/dividePager.action")	
		public String dividePager(Model mo ,@RequestParam(value="pageNum",defaultValue="1") int pageNum){
			Map<String,Object> map = tradeService.dividePager(pageNum);
			mo.addAttribute("pager",map.get("pager"));
			mo.addAttribute("trade",map.get("trade"));
			return "trade";
		}
		//分页显示
		@RequestMapping("/showPager.action")	
		public @ResponseBody ArrayList<Trade> showPager(@RequestParam(value="pageNum",defaultValue="1") int pageNum){
			Map<String,Object> map = tradeService.dividePager(pageNum);
			ArrayList<Trade> list = (ArrayList<Trade>)map.get("trade");
			return list;
			}
		//显示页面页码数
		@RequestMapping("/showPager_change.action")	
		public @ResponseBody int showPager_change(@RequestParam(value="pageNum",defaultValue="1") int pageNum){
			Map<String,Object> map = tradeService.dividePager(pageNum);
			Pager paper = (Pager)map.get("pager");
			int i = paper.getPageCount();
			return  i;
			}
		//分页显示 bytime
		@RequestMapping("/dividePagerByTime.action")	
			public String showPagerByTime(String startTime,String endTime,Model mo ,@RequestParam(value="pageNum",defaultValue="1")int pageNum){
				String starttime = startTime.replace("-", "");
				String endtime = endTime.replace("-", "");
				Map<String,Object> map = tradeService.selectAllByTime(starttime, endtime, pageNum);
				mo.addAttribute("pager",map.get("pager"));
				mo.addAttribute("trade",map.get("trade"));
				return "trade_date";
			}
		//分页显示bytime 显示table
		@RequestMapping("/showPagerByTime.action")	
		public @ResponseBody ArrayList<Trade> showPagerByTime(String startTime,String endTime,@RequestParam(value="pageNum",defaultValue="1")int pageNum){
			String starttime = startTime.replace("-", "");
			String endtime = endTime.replace("-", "");
			Map<String,Object> map = tradeService.selectAllByTime(starttime, endtime, pageNum);
			ArrayList<Trade> list = (ArrayList<Trade>)map.get("trade");
			return list;
			}
		//分页显示bytime 显示页码
				@RequestMapping("/showPagerByTime2.action")	
				public @ResponseBody int showPagerByTime2(String startTime,String endTime,@RequestParam(value="pageNum",defaultValue="1")int pageNum){
					String starttime = startTime.replace("-", "");
					String endtime = endTime.replace("-", "");
					Map<String,Object> map = tradeService.selectAllByTime(starttime, endtime, pageNum);
					Pager paper = (Pager)map.get("pager");
					int i = paper.getPageCount();
					return i;
					}
		//删除trade和Email
		@RequestMapping("/deleteTrade.action")
		public @ResponseBody int delete(String id){
			int i = 0;
			if(id.equals("")){
				
			}else{
				String [] str = id.split(",");
				for (int j = 0; j < str.length; j++) {
					int deleteID = Integer.parseInt(str[j].trim());
					tradeService.delete(deleteID);
				}
				i = 1;
			}
			return i;
		}
		//查询所有策略的名字
		@RequestMapping("/showAllclName.action")
		public @ResponseBody ArrayList<String> selectAllclName(){
			return tradeService.selectAllclName();
		}
		//查询一段时间所有账号的名字
		@RequestMapping("/showTradeCountByTime.action")
		public @ResponseBody ArrayList<String> showTradeCountByTime(String startTime,String endTime){
			String starttime = startTime.replace("-", "");
			String endtime = endTime.replace("-", "");
			return tradeService.showTradeCountByTime(starttime,endtime);
		}
		//查询一段时间所有策略的名字
		@RequestMapping("/showClNameByTime.action")
		public @ResponseBody ArrayList<String> showClNameByTime(String startTime,String endTime){
			String starttime = startTime.replace("-", "");
			String endtime = endTime.replace("-", "");
			return tradeService.showClNameByTime(starttime,endtime);
		}
		//查询一段时间  一个  策略的 所有品种名字
		@RequestMapping("/showContractByTime.action")
		public	@ResponseBody ArrayList<String> showContractByTime(String startTime,String endTime,String clName){
			String starttime = startTime.replace("-", "");
			String endtime = endTime.replace("-", "");
			return tradeService.showContractByTime(starttime,endtime,clName);
		}
		//查询一段时间所有合约的名字
		@RequestMapping("/showContractByTime_2.action")
		public @ResponseBody ArrayList<String> showContractByTime_2(String startTime,String endTime){
			String starttime = startTime.replace("-", "");
			String endtime = endTime.replace("-", "");
			return tradeService.showContractByTime_2(starttime,endtime);
		}
		//查询一段时间  一个  合约的 所有策略名字
		@RequestMapping("/showClNameByTime_2.action")
		public	@ResponseBody ArrayList<String> showClNameByTime_2(String startTime,String endTime,String contract){
			String starttime = startTime.replace("-", "");
			String endtime = endTime.replace("-", "");
			return tradeService.showClNameByTime_2(starttime,endtime,contract);
		}		
		//增加交易
		@RequestMapping("/addtrade.action")
		public @ResponseBody int insert(String time ,String dn,String gskCount,String tradeCount,String clName,String csID,String contract,String qwsy,String pcyk,String ccyk,String fdsy,String zxj,String dcc,String kcc,String sxf){
			Email email = new Email("补录信息",time,"Fw:" + time + "_" + dn + "_" + gskCount + "_" + tradeCount ,"补录信息,请查原邮件","","");
			emailService.insert(email);
			int eamilid = email.getId();
			Trade trade = new Trade(eamilid,time ,dn,gskCount, tradeCount, clName, csID, contract, qwsy, pcyk, ccyk, fdsy, zxj, dcc, kcc, sxf);
			return tradeService.insert(trade);
		}
		//模糊查找
		@RequestMapping("/showAllByCondition.action")
		public @ResponseBody ArrayList<Trade> selectAllByConditiona(Trade trade){
			return tradeService.selectAllByCondition(trade);
		}
		//生成图表：1.同策略查询
		@RequestMapping("/highcharts_byclName.action")
		public String highcharts_byclName(HttpServletRequest request,String contract,String clName,String tradeCount,String startTime,String endTime,Model model) throws ParseException{
			System.out.println("----------------------------------");
			System.out.println(contract);
			//把搜索的数据存储到数据库中
			Admin admin = (Admin)request.getSession().getAttribute("admin");
			String userID = admin.getUserID();
			Date dddd = new Date();
			Query_info info = new Query_info(userID,dddd,startTime,endTime,tradeCount,clName,contract,1);
			System.out.println(info);
			query_infoService.insert(info);
			//生成图表
			//开始结束时间
			String starttime = startTime.replace("-", "");
			String endtime = endTime.replace("-", "");
			//合约数组
			String [] contract_all = contract.split("-");
			//往前端传的值 遍历生成图表的
			Map<String,ArrayList<Trade>> map = new HashMap<String,ArrayList<Trade>>();
			Map<String,Map<String,String>> table = new HashMap<String,Map<String,String>>();
			//显示单个图表
			//生成Tables有多少数组就生成多少个表格 也就是put多少个map元素
			for (int i = 0; i < contract_all.length; i++) {
				ArrayList<Trade> list = tradeService.highcharts_byclName(contract_all[i],clName, tradeCount, starttime, endtime);
				System.out.println("-----------------------");
				for (Trade trade : list) {
					
					System.out.println(trade);
				}
				//日期
				StringBuffer time=new StringBuffer();
				//浮动盈亏
		        StringBuffer pcyk=new StringBuffer();  
		        //格式化数据，格式为[time1,time2,....],[value1,value2,....]  
		        time.append("["); 
		        pcyk.append("["); 
		        //两个double相加  有时候会 出现小数点后面多个数的问题 用BigDecimal转化来做
		        double temp = 0.00 ;
		        for (int j = 0; j < list.size(); j++) {
		        	BigDecimal b1 = new BigDecimal(Double.toString(temp)); 
		        	BigDecimal b2 = new BigDecimal(list.get(j).getPcyk()); 
		        	temp = b1.add(b2).doubleValue();
		            time.append(list.get(j).getTime()); 
		            //平仓盈亏
		            pcyk.append(temp + "");  
		            //若为最后一个则不加逗号  
		            if(j<list.size()-1){  
		                time.append(",");  
		                pcyk.append(",");  
		            }  
		        }  
		        time.append("]");  
		        pcyk.append("]");  
		        //有就将数据封装到map中
		        map.put(""+contract_all[i],list);
		        //数据过度
		        HashMap<String,String> temp2 = new HashMap<String,String>();
		        temp2.put(time.toString(), pcyk.toString());
		        table.put(""+contract_all[i],temp2);
		        System.out.println("-----------------");
				System.out.println("-----------------");
				System.out.println("----------time-------" +time);
				System.out.println("-----------pcyk------"+pcyk);
				System.out.println("-----------------");
			
			}
			model.addAttribute("map", map);
			model.addAttribute("table", table);
			//显示整个统计
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
			//全部的查找   1天(d) = 86400000毫秒(ms)
			//开始时间 结束时间 st1Time  ed1Time  format		
			//X轴
				//得到时间的long值
				//开始时间
				Date st1 = format2.parse(startTime);
				long st1Time = st1.getTime();
				//结束时间
				Date ed1 = format2.parse(endTime);
				long ed1Time = ed1.getTime();
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for (long j = st1Time; j <= ed1Time; j= j + 86400000l) {
				 sb.append(format.format(new Date(j)));
				 sb.append(","); 
			}
			sb.append("]");
			model.addAttribute("alltime", sb);
			//Y轴
			Map<String,String> value = new HashMap<String,String>();
			//得到每一个选中的策略 放到Map value集合里去
 			for (int j = 0; j < contract_all.length; j++) {
				StringBuffer tempdate = new StringBuffer();
				tempdate.append("[");
				double temp = 0.00 ;
				for (long k = st1Time; k <= ed1Time; k= k + 86400000l) {
					String time = format.format(new Date(k));
					ArrayList<Trade> list = tradeService.highcharts_byclName_2(contract_all[j],clName, tradeCount, time);					
					System.out.println("---------------------------------------------------------------");
					System.out.println("--------------" + list.size());
					if(list.size() != 0){
						for (int l = 0; l < list.size(); l++) {
							BigDecimal b1 = new BigDecimal(Double.toString(temp)); 
				        	BigDecimal b2 = new BigDecimal(list.get(l).getPcyk()); 
				        	temp = b1.add(b2).doubleValue();
						}
						System.out.println("--------------" + list.get(0).getPcyk());
						System.out.println("--------------" + temp);
						tempdate.append("" + temp);
					}else{
						temp = temp + 0;
						tempdate.append("" + temp);
					}
					tempdate.append(",");
				}
				tempdate.append("]");
				value.put(contract_all[j], tempdate.toString());
			}
 			model.addAttribute("value", value);
 			
			return "show_byclName";
		};
		//生成图表：2.同合约查询
		@RequestMapping("/highcharts_bycontract.action")
		public String highcharts_bycontract(HttpServletRequest request ,Model model,String clName,String contract,String tradeCount,String startTime,String endTime) throws ParseException{
			//把搜索的数据存储到数据库中
			Admin admin = (Admin)request.getSession().getAttribute("admin");
			String userID = admin.getUserID();
			Date dddd = new Date();
			Query_info info = new Query_info(userID,dddd,startTime,endTime,tradeCount,clName,contract,2);
			query_infoService.insert(info);
			//开始结束时间
			String starttime = startTime.replace("-", "");
			String endtime = endTime.replace("-", "");
			//合约数组
			String [] clName_all = clName.split("-");
			//往前端传的值 遍历生成图表的
			Map<String,ArrayList<Trade>> map = new HashMap<String,ArrayList<Trade>>();
			Map<String,Map<String,String>> table = new HashMap<String,Map<String,String>>();
			//显示单个图表
			//生成Tables有多少数组就生成多少个表格 也就是put多少个map元素
			for (int i = 0; i < clName_all.length; i++) {
				ArrayList<Trade> list = tradeService.highcharts_bycontract(clName_all[i],contract, tradeCount, starttime, endtime);
				System.out.println("-----------------------");
				for (Trade trade : list) {
					
					System.out.println(trade);
				}
				//日期
				StringBuffer time=new StringBuffer();
				//浮动盈亏
		        StringBuffer pcyk=new StringBuffer();  
		        //格式化数据，格式为[time1,time2,....],[value1,value2,....]  
		        time.append("["); 
		        pcyk.append("["); 
		        //两个double相加  有时候会 出现小数点后面多个数的问题 用BigDecimal转化来做
		        double temp = 0.00 ;
		        for (int j = 0; j < list.size(); j++) {
		        	BigDecimal b1 = new BigDecimal(Double.toString(temp)); 
		        	BigDecimal b2 = new BigDecimal(list.get(j).getPcyk()); 
		        	temp = b1.add(b2).doubleValue();
		            time.append(list.get(j).getTime()); 
		            //平仓盈亏
		            pcyk.append(temp + "");  
		            //若为最后一个则不加逗号  
		            if(j<list.size()-1){  
		                time.append(",");  
		                pcyk.append(",");  
		            }  
		        }  
		        time.append("]");  
		        pcyk.append("]");  
		        //有就将数据封装到map中
		        map.put(""+clName_all[i],list);
		        //数据过度
		        HashMap<String,String> temp2 = new HashMap<String,String>();
		        temp2.put(time.toString(), pcyk.toString());
		        table.put(""+clName_all[i],temp2);
		        System.out.println("-----------------");
				System.out.println("-----------------");
				System.out.println("----------time-------" +time);
				System.out.println("-----------pcyk------"+pcyk);
				System.out.println("-----------------");
			
			}
			model.addAttribute("map", map);
			model.addAttribute("table", table);
			//显示整个统计
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
			//全部的查找   1天(d) = 86400000毫秒(ms)
			//开始时间 结束时间 st1Time  ed1Time  format		
			//X轴
				//得到时间的long值
				//开始时间
				Date st1 = format2.parse(startTime);
				long st1Time = st1.getTime();
				//结束时间
				Date ed1 = format2.parse(endTime);
				long ed1Time = ed1.getTime();
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for (long j = st1Time; j <= ed1Time; j= j + 86400000) {
				 sb.append(format.format(new Date(j)));
				 sb.append(","); 
			}
			sb.append("]");
			model.addAttribute("alltime", sb);
			//Y轴
			Map<String,String> value = new HashMap<String,String>();
			/*//色彩
			String [] colors = {"#FFB6C1","#90ed7d","#FFF0F5","#4B0082","#0000FF","#FFD700","#FFA500","#FFDEAD","#BC8F8F","#FFDAB9","#FF6347","#FFA07A","#FF6700","#DEB887","#FFFF00","#F5DEB3","#808000","#7CFC00","#3CB371","#00FFFF","#008B8B"};*/
			//得到每一个选中的策略 放到Map value集合里去
 			for (int j = 0; j < clName_all.length; j++) {
				StringBuffer tempdate = new StringBuffer();
				tempdate.append("[");
				double temp = 0.00 ;
				for (long k = st1Time; k <= ed1Time; k= k + 86400000) {
					String time = format.format(new Date(k));
					ArrayList<Trade> list = tradeService.highcharts_bycontract_2(clName_all[j],contract, tradeCount, time);					
					System.out.println("---------------------------------------------------------------");
					System.out.println("--------------" + list.size());
					if(list.size() != 0){
						for (int l = 0; l < list.size(); l++) {
							BigDecimal b1 = new BigDecimal(Double.toString(temp)); 
				        	BigDecimal b2 = new BigDecimal(list.get(l).getPcyk()); 
				        	temp = b1.add(b2).doubleValue();
						}
						System.out.println("--------------" + list.get(0).getPcyk());
						System.out.println("--------------" + temp);
						tempdate.append("" + temp);
					}else{
						temp = temp + 0;
						tempdate.append("" + temp);
					}
					tempdate.append(",");
				}
				tempdate.append("]");
				value.put(clName_all[j], tempdate.toString());
			}
 			model.addAttribute("value", value);
 			
			return "show_byclName";
		};
		//策略查询   之前的同交同账号下不同策略查询
		@RequestMapping("/showCL.action")  
	    public String showCL( String jyzh,String contract,String starttime,String endtime,String clm,Model model, HttpServletRequest request) throws ParseException { 
			/*System.out.println("交易：" + jyzh + contract + starttime + endtime);
			System.out.println("复选：" + clm.split(",")[0] +clm.split(",")[1] +clm.split(",")[3]);	
			System.out.println("---------------账号：" + jyzh);
			System.out.println("---------------策略名：" + clm);
			.[GL08].null.[GH02].[GL01].[GL03].[GL05].[GL04].[GL07].[GL06]
			System.out.println("---------------合约：" + contract);*/
			//做个开关
			boolean open = false;
			//复选框根据name返回的是一个字符串，选中项的value用逗号相隔
			//得到时间格式 2017-01-01
			System.out.println("---------------账号：" + jyzh);
			System.out.println("---------------策略名：" + clm);
			//创建一个数组
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
			
			String [] clName = null;
			//处理策略名字 得到选中要查询的策略数组
			if(!(clm.length() == 0)){
				 clName = clm.substring(1).split("-");
			StringBuffer all1 = new StringBuffer();
			all1.append("[");
		
			for (int i = 0; i < clName.length; i++) {
				all1.append("\"");
				all1.append(clName[i]);
				all1.append("\"");
				if(!(i == clName.length-1)){
					all1.append(",");
				}
			}
			all1.append("]");
			model.addAttribute("all", all1);
			}
			//交易账号
			String tradeCount = jyzh.trim();
			//合约
			String contract1 = contract.trim();
			//传到前端的Map
			Map<String,ArrayList<Trade>> map = new HashMap<String,ArrayList<Trade>>();
			Map<String,Map<String,String>> table = new HashMap<String,Map<String,String>>();	
			if(clm == null||!(starttime.matches("^\\d{4}-\\d{2}-\\d{2}"))||!(endtime.matches("^\\d{4}-\\d{2}-\\d{2}"))||starttime == null||endtime == null){ 
			
			}else{			
			//得到时间的long值
			//开始时间
			Date st1 = format2.parse(starttime);
			long st1Time = st1.getTime();
			//结束时间
			Date ed1 = format2.parse(endtime);
			long ed1Time = ed1.getTime();
			//得到所有复选框数值
			//所有策略的集合
			ArrayList<String> list = tradeService.selectAllclName();
			/*String [] select = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				select[i] = list.get(i);
			}*/
			//建立所有策略长度的List
			ArrayList<Trade> [] arrayList = new ArrayList[list.size()];
			
			//循环遍历复选框长度次数 分别赋值传给前端
			for (int i = 0; i < list.size(); i++) {
				System.out.println("-------------第" + i + "次循环-------");
				//每循环一次  查一次选中项有没有 true 选中了 false没选中
				boolean boo = false;
				for (int j = 0; j < clName.length; j++) {
					if(clName[j].equals(list.get(i))){
						boo = true;
					}
				}
				System.out.println("-----------------");
				System.out.println("-------------boo:::" + boo);
				System.out.println("-----------------");
				//为真 说明前端被选中  没有 不需要  直接给前端赋值两个0 0
				if(boo){
					System.out.println("-------------boo=" + boo);
					//查询
					arrayList[i] = tradeService.selectCL(tradeCount, contract1,list.get(i));
					//还的过滤掉时间段
					for (int k = arrayList[i].size() - 1; k >= 0; k--) {			
						long time123 = format.parse(arrayList[i].get(k).getTime()).getTime();
						System.out.println("开始时间------------：：" + st1Time);						
						System.out.println("每一个时间------------：：" + time123);
						System.out.println("结束时间------------：：" + ed1Time);						
						//循环出所有的time 如果时间不在范围内 删除 得到最新的符合时间条件的
						if(!(time123 >= st1Time && time123<= ed1Time)){
							arrayList[i].remove(k);
						}
					}
					//把每一个ArrayList<Trade>也给到前端  显示表中 这是每天的盈亏单数据 
					model.addAttribute(""+list.get(i)+"table", arrayList[i]);
					if(arrayList[i].size() != 0){
						open = true;
						System.out.println("----------boo=true重新的size" + arrayList[i].size() );
						//日期
						StringBuffer time=new StringBuffer();
						//浮动盈亏
				        StringBuffer fdsy=new StringBuffer();  
				        //格式化数据，格式为[time1,time2,....],[value1,value2,....]  
				        time.append("["); 
				        fdsy.append("["); 
				        //两个double相加  有时候会 出现小数点后面多个数的问题 用BigDecimal转化来做
				        double temp = 0.00 ;
				        for (int j = 0; j < arrayList[i].size(); j++) {
				        	BigDecimal b1 = new BigDecimal(Double.toString(temp)); 
				        	BigDecimal b2 = new BigDecimal(arrayList[i].get(j).getFdsy()); 
				        	temp = b1.add(b2).doubleValue();
				            time.append(arrayList[i].get(j).getTime()); 
				            //平仓盈亏
				            fdsy.append(temp + "");  
				            //若为最后一个则不加逗号  
				            if(j<arrayList[i].size()-1){  
				                time.append(",");  
				                fdsy.append(",");  
				            }  
				        }  
				        time.append("]");  
				        fdsy.append("]");  
				        //有就将数据封装到map中
				        map.put(""+list.get(i),arrayList[i]);
				        //数据过度
				        HashMap<String,String> temp2 = new HashMap<String,String>();
				        temp2.put(time.toString(), fdsy.toString());
				        table.put(""+list.get(i),temp2);
				        System.out.println("-----------------");
						System.out.println("-----------------");
						System.out.println("----------time-------" +time);
						System.out.println("-----------fdsy------"+fdsy);
						System.out.println("-----------------");
					}else{
						System.out.println("-------------第"+i + "个" + "0");
						//如果没有值就是0
					}
				}else{
					System.out.println("-------------boo=" + boo);
					System.out.println("-------------第"+i + "个" + "0");
					//判断有没有选中的 0 为没有选中 1为选中
				}
			}
			}
			model.addAttribute("map", map);
			model.addAttribute("table", table);
			//全部的查找   1天(d) = 86400000毫秒(ms)
			//开始时间 结束时间 st1Time  ed1Time  format		
			if(open){
				//得到时间的long值
				//开始时间
				Date st1 = format2.parse(starttime);
				long st1Time = st1.getTime();
				//结束时间
				Date ed1 = format2.parse(endtime);
				long ed1Time = ed1.getTime();
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for (long j = st1Time; j <= ed1Time; j= j + 86400000) {
				 sb.append(format.format(new Date(j)));
				 sb.append(","); 
			}
			sb.append("]");
			model.addAttribute("alltime", sb);
			Map<String,String> value = new HashMap<String,String>();
			//得到每一个选中的策略 放到Map value集合里去
 			for (int j = 0; j < clName.length; j++) {
				StringBuffer tempdate = new StringBuffer();
				tempdate.append("[");
				double temp = 0.00 ;
				for (long k = st1Time; k <= ed1Time; k= k + 86400000l) {
					String time = format.format(new Date(k));
					ArrayList<Trade> list = tradeService.selectCL2(tradeCount, contract1, clName[j], time);
					System.out.println("---------------------------------------------------------------");
					System.out.println("--------------" + list.size());
					if(list.size() != 0){
						for (int l = 0; l < list.size(); l++) {
							BigDecimal b1 = new BigDecimal(Double.toString(temp)); 
				        	BigDecimal b2 = new BigDecimal(list.get(l).getFdsy()); 
				        	temp = b1.add(b2).doubleValue();
						}
						System.out.println("--------------" + list.get(0).getFdsy());
						System.out.println("--------------" + temp);
						tempdate.append("" + temp);
					}else{
						temp = temp + 0;
						tempdate.append("" + temp);
					}
					tempdate.append(",");
				}
				tempdate.append("]");
				value.put(clName[j], tempdate.toString());
			}
 			model.addAttribute("value", value);
 			}else{
 				Map<String,String> value = new HashMap<String,String>();
 				model.addAttribute("alltime", 0);
 				model.addAttribute("value", value);
 			}
			return "show";
		
		}
		
}
