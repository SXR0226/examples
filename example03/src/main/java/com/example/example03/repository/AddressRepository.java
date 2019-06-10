package com.example.example03.repository;

import com.example.example03.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address,Integer>{
    @Query("SELECT a FROM Address a WHERE a.detail=:detail")
    List<Address> list(@Param("detail") String detail);

}
