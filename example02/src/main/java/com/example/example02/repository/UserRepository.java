package com.example.example02.repository;

import com.example.example02.entity.Address;
import com.example.example02.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository@Transactional@Slf4j
public class UserRepository {
    @PersistenceContext
    private EntityManager em;
    /**
     * 添加用户，并返回包括数据库时间戳的用户对象
     * @param user
     * @return
     */
    public User addUser(User user) {
        em.persist(user);
        em.refresh(user);
        return user;
    }

    /**
     * 添加地址，并指定地址对应的用户
     * @param address
     * @param uid
     * @return
     */
    public Address addAddress(Address address, int uid) {
        User u=em.find(User.class, uid);
        address.setUser(u);
        em.persist(address);
        return address;
    }

    /**
     * 更新指定ID用户的姓名
     * @param uid
     * @param newName
     * @return
     */
    public User updateUser(int uid, String newName) {
        User u=em.find(User.class, uid);
        u.setName(newName);
        em.flush();
        return u;
    }

    /**
     * 尝试使用merge()，以及find()2种方法分别实现
     * 更新指定地址为指定用户
     * @param aid
     * @param uid
     * @return
     */
    public Address updateAddress(int aid, int uid) {
        User u=em.find(User.class, uid);
        User u1=em.merge(u);
        Address add=em.find(Address.class, aid);
        add.setUser(u1);
        em.flush();
        return add;
    }

    /**
     * 返回指定用户的全部地址，没有返回空集合，而非null
     * @param uid
     * @return
     */
    public List<Address> listAddresses(int uid) {
        User u=em.find(User.class, uid);
        List<Address> addresses=u.getAddressesList();
        for(Address address:addresses){
           log.debug("{}",address.getId());
        }
        return List.of();
    }

    public void removeAddress(int aid) {
        Address add=em.find(Address.class, aid);
        em.remove(add);
    }

    /**
     * 删除用户，设置级联操作或手动删除相关地址
     * @param uid
     */
    public void removeUser(int uid) {
        User u=em.find(User.class, uid);
        em.remove(u);
    }
}
