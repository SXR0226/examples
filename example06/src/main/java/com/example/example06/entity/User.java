package com.example.example06.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Size(min = 2,max = 6,
    message = "您输入的值为${validatedValue},用户名长度应大于{min},"
    +"小于{max}")
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 6,message = "您输入的值为${validatedValue},"
    +"密码长度应大于{min}")
    private String password;
    @Min(value = 18,message = "您的年龄必须大于{value}")
    @Max(value = 60,message = "您的年龄必须小于{value}")
    private int age;
    @Email
    private String email;
}
