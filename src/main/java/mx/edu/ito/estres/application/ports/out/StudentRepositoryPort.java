package mx.edu.ito.estres.application.port.out;

import java.util.Optional;

import mx.edu.ito.estres.domain.model.Student;

public interface StudentRepositoryPort {
    Optional<Student> findByEmail(String email);

    Student save(Student student);
}
