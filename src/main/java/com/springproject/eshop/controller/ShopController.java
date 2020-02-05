package com.springproject.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.eshop.service.ItemService;

@Controller
public class ShopController {
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping(value = "/home")
	public ModelAndView getLoginPage() {
		return new ModelAndView("index").addObject("items", itemService.getAllItems());
	}
}
