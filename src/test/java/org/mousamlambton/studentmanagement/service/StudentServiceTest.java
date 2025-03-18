package org.mousamlambton.studentmanagement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mousamlambton.studentmanagement.model.Student;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        studentService = new StudentService();
    }

    @Test
    public void testAddStudentIncreasesListSize() {
        // Initialize a new student
        Student student = new Student();
        student.setName("Mousam Dhakal");
        student.setEmail("mousam@mylambton.com");
        student.setAge(25);

        // First the initial size of the list is stored
        int initialSize = studentService.getAllStudents().size();

        // Add test student
        studentService.addStudent(student);

        // Then the size of the list is checked again which is expected to increase by 1
        assertEquals(initialSize + 1, studentService.getAllStudents().size());
        assertNotNull(student.getId());
    }

    @Test
    public void testDeleteStudentDecreasesListSize() {
        // Initialize a new student
        Student student = new Student();
        student.setName("Mousam Dhakal");
        student.setEmail("mousam@mylambton.com");
        student.setAge(25);


        studentService.addStudent(student);

        // Initial size of the list after adding the student is stored
        int sizeAfterAdd = studentService.getAllStudents().size();
        Integer studentId = student.getId();

        // Delete the student
        boolean result = studentService.deleteStudent(studentId);

        // Check the size of the list after deleting the student
        assertTrue(result);
        assertEquals(sizeAfterAdd - 1, studentService.getAllStudents().size());
    }
}