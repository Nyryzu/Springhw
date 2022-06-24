package com.example.springhw.controller.web;

import com.example.springhw.entities.Student;
import com.example.springhw.service.ClassroomService;
import com.example.springhw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class webStudentController {

    public StudentService studentService;
    @Autowired
    public ClassroomService classroomService ;

    public webStudentController(StudentService studentService){
        this.studentService = studentService ;
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students",studentService.getAllStudent());
        return "students";
    }
    @GetMapping("/students/new")
    public String createStudentForm(Model model){
        Student student = new Student();
        model.addAttribute("student",student);
        model.addAttribute("classrooms",classroomService.getAllClassrooms());
        return "create_student";
    }
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }
    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model){
        model.addAttribute("student",studentService.getById(id));
        model.addAttribute("classrooms",classroomService.getAllClassrooms());
        return "edit_student" ;
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,@ModelAttribute("student") Student student,Model model){
        Student existingStudent = studentService.getById(id);
        existingStudent.setId(student.getId());
        existingStudent.setNeptunCode(student.getNeptunCode());
        existingStudent.setName(student.getName());
        studentService.saveStudent(existingStudent);
        return "redirect:/students";
    }
    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteById(id);
        return "redirect:/students";
    }
}
