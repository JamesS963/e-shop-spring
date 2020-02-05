package com.springproject.eshop.service;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.springproject.eshop.dao.AuthorityDao;
import com.springproject.eshop.dao.UserDao;
import com.springproject.eshop.model.Authority;
import com.springproject.eshop.model.User;
import com.springproject.eshop.settings.AuthorityType;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.condition.JRE;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@EnabledOnJre({ JRE.JAVA_8, JRE.JAVA_12 })
@Tag("service")
public class UserServiceTest {

	@Mock
	UserDao userDao;
	@Mock
	AuthorityDao authorityDao;
	@InjectMocks
	UserService userService;

	@Test
	public void test_ThatUserIsCreated() {
		User user = new User("James", "Password");
		when(userDao.findByUsername(user.getUsername())).thenReturn(null);
		when(authorityDao.getByAuthority(AuthorityType.ROLE_USER)).thenReturn(new Authority(AuthorityType.ROLE_USER));

		assertTrue(userService.create(user));

	}

	@Test
	public void test_ThatUserIsNotCreated_WhenUserAlreadyExists() {
		User user = new User("James", "Password");
		when(userDao.findByUsername(user.getUsername())).thenReturn(user);
		when(authorityDao.getByAuthority(AuthorityType.ROLE_USER)).thenReturn(new Authority(AuthorityType.ROLE_USER));

		assertTrue(userService.create(user));

	}
}
