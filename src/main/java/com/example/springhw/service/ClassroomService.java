package com.example.springhw.service;


import com.example.springhw.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {
    private ClassroomRepository repository;

    public ClassroomService(@Autowired ClassroomRepository repository){
        this.repository=repository;
    }
}
