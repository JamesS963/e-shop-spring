package com.springproject.eshop.dao;

import org.springframework.stereotype.Repository;

import com.springproject.eshop.model.Item;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ItemDao extends JpaRepository<Item, Long> {

}
