package com.example.example02;

import com.example.example02.entity.Address;
import com.example.example02.entity.User;
import com.example.example02.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Example02ApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void contextLoads() {
        /*User u=new User("abb");
        userRepository.addUser(u);
        Address add=new Address("liiii");
        userRepository.addAddress(add, 4);
        userRepository.updateUser(4, "aba");
        userRepository.updateAddress(7, 5);
        userRepository.updateAddress(7, 4);
        userRepository.removeAddress(7);
        userRepository.removeUser(4);*/
        userRepository.listAddresses(3);
    }

}
