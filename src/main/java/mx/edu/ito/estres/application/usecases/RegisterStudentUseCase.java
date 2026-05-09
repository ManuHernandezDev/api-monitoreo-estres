package mx.edu.ito.estres.application.usecases;

import java.util.Optional;

import mx.edu.ito.estres.domain.exception.EmailAlreadyExistsException;
import mx.edu.ito.estres.application.ports.out.StudentRepositoryPort;
import mx.edu.ito.estres.domain.exception.InvalidStudentException;
import mx.edu.ito.estres.domain.model.Student;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.StudentRequestDTO;
import mx.edu.ito.estres.infrastructure.config.PasswordEncoderConfig;

public class RegisterStudentUseCase {
    private final StudentRepositoryPort studentRepositoryPort;
    private final PasswordEncoderConfig passwordEncoderConfig;

    public RegisterStudentUseCase(StudentRepositoryPort studentRepositoryPort, PasswordEncoderConfig passwordEncoderConfig) {
        this.studentRepositoryPort = studentRepositoryPort;
        this.passwordEncoderConfig = passwordEncoderConfig;
    }

    public Student register(StudentRequestDTO studentRequestDTO) {
        // 1. Validaciones (Ya las hace tu constructor de Record, ¡bien!)

        // 2. Verificar duplicados
        if (studentRepositoryPort.findByEmail(studentRequestDTO.email()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }
        String encodedPassword = passwordEncoderConfig.passwordEncoder().encode(studentRequestDTO.password());

        Student studentToSave = new Student(
                null, // El ID va null porque lo genera la DB
                studentRequestDTO.email(),
                encodedPassword, // Guardamos la encriptada
                studentRequestDTO.semester(),
                studentRequestDTO.origin(),
                studentRequestDTO.sleepHours(),
                studentRequestDTO.privacyAccepted(),
                studentRequestDTO.role() // <--- ASEGÚRATE DE PASAR EL ROL AQUÍ
        );

        return studentRepositoryPort.save(studentToSave);
    }
}
