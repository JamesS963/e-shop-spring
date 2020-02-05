package com.springproject.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.eshop.model.Item;
import com.springproject.eshop.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	ItemService itemService;
	
	
	@GetMapping("/createItem")
	public ModelAndView getCreateItemPage() {
		return new ModelAndView("createItem", "item", new Item());
	}

	@PostMapping("/createItem")
	public ModelAndView postCreateItemPage(@ModelAttribute("item") Item item) {
		item =itemService.createItem(item);
		if (item!= null) {
				return new ModelAndView("index", "message", "item has been created").addObject("items",itemService.getAllItems());
		}else {
			 return new ModelAndView("index", "message", "item has not been created");
					}
	}
}
