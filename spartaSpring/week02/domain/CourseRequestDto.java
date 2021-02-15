package com.sparta.week2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
//final이 붙은 멤버변수를 매개변수로 포함한 생성자를 생성해준다.
@RequiredArgsConstructor //-> public CourseRequestDto(String title, String tutor){this.title = title; this.tytor = tutor;}
public class CourseRequestDto {

    private final String title;

    private final String tutor;
}
