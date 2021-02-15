package com.sparta.week2.controller;

import com.sparta.week2.Service.CourseService;
import com.sparta.week2.domain.Course;
import com.sparta.week2.domain.CourseRepository;
import com.sparta.week2.domain.CourseRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor //CourseRepository를 포함한 생성자
@RestController//json으로 응답하는 자동응답기
public class CourseController {

    private final CourseRepository courseRepository;

    private final CourseService courseService;


    @DeleteMapping("/api/courses/{id}")
    //PathVariable를 쓰는 이유는 요청이 온 URL에서 id값을 가져오기 위함이다.
    public Long deleteCourse(@PathVariable Long id){
        courseRepository.deleteById(id);
        return id;
    }

    @PutMapping("/api/courses/{id}") //{id} 는 id값이 오는데 유동적으로 온다.
    public Long updateCourse(@PathVariable Long id, @RequestBody CourseRequestDto requestDto) {
        return courseService.update(id, requestDto);
    }


    // PostMapping을 통해서, 같은 주소라도 방식이 다름을 구분합니다.
    @PostMapping("/api/courses")
    public Course createCourse(@RequestBody CourseRequestDto requestDto) {
        // requestDto 는, 생성 요청을 의미합니다.
        // 강의 정보를 만들기 위해서는 강의 제목과 튜터 이름이 필요하잖아요?
        // 그 정보를 가져오는 녀석입니다.

        // 저장하는 것은 Dto가 아니라 Course이니, Dto의 정보를 course에 담아야 합니다.
        // 잠시 뒤 새로운 생성자를 만듭니다.
        Course course = new Course(requestDto);

        // JPA를 이용하여 DB에 저장하고, 그 결과를 반환합니다.
        return courseRepository.save(course);
    }

    @GetMapping("/api/courses") //get방식으로 요청이 오면
    public List<Course> getCourses() { //DB를 조회해서 list를 반환해라
        return courseRepository.findAll();
    }
}
