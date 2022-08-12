package com.achintha.datajpa.repository;

import com.achintha.datajpa.entity.Course;
import com.achintha.datajpa.entity.Student;
import com.achintha.datajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        long totalElements = courseRepository
                .findAll(secondPageWithTwo)
                .getTotalElements();

        System.out.println("Total pages :"+totalPages);
        System.out.println("records :"+courses);
    }

    @Test
    @Transactional
    public void findAllWithSorting(){
        Pageable sortByTile =
                PageRequest.of(0,
                        2,
                        Sort.by("title")
                );

        Pageable sortByCreditDesc =
                PageRequest.of(0,
                        2,
                        Sort.by("credit").descending()
                );

        Pageable sortByTileAndCreditDesc =
                PageRequest.of(0,
                        4,
                        Sort.by("title")
                                .and(Sort.by("credit").descending())
                );

        List<Course> courses = courseRepository
                .findAll(sortByTileAndCreditDesc)
                .getContent();

        System.out.println(courses);
    }

    //test custom sorting
    @Test
    @Transactional
    public void printFindByTitleContaining(){
        Pageable firstPageTenRecords =
                PageRequest.of(0,10);

        List<Course> courses=
                courseRepository.findByTitleContaining(
                        "D",
                        firstPageTenRecords
                ).getContent();

        System.out.println("Courses :"+ courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("nimal")
                .LastName("Perera")
                .build();

        Student student =Student.builder()
                .firstName("jhonny")
                .lastName("depp")
                .email("test123@gmail.com")
                .build();

        Course course = Course.builder()
                .teacher(teacher)
                .credit(2)
                .title("CM")
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }

}