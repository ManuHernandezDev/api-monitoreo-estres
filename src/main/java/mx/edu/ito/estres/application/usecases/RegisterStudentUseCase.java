package mx.edu.ito.estres.application.usecases;

import java.util.Optional;

import org.springframework.stereotype.Service;

import mx.edu.ito.estres.application.ports.out.StudentRepositoryPort;
import mx.edu.ito.estres.domain.model.Student;

@Service
public class RegisterStudentUseCase {
    private final StudentRepositoryPort studentRepositoryPort;

    public RegisterStudentUseCase(StudentRepositoryPort studentRepositoryPort) {
        this.studentRepositoryPort = studentRepositoryPort;
    }

    public Student register(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        // Rule Businness, email exists?
        Optional<Student> existingStudent = studentRepositoryPort.findByEmail(student.email());
        if (existingStudent.isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        return studentRepositoryPort.save(student);

    }
}
