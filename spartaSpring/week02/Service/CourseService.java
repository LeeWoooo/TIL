package com.sparta.week2.Service;

import com.sparta.week2.domain.Course;
import com.sparta.week2.domain.CourseRepository;
import com.sparta.week2.domain.CourseRequestDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service // 스프링에게 이 클래스는 서비스임을 명시
@RequiredArgsConstructor
public class CourseService {

    // final: 서비스에게 꼭 필요한 녀석임을 명시
    private final CourseRepository courseRepository;

    // 생성자를 통해, Service 클래스를 만들 때 꼭 Repository를 넣어주도록
    // 스프링에게 알려줌
    //@RequiredArgsConstructor

    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public Long update(Long id, CourseRequestDto requestDto) { //반환값이 Long인 이유는 어떠한 것이 update됬는지 id값 반환
        Course course1 = courseRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        course1.update(requestDto);
        return course1.getId();
    }
}