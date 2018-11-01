package com.ry.szq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ry.szq.dao.Query_infoMapper;
import com.ry.szq.pojo.Query_info;

@Service
public class Query_infoService {
	@Autowired
	private Query_infoMapper query_infoMapper;

	public Query_infoMapper getQuery_infoMapper() {
		return query_infoMapper;
	}

	public void setQuery_infoMapper(Query_infoMapper query_infoMapper) {
		this.query_infoMapper = query_infoMapper;
	}
	//增加
	public int insert(Query_info query_info){
		return query_infoMapper.insert(query_info);
	}
	//查询
	public Query_info queryByInfo(String userID){
		return query_infoMapper.queryByInfo(userID);
	}
}
