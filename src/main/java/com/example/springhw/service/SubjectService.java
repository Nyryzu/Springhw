package com.example.springhw.service;


import com.example.springhw.entities.Subject;
import com.example.springhw.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class SubjectService {

        private SubjectRepository repository;

        public SubjectService(@Autowired SubjectRepository repository){
            this.repository=repository;

        }

        public List<Subject> getAllSubjects(){
            return this.repository.findAll();
        }

        public Subject getById(long id){
            return this.repository.getById(id);
        }

        public Subject saveSubject(Subject subject){
            if (subject.getId()!=null){
                Subject sb = this.repository.getById(subject.getId());
                if(sb==null)
                    return this.repository.save(subject);
                sb.setName(subject.getName());
                return this.repository.save(sb);
            }
            return subject==null ? null : this.repository.save(subject);
        }

        public void delete(Subject sb){
            this.repository.delete(sb);
        }

        public Page<Subject> getPaginated(Pageable page){
            return repository.findAll(page);
        }
    }

