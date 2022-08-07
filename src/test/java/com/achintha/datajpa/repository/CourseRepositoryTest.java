package com.achintha.datajpa.repository;

import com.achintha.datajpa.entity.Course;
import com.achintha.datajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void printAllCourse(){
        List<Course> c = courseRepository.findAll();
        System.out.println(c);
    }

    @Test
    public void saveWithTeacher(){
        Teacher teacher= Teacher.builder()
                .LastName("nimal")
                .firstName("perera")
                .build();

        Course course= Course.builder()
                .title("AI")
                .credit(4)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

}