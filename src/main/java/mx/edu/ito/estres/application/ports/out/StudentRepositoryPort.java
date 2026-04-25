package mx.edu.ito.estres.application.ports.out;

import java.util.Optional;

import mx.edu.ito.estres.domain.model.Student;

public interface StudentRepositoryPort {
    Optional<Student> findByEmail(String email);

    Student save(Student student);
}
