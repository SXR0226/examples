package com.example.example05.controller;

import com.example.example05.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    private static List<User> users=creat();
    private static List<User> creat(){
        users=new ArrayList<>();
        User u1=new User(1, "BO", "123456", "956");
        User u2=new User(2, "SUN", "1122", "1021");
        User u3=new User(3, "ZHANG", "5544", "1221");
        users.add(u1);
        users.add(u2);
        users.add(u3);
        return users;
    }

    @GetMapping("/index")
    public Map getIndex(){
        return Map.of("index","main");
    }
    @GetMapping("/users")
    public Map getUser(){
        return Map.of("users",users);
    }
    @GetMapping("/users/{uid}")
    public Map getUser(@PathVariable int uid){
        System.out.println("user的id"+uid);
        User user=users.stream()
                .filter(u->u.getId()==uid)
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(user)
                .map(u-> Map.of("user", u))
                .orElse(Map.of());
    }
    @PostMapping("/users")
    public Map postUser(@RequestBody User user){
        users.add(user);
        return Map.of("users",user);
    }
}
