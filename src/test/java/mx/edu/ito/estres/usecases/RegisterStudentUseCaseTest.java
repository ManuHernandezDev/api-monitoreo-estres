package mx.edu.ito.estres.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.edu.ito.estres.application.ports.out.StudentRepositoryPort;
import mx.edu.ito.estres.application.usecases.RegisterStudentUseCase;
import mx.edu.ito.estres.domain.model.Student;

@ExtendWith(MockitoExtension.class)
public class RegisterStudentUseCaseTest {
    @Mock
    private StudentRepositoryPort studentRepositoryPort;

    @InjectMocks
    private RegisterStudentUseCase registerStudentUseCase;

    @Test
    void shoueldRegisterStudentSuccessfully() {
        // Given: An Student Valid that dont exist in database
        Student newStudent = new Student(null, "manu@itoaxaca.edu.mx", "rawPassword123", 4, "Local", 6);

        // Simulated The port of database dont find the email
        when(studentRepositoryPort.findByEmail("manu@itoaxaca.edu.mx")).thenReturn(Optional.empty());

        // Simulated that saved, the database set ID 1
        Student savedStudent = new Student(1L, "manu@itoaxaca.edu.mx", "hashedPassword", 4, "Local", 6);
        when(studentRepositoryPort.save(any(Student.class))).thenReturn(savedStudent);

        // When Execute the use case
        Student result = registerStudentUseCase.register(newStudent);

        // Then We checked what called the port save exactly once
        verify(studentRepositoryPort, times(1)).save(any(Student.class));

        assertNotNull(result, "El resultado no deberia de ser nulo");
        assertEquals(1L, result.id(), "El ID del estudiante registrado deberia ser 1");
        assertEquals("manu@itoaxaca.edu.mx", result.email(), "El email debe coincidir");
        assertNotNull(result.password(), "El password no debe estar vacio");

    }
}