package com.tokenAuth.service;

import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.tokenAuth.model.Customer;
import com.tokenAuth.repo.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	public String login(String username, String password) {
		Optional<Customer> customer = customerRepository.login(username, password);
		if (customer.isPresent()) {
			String token = UUID.randomUUID().toString();
			Customer custom = customer.get();
			custom.setToken(token);
			customerRepository.save(custom);
			return token;
		}

		return StringUtils.EMPTY;
	}

	public Optional<User> findByToken(String token) {
		Optional<Customer> customer = customerRepository.findByToken(token);
		if (customer.isPresent()) {
			Customer customer1 = customer.get();
			User user = new User(customer1.getUserName(),
					customer1.getPassword(), true, true, true, true,
					AuthorityUtils.createAuthorityList("USER"));
			
			return Optional.of(user);
		}
		return Optional.empty();
	}

	public Customer findById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return customer.orElse(null);
	}
}