package com.springproject.eshop.dataloader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springproject.eshop.dao.AuthorityDao;


import com.springproject.eshop.model.Authority;
import com.springproject.eshop.model.User;
import com.springproject.eshop.service.UserService;
import com.springproject.eshop.settings.AuthorityType;

@Configuration
public class LoadDatabase {
	@Bean
	CommandLineRunner initDatabase(AuthorityDao authorityDao) {
		return args -> {
			authorityDao.save(new Authority(AuthorityType.ROLE_USER));
		};
	}
}
