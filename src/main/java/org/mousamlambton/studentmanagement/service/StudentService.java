package org.mousamlambton.studentmanagement.service;

import org.mousamlambton.studentmanagement.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// Marking service annotation for dependency injection
@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    // Generate unique IDs for students
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    // Get all students
    public List getAllStudents() {
        return students;
    }

    // Add a new student
    public Student addStudent(Student student) {
        student.setId(idGenerator.getAndIncrement());
        students.add(student);
        return student;
    }

    // Delete a student by ID
    public boolean deleteStudent(Integer id) {
        return students.removeIf(student -> student.getId().equals(id));
    }
}