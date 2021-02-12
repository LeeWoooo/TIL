package com.sparta.week01.prac;

public class Prac {
    public static void main(String[] args) {
        System.out.println("안녕, 스프링:)");

        Course course = new Course();
        course.setTitle("웹개발의 봄, spring");
        course.setTutor("남병관");
        System.out.println(course.getTitle());
        System.out.println(course.getTutor());

    }
}
