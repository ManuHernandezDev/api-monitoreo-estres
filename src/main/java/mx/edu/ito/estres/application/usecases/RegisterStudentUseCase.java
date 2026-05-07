package mx.edu.ito.estres.application.usecases;

import java.util.Optional;

import mx.edu.ito.estres.domain.exception.EmailAlreadyExistsException;
import mx.edu.ito.estres.application.ports.out.StudentRepositoryPort;
import mx.edu.ito.estres.domain.exception.InvalidStudentException;
import mx.edu.ito.estres.domain.model.Student;
import mx.edu.ito.estres.infrastructure.config.PasswordEncoderConfig;

public class RegisterStudentUseCase {
    private final StudentRepositoryPort studentRepositoryPort;
    private final PasswordEncoderConfig passwordEncoderConfig;

    public RegisterStudentUseCase(StudentRepositoryPort studentRepositoryPort, PasswordEncoderConfig passwordEncoderConfig) {
        this.studentRepositoryPort = studentRepositoryPort;
        this.passwordEncoderConfig = passwordEncoderConfig;
    }

    public Student register(Student student) {
        if (student == null) {
            throw new InvalidStudentException("Student cannot be null");
        }
        Optional<Student> existingStudent = studentRepositoryPort.findByEmail(student.email());
        if (existingStudent.isPresent()) {
            throw new EmailAlreadyExistsException();
        }
        passwordEncoderConfig.passwordEncoder().encode(student.password());
        return studentRepositoryPort.save(student);
    }
}
