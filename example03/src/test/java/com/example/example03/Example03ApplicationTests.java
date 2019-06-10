package com.example.example03;

import com.example.example03.entity.Address;
import com.example.example03.entity.User;
import com.example.example03.entity.UserAddress;
import com.example.example03.repository.AddressRepository;
import com.example.example03.repository.UserAddressRepository;
import com.example.example03.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Example03ApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserAddressRepository userAddressRepository;
    @Test
    public void init() {
        User u1=new User("sxr");
        userRepository.save(u1);
        User u2=new User("SUN");
        userRepository.save(u2);

        Address a1=new Address("956");
        addressRepository.save(a1);
        Address a2=new Address("925");
        addressRepository.save(a2);
        Address a3=new Address("1021");
        addressRepository.save(a3);

        UserAddress ua=new UserAddress(u1, a1);
        userAddressRepository.save(ua);
        UserAddress ua2=new UserAddress(u1, a2);
        userAddressRepository.save(ua2);
        UserAddress ua3=new UserAddress(u2, a3);
        userAddressRepository.save(ua3);
    }
    @Test
    public void addressRepTest(){
        addressRepository.list("956")
                .forEach(v->{
                    System.out.println(v.getId());
                });
    }
    @Test
    public void userAddressRepTest(){
        UserAddress ua=userAddressRepository.find("SUN", "1021");
        log.debug("时间{}", ua.getInsertTime());
    }
    @Test
    public void userRepTest(){
        User u=userRepository.find(1);
        log.debug("用户名为{}",u.getName());
    }
}
