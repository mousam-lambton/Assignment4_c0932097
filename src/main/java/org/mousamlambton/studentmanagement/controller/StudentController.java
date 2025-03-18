package org.mousamlambton.studentmanagement.controller;

import org.mousamlambton.studentmanagement.model.Student;
import org.mousamlambton.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Display all students in a table
    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    // Display form to add a new student
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "new-student";
    }

    // Save new student with validation
    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "new-student";
        }

        studentService.addStudent(student);
        return "redirect:/students";
    }

    // Delete student by ID
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    // This is for use in html forms, as they don't support DELETE method directly
    @GetMapping("/delete/{id}")
    public String deleteStudentGet(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    // Return all students as JSON
    @GetMapping("/json")
    @ResponseBody
    public List<Student> getAllStudentsJson() {
        return studentService.getAllStudents();
    }
}