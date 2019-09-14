package com.tokenAuth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tokenAuth.model.Customer;
import com.tokenAuth.service.CustomerService;

@RestController
public class UserProfileController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/api/users/user/{id}",produces = "application/json")
    public Customer getUserDetail(@PathVariable Long id){
        return customerService.findById(id);
    }
    
    @GetMapping(value = "/api/message/{name}")
    public String getMessage(@PathVariable String name){
		return "Welcome "+name;
    }
}
