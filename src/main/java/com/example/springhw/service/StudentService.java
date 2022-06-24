package com.example.springhw.service;



import com.example.springhw.entities.Student;
import com.example.springhw.repositories.ClassroomRepository;
import com.example.springhw.repositories.StudentRepository;
import com.example.springhw.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository repository;
    private ClassroomRepository classroomRepository;

    public StudentService(@Autowired StudentRepository repository){
        this.repository=repository;
    }

    public List<Student> getAllStudent(){
        return this.repository.findAll();
    }

    public Student getById(long id){
        return this.repository.getById(id);
    }

    public Student saveStudent(Student student){
        if (student.getId()!=null){
            Student st = this.repository.getById(student.getId());
            if(st==null)
                return this.repository.save(student);
            st.setName(student.getName());
            st.setNeptunCode(student.getNeptunCode());
            return this.repository.save(st);
        }
        return student==null ? null : this.repository.save(student);
    }

    public List<Student> getRegisteredStudentsByClassId(Long id){
        List<Student> studentList = null;
        for (Student s: this.repository.findAll())
        {
            if (s.getSubjects().contains(this.classroomRepository.getById(id)))
                studentList.add(s);
        }
        return studentList;
    }

    public void deleteById(Long id){
        this.repository.deleteById(id);
    }

    public Page<Student> getPaginated(Pageable page){
        return repository.findAll(page);
    }
}
