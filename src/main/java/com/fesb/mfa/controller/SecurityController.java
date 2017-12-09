package com.fesb.mfa.controller;

import com.fesb.mfa.domain.User;
import com.fesb.mfa.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    private GenericService userService;

    @RequestMapping(value ="/users", method = RequestMethod.GET)
    public List<User> getUsers(){
        return userService.findAllUsers();
    }

    @RequestMapping(value ="/user", method = RequestMethod.POST)
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @RequestMapping(value ="/user/{userName}", method = RequestMethod.GET)
    public User getUser(@PathVariable("userName") String userName){
        return userService.findByUsername(userName);
    }

    @RequestMapping(value ="/login", method = RequestMethod.POST)
    public User login(@RequestBody User user){
        return userService.login(user.getUsername(), user.getPassword());
    }
}
