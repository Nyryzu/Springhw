package com.example.springhw.controller.rest;

import com.example.springhw.Constants;
import com.example.springhw.entities.Subject;
import com.example.springhw.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private SubjectService subjectService;
    public  SubjectController(@Autowired SubjectService subjectService){
        this.subjectService=subjectService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Subject> findById(Long id){
        Subject sub = subjectService.getById(id);
        return sub==null ? ResponseEntity.notFound().build() : ResponseEntity.ok(sub) ;
    }
    @GetMapping("/all")
    public ResponseEntity<Page<Subject>> findAll(@RequestParam(name = "page",defaultValue = "0") int page,
                                                 @RequestParam(name = "size",defaultValue = "5") int size){
        Pageable paging = PageRequest.of(page, size);
        Page<Subject> p = subjectService.getPaginated(paging);
        return ResponseEntity.ok(p);
    }

    @PostMapping(consumes = Constants.MIME_JSON)
    public ResponseEntity<Subject> addSubject(@RequestBody Subject s) {
        this.subjectService.saveSubject(s);
        return ResponseEntity.ok(s);
    }

    @PutMapping(name = "/{id}", consumes = Constants.MIME_JSON)
    public ResponseEntity<Subject> updateSubject(@RequestBody Subject s, @PathVariable long id) {
        Subject sub = subjectService.getById(id);
        if (sub!=null) {
            sub.setName(s.getName());
            sub.setRegisteredStudents(s.getRegisteredStudents());
            return ResponseEntity.ok(this.subjectService.saveSubject(sub));
        }
        s.setId(id);
        return ResponseEntity.ok(this.subjectService.saveSubject(s));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        Subject s = this.subjectService.getById(id);
        if (s==null)
            return ResponseEntity.notFound().build();
        this.subjectService.delete(id);
        return ResponseEntity.ok().build();
    }
}
