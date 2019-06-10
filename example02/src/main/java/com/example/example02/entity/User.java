package com.example.example02.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity@Setter@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP",
    insertable = false,
    updatable = false)
    private LocalDateTime insertTime;
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Address> addressesList;
    public User(String name){
        this.name=name;
    }
}
