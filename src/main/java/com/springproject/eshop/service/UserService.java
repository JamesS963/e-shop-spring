package com.springproject.eshop.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.springproject.eshop.dao.AuthorityDao;
import com.springproject.eshop.dao.ItemDao;
import com.springproject.eshop.dao.UserDao;
import com.springproject.eshop.model.Authority;
import com.springproject.eshop.model.Item;
import com.springproject.eshop.model.User;
import com.springproject.eshop.security.Authentication;
import com.springproject.eshop.security.CustomUserDetails;
import com.springproject.eshop.settings.AuthorityType;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthorityDao authorityDao;

	@Autowired
	private ItemDao itemDao;

	BCryptPasswordEncoder encoder;

	public UserService() {

		super();
		encoder = new BCryptPasswordEncoder();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found.");
		}
		return new CustomUserDetails(user);
	}

	private boolean created;

	public boolean create(User user) {

		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(authorityDao.getByAuthority(AuthorityType.ROLE_USER));

		user.setAuthorities(authorities);
		user.setPassword(encoder.encode(user.getPassword()));

		created = false;
		userDao.findByUsernameOptional(user.getUsername()).orElseGet(() -> {
			created = true;
			return userDao.save(user);
		});

		return created;
	}

	public User save(User user) {
		return userDao.save(user);
	}

	public Optional<User> retrieveById(long id) {
		return userDao.findById(id);
	}

	// this can probably be done in a much better way but im just being a bit lazy
	// at
	// the moment to get some bare bones features up.
	public void addItemToBasket(Long itemID) {
		Authentication authentication = new Authentication();
		Optional<Item> item = itemDao.findById(itemID);
		Optional<User> user = retrieveById(authentication.getLoggedInUser().getId());

		// later on i will throw an exception to return to the controller but for the
		// now it will just save, this should work fine for now but will not be good
		// practice in a real application.
		item.ifPresent(i -> {
			user.ifPresent(u -> {
				u.addItemToBasket(i);
				userDao.save(u);
			});
		});

	}

}
