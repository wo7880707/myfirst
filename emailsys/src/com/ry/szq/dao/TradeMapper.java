package com.ry.szq.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.ry.szq.common.Pager;
import com.ry.szq.pojo.Trade;

public interface TradeMapper {
	//增删改查方法的实现
	//增加
	int insert(Trade trade); 
	//删除(根据主键)
	int delete(int id);
	//修改
    int update(Trade trade);
	//查找
	//查找单个
	Trade selectByID(int id);
	//查找全部
	ArrayList<Trade> selectAll();
	//查找全部 ByTime
	ArrayList<Trade> selectAllByTime(@Param(value="startTime")String startTime,@Param(value="endTime")String endTime,@Param(value="start")int start,@Param(value="pageSize")int pageSize);
	//模糊查找
	ArrayList<Trade> selectAllByCondition(Trade trade);
	//查找:根据  账号  合约    策略名
	ArrayList<Trade> selectCL(@Param(value="tradeCount")String tradeCount,@Param(value="contract")String contract,@Param(value="clName")String clName);
	//查找:根据  账号  合约    策略名 日期
	ArrayList<Trade> selectCL2(@Param(value="tradeCount")String tradeCount,@Param(value="contract")String contract,@Param(value="clName")String clName,@Param(value="time")String time);
	//查询所有策略的名字
	ArrayList<String> selectAllclName();
	//查询一定时间内所有账号的名字
	ArrayList<String> showTradeCountByTime(@Param(value="startTime")String startTime,@Param(value="endTime")String endTime);
	//查询一定时间内所有策略的名字
	ArrayList<String> showClNameByTime(@Param(value="startTime")String startTime,@Param(value="endTime")String endTime);
	//查询一定时间内同策略下的所有合约的名字
	ArrayList<String> showContractByTime(@Param(value="startTime")String startTime,@Param(value="endTime")String endTime,@Param(value="clName")String clName);
	//查询一定时间内所有合约的名字
	ArrayList<String> showContractByTime_2(@Param(value="startTime")String startTime,@Param(value="endTime")String endTime);
	//查询一定时间内同合约下的所有策略的名字
	ArrayList<String> showClNameByTime_2(@Param(value="startTime")String startTime,@Param(value="endTime")String endTime,@Param(value="contract")String contract);
	//分页显示
	ArrayList<Trade> selectAllByPaper(Pager pager);
	//分页  总记录数
	int recordCount();
	//分页  总记录数
	int recordCountByTime(@Param(value="startTime")String startTime,@Param(value="endTime")String endTime);
	//生成图表：1.同策略查询
	ArrayList<Trade> highcharts_byclName(@Param(value="contract")String contract,@Param(value="clName")String clName,@Param(value="tradeCount")String[] tradeCount,@Param(value="startTime")String startTime,@Param(value="endTime")String endTime);
	ArrayList<Trade> highcharts_byclName_2(@Param(value="contract")String contract,@Param(value="clName")String clName,@Param(value="tradeCount")String[] tradeCount,@Param(value="time")String time);
	//生成图表：2.同合约查询
	ArrayList<Trade> highcharts_bycontract(@Param(value="clName")String clName,@Param(value="contract")String contract,@Param(value="tradeCount")String[] tradeCount,@Param(value="startTime")String startTime,@Param(value="endTime")String endTime);
	ArrayList<Trade> highcharts_bycontract_2(@Param(value="clName")String clName,@Param(value="contract")String contract,@Param(value="tradeCount")String[] tradeCount,@Param(value="time")String time);
}
