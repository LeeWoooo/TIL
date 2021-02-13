package com.sparta.week01.controller;

import com.sparta.week01.prac.Course;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    //mapping명으로 요청이 오면 지금 현재의 course 객체를 Json형태로 응답해준다.
    //이 method의 실행은 요청이 오면 spring이 해준다.
    @GetMapping("/courses")
    public Course getCourse(){
        Course course = new Course();
        course.setTitle("웹개발의 봄 스프링");
        course.setTutor("남병관");
        course.setDays(35);
        return course;
    }
}
