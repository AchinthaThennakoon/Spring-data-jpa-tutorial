package com.achintha.datajpa.repository;

import com.achintha.datajpa.entity.Guardian;
import com.achintha.datajpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student=Student.builder()
                .firstName("achintha")
                .lastName("thennakoon")
                .email("achintha@gmail.com")
//                .guardianName("thennakoon")
//                .guardianEmail("guardian@gmail.com")
//                .guardianMobile("077432512")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){

        Guardian guardian= Guardian.builder()
                .name("thennakoon")
                .email("guardian@gmail.com")
                .mobile("077432512")
                .build();

        Student student = Student.builder()
                .firstName("achintha")
                .lastName("thennakoon")
                .email("achintha97@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }



    @Test
    public void printAllStudent(){
        List<Student> studentList = studentRepository.findAll();

        System.out.println("student list = "+studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> studentList = studentRepository.findByFirstNameContaining("achi");
        System.out.println(studentList);
    }

    @Test
    public void printGetStudentByEmail(){
        String student= studentRepository.getStudentFirstNameByEmailAddress("achintha@gmail.com");
        System.out.println(student);
    }

    @Test
    public void printGetStudentByEmailNative(){
        Student student= studentRepository.getStudentByEmailAddressNamedParams("achintha@gmail.com");
        System.out.println(student);
    }

    @Test
    public void updateStudent(){
        studentRepository.updateStudentNameNative(
                "chammika",
                "achintha97@gmail.com"
        );

    }
}