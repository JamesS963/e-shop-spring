package com.springproject.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.eshop.model.Item;
import com.springproject.eshop.service.UserService;

@Controller
public class BasketController {

	@Autowired
	UserService userService;

	@PostMapping("/addItemToBasket")
	public ModelAndView addItemToBasket(@RequestParam("itemId") Long itemID) {
		userService.addItemToBasket(itemID);
		return new ModelAndView("index").addObject("message", "item added sucessfully to basket.");
	}
	
	@GetMapping("/basket")
	public ModelAndView myBasket() {
		return new ModelAndView("basket");
	}
}
