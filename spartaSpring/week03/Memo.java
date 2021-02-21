package com.sparta.week03_prac.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Memo extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String contents;

    public Memo(MemoDTO memoDTO){
        this.userName = memoDTO.getUserName();
        this.contents = memoDTO.getContents();
    }

    public void update(MemoDTO memoDTO){
        this.userName = memoDTO.getUserName();
        this.contents = memoDTO.getContents();
    }
}
