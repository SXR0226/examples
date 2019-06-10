package com.example.example04.aspect;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface MyInterceptor {
    AuthorityType[] value() default AuthorityType.USER;
    public static enum  AuthorityType{
        USER,ADMIN,SUPERADMIN;
    }
}
