package com.achintha.datajpa.repository;

import com.achintha.datajpa.entity.Course;
import com.achintha.datajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

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
                .title("AI2")
                .credit(4)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    @Transactional
    public void findPagination(){
        //define pageable objects
        Pageable firstPageWithThree =
                PageRequest.of(0,3);
        Pageable secondPageWithTwo =
                PageRequest.of(1,2);

        List<Course> courses =
                courseRepository
                        .findAll(secondPageWithTwo)
                        .getContent();

        long totalPages = courseRepository
                .findAll(secondPageWithTwo)
                        .getTotalPages();

        System.out.println("Total pages :"+totalPages);
        System.out.println("records :"+courses);
    }

}