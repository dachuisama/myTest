/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.test.entity.MyTest;
import com.thinkgem.jeesite.modules.test.dao.MyTestDao;

/**
 * 单表生成Service
 * @author ThinkGem
 * @version 2019-07-11
 */
@Service
@Transactional(readOnly = true)
public class MyTestService extends CrudService<MyTestDao, MyTest> {

	public MyTest get(String id) {
		return super.get(id);
	}
	
	public List<MyTest> findList(MyTest myTest) {
		return super.findList(myTest);
	}
	
	public Page<MyTest> findPage(Page<MyTest> page, MyTest myTest) {
		return super.findPage(page, myTest);
	}
	
	@Transactional(readOnly = false)
	public void save(MyTest myTest) {
		super.save(myTest);
	}
	
	@Transactional(readOnly = false)
	public void delete(MyTest myTest) {
		super.delete(myTest);
	}
	
}