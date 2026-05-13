package mx.edu.ito.estres.infrastructure.adapters.in.web;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mx.edu.ito.estres.application.usecases.RegisterStudentUseCase;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.request.StudentRequestDTO;

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
    public ResponseEntity<String> registerStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        registerStudentUseCase.register(studentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student registered successfully");
    }

}
