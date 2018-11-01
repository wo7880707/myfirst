package com.ry.szq.dao;

import com.ry.szq.pojo.Query_info;


public interface Query_infoMapper {
	//增加
	int insert(Query_info query_info); 
	//查询
	Query_info queryByInfo(String userID);
}
