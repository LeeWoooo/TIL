package com.sparta.week2prac.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Person extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String addr;

    public Person(PersonDTO pDTO){
        this.name = pDTO.getName();
        this.addr = pDTO.getAddr();
    }

    public Person(String name, String addr){
        this.name = name;
        this.addr = addr;
    }

    public void update(PersonDTO pDTO){
        this.name = pDTO.getName();
        this.addr = pDTO.getAddr();
    }
}
