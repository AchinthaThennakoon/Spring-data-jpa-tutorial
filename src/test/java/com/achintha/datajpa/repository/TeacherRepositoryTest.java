package com.achintha.datajpa.repository;

import com.achintha.datajpa.entity.Course;
import com.achintha.datajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){
        Course course1 = Course.builder()
                .credit(2)
                .title("DB")
                .build();

        Course course2 = Course.builder()
                .credit(2)
                .title("Stat")
                .build();

        Teacher teacher= Teacher.builder()
                .firstName("Jhon")
                .LastName("keels")
                .courses(List.of(course1,course2))
                .build();

        teacherRepository.save(teacher);
    }
}