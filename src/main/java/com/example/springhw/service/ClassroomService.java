package com.example.springhw.service;


import com.example.springhw.entities.Classroom;
import com.example.springhw.entities.Student;
import com.example.springhw.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {

    private ClassroomRepository repository;

    public ClassroomService(@Autowired ClassroomRepository repository){
        this.repository=repository;
    }

    public List<Classroom> getAllClassrooms () {
        return this.repository.findAll();
    }

    public Classroom getById(long id){
        return this.repository.getById(id);
    }

    public Page<Classroom> getPaginated(Pageable page){
        return repository.findAll(page);
    }

    public Classroom saveClassroom(Classroom classroom){
        if (classroom.getId()!=null){
            Classroom cl = this.repository.getById(classroom.getId());
            if(cl==null)
                return this.repository.save(classroom);
            return this.repository.save(cl);
        }
        return classroom==null ? null : this.repository.save(classroom);
    }
}
