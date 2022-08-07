package com.achintha.datajpa.repository;

import com.achintha.datajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByFirstNameContaining(String firstName);
    List<Student> findByFirstName(String firstName);
    List<Student> findByLastNameNotNull();
    List<Student> findByGuardianName(String guardianName);

    @Query("select s.firstName from Student s where s.email=?1")
    String getStudentFirstNameByEmailAddress(String email);

    //native querries
    @Query(
            nativeQuery = true,
            value = "select * from tbl_student s where s.email=?1"
    )
    Student getStudentByEmailAddressNative(String email);

    //with named params
    @Query(
            nativeQuery = true,
            value = "select * from tbl_student s where s.email=:email"
    )
    Student getStudentByEmailAddressNamedParams(@Param("email") String email);


    // for updating tables
    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "update tbl_student set first_name = ?1 where email= ?2"
    )
    int updateStudentNameNative(String firstName,String emailId);

}