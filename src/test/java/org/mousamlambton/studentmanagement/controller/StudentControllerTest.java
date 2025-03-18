package org.mousamlambton.studentmanagement.controller;

import org.mousamlambton.studentmanagement.model.Student;
import org.junit.jupiter.api.Test;
import org.mousamlambton.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Test
    public void shouldReturnStudentListPage() throws Exception {

        // Getting students should return a list of students
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("students"))
                .andExpect(model().attributeExists("students"));
    }

    @Test
    public void shouldReturnErrorsWhenSavingInvalidStudent() throws Exception {
        // Trying to create student with invalid data should return errors
        mockMvc.perform(post("/students/save")
                .param("name", "J")
                .param("email", "invalid-email")
                .param("age", "16"))
                .andExpect(status().isOk())
                .andExpect(view().name("new-student"))
                .andExpect(model().attributeHasErrors("student"))
                .andExpect(model().attributeHasFieldErrors("student", "name"))
                .andExpect(model().attributeHasFieldErrors("student", "email"))
                .andExpect(model().attributeHasFieldErrors("student", "age"));
    }
}