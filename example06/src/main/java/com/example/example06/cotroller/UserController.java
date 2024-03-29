package com.example.example06.cotroller;

import com.example.example06.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {
    @PostMapping("/users")
    public Map postUser(@Valid @RequestBody User user){
        return Map.of("user",user);
    }

    @GetMapping("/users/{username}")
    public void getConstraintViolation(
            @Size(min = 2,max = 6,message = "用户参数信息错误")
            @PathVariable String username
    ){}
}
