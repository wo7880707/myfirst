package com.ry.szq.dao;

import com.ry.szq.pojo.Count;

public interface CountMapper {
	//修改
	int update(Count count);
	//查询
	Count queryById(int id);
}
