package com.example.springhw.controller.rest;

import com.example.springhw.Constants;
import com.example.springhw.entities.Student;
import com.example.springhw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService service;

    public StudentController(@Autowired StudentService service){
        this.service=service;
    }
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> findById(Long id, @PathVariable String studentId) {
        Student s = service.getById(id);
        return  s ==null ? ResponseEntity.notFound().build() : ResponseEntity.ok(s);
    }

    @GetMapping("/all")

    public ResponseEntity<Page<Student>> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name = "size", defaultValue = "5") int size) {
        Pageable paging= PageRequest.of(page,size);
        Page<Student> l=service.getPaginated(paging);
        return ResponseEntity.ok(l);
    }

    @PostMapping(consumes = Constants.MIME_JSON)
    public ResponseEntity<Student> addStudent(@RequestBody Student s) {
        this.service.saveStudent(s);
        return ResponseEntity.ok(s);
    }

    @PutMapping(name = "/{studentId}", consumes = Constants.MIME_JSON)
    public ResponseEntity<Student> updateStudent(@RequestBody Student s, @PathVariable long studentId) {
        Student st=service.getById(studentId);
        if (st!=null) {
            st.setName(s.getName());
            st.setNeptunCode(s.getNeptunCode());
            return ResponseEntity.ok(this.service.saveStudent(st));
        }
        //s.setName(s.getName());
        return ResponseEntity.ok(this.service.saveStudent(s));
    }

    @DeleteMapping("/{studentId}")
    ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        Student s=this.service.getById(studentId);
        if (s==null)
            return ResponseEntity.notFound().build();
        this.service.deleteById(studentId);
        return ResponseEntity.ok().build();
    }
}


