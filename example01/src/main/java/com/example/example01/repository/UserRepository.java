package com.example.example01.repository;

import com.example.example01.entity.Address;
import com.example.example01.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserRepository {
    @PersistenceContext
    private EntityManager em;
    public void addUserAddress(){
        User user=new User("aaa");
        User user1=new User("bbb");
        Address address=new Address("123");
        Address address1=new Address("456");
        em.persist(user);
        address.setUser(user);
        em.persist(address);
        em.persist(user1);
        address1.setUser(user1);
        em.persist(address1);
    }
}
