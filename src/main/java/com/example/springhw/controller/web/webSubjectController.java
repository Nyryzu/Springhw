package com.example.springhw.controller.web;

import com.example.springhw.entities.Student;
import com.example.springhw.entities.Subject;
import com.example.springhw.service.StudentService;
import com.example.springhw.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class webSubjectController {

    private SubjectService subjectService;

    @Autowired
    private StudentService studentService;

    public webSubjectController(SubjectService subjectService){
        this.subjectService=subjectService;
    }

    @GetMapping("/subjects")
    public String listSubjects(Model model){
        model.addAttribute("subjects",subjectService.getAllSubjects());
        for (Subject subject: this.subjectService.getAllSubjects()) {
            model.addAttribute("studentList",this.subjectService.getRegisteredStudents(subject));
        }
        return "subjects";
    }

    @GetMapping("/subjects/new")
    public String createSubjectForm(Model model,@PathVariable Long id){
        Subject subject = new Subject() ;
        model.addAttribute("subject",subject);
        model.addAttribute("studentList",this.subjectService.getRegisteredStudents(subject));

    return "create_subject";
    }

    @PostMapping("/subjects")
    public String saveSubject(@ModelAttribute("subject") Subject subject){
        subjectService.saveSubject(subject);
        return "redirect/subjects";
    }

    @GetMapping("/subjects/edit/{id}")
    public String editSubjectForm(@PathVariable Long id, Model model){
        model.addAttribute("subject",subjectService.getById(id));
        return "edit_subject";
    }

    @PostMapping("/subjects/{id}")
    public String updateSubject(@PathVariable Long id,@ModelAttribute("subject") Subject subject, Model model){
        Subject existingSubject = subjectService.getById(id);
        existingSubject.setId(subject.getId());
        existingSubject.setName(subject.getName());
        subjectService.saveSubject(existingSubject);
        return "redirect:/subjects";
    }
    @GetMapping("/subjects/{id}")
    public String deleteSubject(@PathVariable Long id){
        subjectService.delete(id);
        return "redirect:/subjects";
    }
}
