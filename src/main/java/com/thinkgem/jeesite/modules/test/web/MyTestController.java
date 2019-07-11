/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.test.entity.MyTest;
import com.thinkgem.jeesite.modules.test.service.MyTestService;

/**
 * 单表生成Controller
 * @author ThinkGem
 * @version 2019-07-11
 */
@Controller
@RequestMapping(value = "${adminPath}/test/myTest")
public class MyTestController extends BaseController {

	@Autowired
	private MyTestService myTestService;
	
	@ModelAttribute
	public MyTest get(@RequestParam(required=false) String id) {
		MyTest entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = myTestService.get(id);
		}
		if (entity == null){
			entity = new MyTest();
		}
		return entity;
	}
	
	@RequiresPermissions("test:myTest:view")
	@RequestMapping(value = {"list", ""})
	public String list(MyTest myTest, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MyTest> page = myTestService.findPage(new Page<MyTest>(request, response), myTest); 
		model.addAttribute("page", page);
		return "modules/test/myTestList";
	}

	@RequiresPermissions("test:myTest:view")
	@RequestMapping(value = "form")
	public String form(MyTest myTest, Model model) {
		model.addAttribute("myTest", myTest);
		return "modules/test/myTestForm";
	}

	@RequiresPermissions("test:myTest:edit")
	@RequestMapping(value = "save")
	public String save(MyTest myTest, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, myTest)){
			return form(myTest, model);
		}
		myTestService.save(myTest);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/test/myTest/?repage";
	}
	
	@RequiresPermissions("test:myTest:edit")
	@RequestMapping(value = "delete")
	public String delete(MyTest myTest, RedirectAttributes redirectAttributes) {
		myTestService.delete(myTest);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/test/myTest/?repage";
	}

}