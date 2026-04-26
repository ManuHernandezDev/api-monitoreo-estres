package mx.edu.ito.estres.application.usecases;

import java.util.Optional;

import mx.edu.ito.estres.domain.exception.EmailAlreadyExistsException;
import mx.edu.ito.estres.application.ports.out.StudentRepositoryPort;
import mx.edu.ito.estres.domain.exception.InvalidStudentException;
import mx.edu.ito.estres.domain.model.Student;

public class RegisterStudentUseCase {
    private final StudentRepositoryPort studentRepositoryPort;

    public RegisterStudentUseCase(StudentRepositoryPort studentRepositoryPort) {
        this.studentRepositoryPort = studentRepositoryPort;
    }

    public Student register(Student student) {
        if (student == null) {
            throw new InvalidStudentException("Student cannot be null");
        }
        Optional<Student> existingStudent = studentRepositoryPort.findByEmail(student.email());
        if (existingStudent.isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        return studentRepositoryPort.save(student);

    }
}
