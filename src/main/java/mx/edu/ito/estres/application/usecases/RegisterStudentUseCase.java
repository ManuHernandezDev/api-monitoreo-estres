package mx.edu.ito.estres.application.usecases;

import mx.edu.ito.estres.domain.exception.EmailAlreadyExistsException;
import mx.edu.ito.estres.application.ports.out.StudentRepositoryPort;
import mx.edu.ito.estres.domain.model.Student;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.request.StudentRequestDTO;
import mx.edu.ito.estres.infrastructure.adapters.in.web.mapper.StudentMapper;
import mx.edu.ito.estres.infrastructure.config.PasswordEncoderConfig;

public class RegisterStudentUseCase {
    private final StudentRepositoryPort studentRepositoryPort;
    private final PasswordEncoderConfig passwordEncoderConfig;

    public RegisterStudentUseCase(StudentRepositoryPort studentRepositoryPort, PasswordEncoderConfig passwordEncoderConfig) {
        this.studentRepositoryPort = studentRepositoryPort;
        this.passwordEncoderConfig = passwordEncoderConfig;
    }

    public void register(StudentRequestDTO studentRequestDTO) {
        if (studentRepositoryPort.findByEmail(studentRequestDTO.email()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }
        String encodedPassword = passwordEncoderConfig.passwordEncoder().encode(studentRequestDTO.password());

        Student studentToSave = new Student(
                null,
                studentRequestDTO.email(),
                encodedPassword,
                studentRequestDTO.semester(),
                studentRequestDTO.origin(),
                studentRequestDTO.sleepHours(),
                studentRequestDTO.privacyAccepted(),
                studentRequestDTO.role()
        );
        Student studentSaved = studentRepositoryPort.save(studentToSave);
        StudentMapper.toDomain(studentSaved);
    }
}
