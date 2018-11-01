package com.ry.szq.service;

import java.awt.print.Paper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ry.szq.common.Pager;
import com.ry.szq.dao.TradeMapper;
import com.ry.szq.pojo.Trade;

@Service
public class TradeService {
	@Autowired
	private TradeMapper tradeMapper;

	public TradeMapper getTradeMapper() {
		return tradeMapper;
	}

	public void setTradeMapper(TradeMapper tradeMapper) {
		this.tradeMapper = tradeMapper;
	}
	//增加
	public int insert(Trade trade){
		return tradeMapper.insert(trade);
	}
		//修改
	public int update(Trade trade){
		return tradeMapper.update(trade);
	}
		//删除
	public int delete(int id){
		return tradeMapper.delete(id);
	}
		//查询
		//查询单个
	public Trade selectByID(int id){
		return tradeMapper.selectByID(id);
	}
		//查找全部
	public ArrayList<Trade> selectAll(){
		return tradeMapper.selectAll();
	}
	
		//模糊查找
	public ArrayList<Trade> selectAllByCondition(Trade trade){
		return tradeMapper.selectAllByCondition(trade);
	}
	//查找:根据  账号  合约    策略名
	public ArrayList<Trade> selectCL(String tradeCount,String contract,String clName){
		return tradeMapper.selectCL(tradeCount, contract, clName);
	}
	//查找:根据  账号  合约    策略名
	public ArrayList<Trade> selectCL2(String tradeCount,String contract,String clName,String time){
			return tradeMapper.selectCL2(tradeCount, contract, clName, time);
		}
	//查询所有策略的名字
	public	ArrayList<String> selectAllclName(){
		return tradeMapper.selectAllclName();
	}
	//查询一段时间所有账号的名字
	public	ArrayList<String> showTradeCountByTime(String startTime,String endTime){
		return tradeMapper.showTradeCountByTime(startTime,endTime);
	}
	//查询一段时间所有策略的名字
	public	ArrayList<String> showClNameByTime(String startTime,String endTime){
		return tradeMapper.showClNameByTime(startTime,endTime);
	}
	//查询一段时间  一个  策略的 所有品种名字
		public	ArrayList<String> showContractByTime(String startTime,String endTime,String clName){
			return tradeMapper.showContractByTime(startTime,endTime,clName);
		}
	//查询一段时间所有合约的名字
	public	ArrayList<String> showContractByTime_2(String startTime,String endTime){
		return tradeMapper.showContractByTime_2(startTime,endTime);
	}
	//查询一段时间  一个  品种下的 所有策略名字
	public	ArrayList<String> showClNameByTime_2(String startTime,String endTime,String contract){
		return tradeMapper.showClNameByTime_2(startTime,endTime,contract);
	}
	//分页显示
	public	Map<String,Object> dividePager(int pageNum){
		//查下总记录数
		int recordCount = tradeMapper.recordCount();
		//生成分页的对象
		Pager pager = new Pager(recordCount,pageNum);
		//根据分页对象去查询数据
		ArrayList<Trade> list = tradeMapper.selectAllByPaper(pager);
		//将查询的两个数据包  封装在Map 集合中  传给controller
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pager", pager);
		map.put("trade", list);
		return map;
	};
	//查找全部 Bytime
		public Map<String,Object> selectAllByTime(String startTime,String endTime,int pageNum){
			//查下总记录数
			int recordCount = tradeMapper.recordCountByTime(startTime,endTime);
			System.out.println("------------------");
			System.out.println(recordCount);
			//生成分页的对象
			Pager pager = new Pager(recordCount,pageNum);
			int start = pager.getStart();
			if(start < 0){
				start =0;
			}
			int pageSize = pager.getPageSize();
			System.out.println(pager.toString());
			//根据分页对象去查询数据
			ArrayList<Trade> list = tradeMapper.selectAllByTime(startTime, endTime, start,pageSize);
			//将查询的两个数据包  封装在Map 集合中  传给controller
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("pager", pager);
			map.put("trade", list);
			return map;
		}
		//生成图表：1.同策略查询
		public ArrayList<Trade> highcharts_byclName(String contract,String clName,String tradeCount,String startTime,String endTime){
			
			String [] str =  tradeCount.split("-");
			return tradeMapper.highcharts_byclName(contract,clName, str, startTime, endTime);
		};
		public ArrayList<Trade> highcharts_byclName_2(String contract,String clName,String tradeCount,String time){	
			String [] str =  tradeCount.split("-");
			return tradeMapper.highcharts_byclName_2(contract,clName, str, time);
		};
		//生成图表：2.同合约查询
		public ArrayList<Trade> highcharts_bycontract(String clName,String contract,String tradeCount,String startTime,String endTime){
			String [] str =  tradeCount.split("-");
			return tradeMapper.highcharts_bycontract(clName,contract, str, startTime, endTime);
		};
		public ArrayList<Trade> highcharts_bycontract_2(String clName,String contract,String tradeCount,String time){
			String [] str =  tradeCount.split("-");
			return tradeMapper.highcharts_bycontract_2(clName,contract, str, time);
		};
	
}
