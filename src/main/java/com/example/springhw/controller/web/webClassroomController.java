package com.example.springhw.controller.web;

import com.example.springhw.entities.Classroom;
import com.example.springhw.entities.Student;
import com.example.springhw.repositories.StudentRepository;
import com.example.springhw.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class webClassroomController {
    private StudentRepository studentRepository;

    private ClassroomService classroomService;

    public webClassroomController(ClassroomService classroomService){
        this.classroomService = classroomService ;
    }

    @GetMapping("/classrooms")
    public String listClassrooms(Model model) {
        model.addAttribute("classrooms",classroomService.getAllClassrooms());
        return "classrooms";
    }

    @GetMapping("/classrooms/new")
    public String createClassroomForm(Model model) {
        Classroom classroom = new Classroom();
        model.addAttribute("classroom",classroom);
        return "create_classroom";
    }

    @PostMapping("/classrooms")
    public String saveCountry(@ModelAttribute("classroom") Classroom classroom) {
        classroomService.saveClassroom(classroom);
        return "redirect:/classrooms";
    }

    @GetMapping("/classrooms/edit/{id}")
    public String editClassroomForm(@PathVariable Long id,Model model) {
        model.addAttribute("classroom",classroomService.getById(id));
        return "edit_classroom";
    }

    @PostMapping("/classrooms/{id}")
    public String updateClassrooms(@PathVariable Long id, @ModelAttribute("classroom") Classroom classroom, Model model){
        Classroom existingClassroom = classroomService.getById(id);
        existingClassroom.setId(classroom.getId());
        for (Student student: this.studentRepository.findAll()
             ) {
            if (student.getClassroom().getId() == id) existingClassroom.setEnrolledStudents(student);
        }

        classroomService.saveClassroom(existingClassroom);
        return "redirect:/classrooms";
    }

}

