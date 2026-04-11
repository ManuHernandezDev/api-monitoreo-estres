package mx.edu.ito.estres.infraestructure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mx.edu.ito.estres.application.RegisterStudentUseCase;
import mx.edu.ito.estres.application.dto.StudentRequestDTO;
import mx.edu.ito.estres.domain.model.Student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final RegisterStudentUseCase registerStudentUseCase;

    @PostMapping("/register")
    public ResponseEntity<String> registerStudent(@RequestBody StudentRequestDTO studentRequestDTO) {

        Student student = new Student(
                null,
                studentRequestDTO.email(),
                studentRequestDTO.password(),
                studentRequestDTO.semester(),
                studentRequestDTO.origin(),
                studentRequestDTO.sleepHours());

        registerStudentUseCase.register(student);

        return ResponseEntity.status(HttpStatus.CREATED).body("Student registered successfully");
    }

}
