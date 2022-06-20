package com.example.springhw.repositories;


import com.example.springhw.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Student findByNeptunCode(String nc);

}
