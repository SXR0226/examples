package com.example.example06.cotroller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handleValidException(MethodArgumentNotValidException exception){
        StringBuilder stringBuilder=new StringBuilder();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(e->{
                    stringBuilder.append(e.getField());
                    stringBuilder.append(":");
                    stringBuilder.append(e.getDefaultMessage());
                    stringBuilder.append(";");
                });
        return Map.of("message", stringBuilder.toString());
    }
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handleInvalidFormatException(InvalidFormatException exception){
        StringBuilder sBuilder=new StringBuilder();
        exception.getPath()
                .forEach(e->{
                    sBuilder.append("属性");
                    sBuilder.append(e.getFieldName());
                    sBuilder.append("，您输入的值：" +exception.getValue());
                    sBuilder.append("，类型错误!");
                });
        return Map.of("message", sBuilder.toString());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handleConstraintViolationException(ConstraintViolationException exception){
        StringBuilder sBuilder=new StringBuilder();
        Set<ConstraintViolation<?>> violations=exception.getConstraintViolations();
        violations.forEach(e->{
            sBuilder.append(e.getMessage()+"; ");
        });
        return Map.of("message",sBuilder.toString());
    }
}
