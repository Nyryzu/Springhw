package com.example.springhw.repositories;


import com.example.springhw.entities.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    Classroom findClassroomByClassroomId(Long id);

}
