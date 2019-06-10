package com.example.example07.controller;

import com.example.example07.component.EncryptorComponent;
import com.example.example07.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    private Map<String, User> users=new HashMap();
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EncryptorComponent encryptorComponent;
    @PostMapping("/register")
    public Map register(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.put(user.getUserName(), user);
        return Map.of("User",user);
    }
    @PostMapping("/login")
    public void login(@RequestBody User user, HttpServletResponse response){
        Optional.ofNullable(users.get(user.getUserName()))
                .or(()->{
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"用户名或密码错误");
                })
                .ifPresent(u->{
                    if (!passwordEncoder.matches(user.getPassword(), u.getPassword())){
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"用户名或密码错误");
                    }
                    Map map=Map.of("name",u.getUserName());
                    String token=encryptorComponent.encrypt(map);
                    response.setHeader("Authorization", token);
                });
    }
    @GetMapping("/index")
    public Map getIndex(@RequestAttribute String name){
        System.out.println(name);
        return Map.of("真实用户名", name);
    }
}
