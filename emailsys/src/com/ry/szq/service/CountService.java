package com.ry.szq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ry.szq.dao.CountMapper;
import com.ry.szq.pojo.Count;
@Service
public class CountService {
	@Autowired
	private CountMapper countMapper;

	public CountMapper getCountMapper() {
		return countMapper;
	}

	public void setCountMapper(CountMapper countMapper) {
		this.countMapper = countMapper;
	}
	//修改
	public int update(Count count){
		return countMapper.update(count);
	}
		//查询
	public Count queryById(int id){
		return countMapper.queryById(id);
	}
}
