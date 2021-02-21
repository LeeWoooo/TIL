package com.sparta.week03_prac.domain;

import lombok.Getter;

@Getter
public class MemoDTO {

    private String contents;

    private String userName;

    public MemoDTO(String contents, String userName) {
        this.contents = contents;
        this.userName = userName;
    }
}
