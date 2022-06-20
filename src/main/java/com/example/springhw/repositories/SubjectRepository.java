package com.example.springhw.repositories;


import com.example.springhw.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

        Subject findByName(String name);

}
