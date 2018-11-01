package com.ry.szq.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.szq.pojo.Count;
import com.ry.szq.service.CountService;

@Controller
@RequestMapping("/count")
public class CountAction {
	@Autowired
	private CountService countService;
	public CountService getCountService() {
		return countService;
	}

	public void setCountService(CountService countService) {
		this.countService = countService;
	}
	/*//修改
	@RequestMapping("/update.action")
	public @ResponseBody int update(Count count) {
		
		return countService.update(count);
	}*/
	//查询
	@RequestMapping("/queryById.action")
	public @ResponseBody Count queryById() {
		//查到原登录的次数
		Count c1 = countService.queryById(1);
		//本次登录 +1
		int num = c1.getCount() + 1;
		//把新次数保存数据库
		Count count = new Count(1,num);
		countService.update(count);
		return count;
	}
}

