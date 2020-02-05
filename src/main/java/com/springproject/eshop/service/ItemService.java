package com.springproject.eshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.eshop.dao.ItemDao;
import com.springproject.eshop.model.Item;

@Service
public class ItemService {
	
	@Autowired
	private ItemDao itemDao;
	
	public ItemService() {
		super();
	}
	
	public Item createItem(Item item) {
		return itemDao.save(item);
	}
	
	public List<Item> getAllItems(){
		return itemDao.findAll();
	}
	
}
