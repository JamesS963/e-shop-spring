package com.springproject.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.eshop.model.User;

@Controller
public class ShopController {
	@RequestMapping(value = "/home")
	public ModelAndView getLoginPage() {
		return new ModelAndView("index");
	}
}
