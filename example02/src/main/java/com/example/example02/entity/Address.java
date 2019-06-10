package com.example.example02.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity@Setter@Getter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String detail;
    @Column(columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP",
    insertable = false,
    updatable = false)
    private LocalDateTime insertTime;
    @ManyToOne
    private User user;
    public Address(String detail){
        this.detail=detail;
    }
}
