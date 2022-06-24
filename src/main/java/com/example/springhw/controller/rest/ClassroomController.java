package com.example.springhw.controller.rest;

import com.example.springhw.entities.Classroom;
import com.example.springhw.entities.Student;
import com.example.springhw.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/controller")
public class ClassroomController {
    @Autowired
    private ClassroomService service;

    private ClassroomController(@Autowired ClassroomService service){this.service=service;}
    @GetMapping("/{classroomId}")
    public ResponseEntity<Classroom> findById(long id, @PathVariable String classroomId){
        Classroom c= service.getById(id) ;
        return c == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(c) ;
    }
    @GetMapping("/all")

    public ResponseEntity<Page<Classroom>> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                   @RequestParam(name = "size", defaultValue = "5") int size) {
        Pageable paging= PageRequest.of(page,size);
        Page<Classroom> l=service.getPaginated(paging);
        return ResponseEntity.ok(l);
    }


}
