package mx.edu.ito.estres.infrastructure.adapters.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import mx.edu.ito.estres.application.ports.out.StudentRepositoryPort;
import mx.edu.ito.estres.domain.model.Student;
import mx.edu.ito.estres.infrastructure.adapters.out.persistence.entity.StudentJpaEntity;
import mx.edu.ito.estres.infrastructure.adapters.out.persistence.repository.StudentSpringRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component // Le decimos a Spring que este es un empleado disponible
@RequiredArgsConstructor
public class StudentPersistenceAdapter implements StudentRepositoryPort {

    private final StudentSpringRepository springRepository;

    @Override
    public Optional<Student> findByEmail(String email) {
        // 1. Busca en la base de datos
        Optional<StudentJpaEntity> entityOptional = springRepository.findByEmail(email);

        // 2. Si lo encuentra, lo traduce de JpaEntity a Domain Student
        return entityOptional.map(this::toDomain);
    }

    @Override
    public Student save(Student student) {
        // 1. Traduce de Domain Student a JpaEntity
        StudentJpaEntity entityToSave = toEntity(student);

        // 2. Lo guarda en PostgreSQL
        StudentJpaEntity savedEntity = springRepository.save(entityToSave);

        // 3. Lo regresa traducido al dominio para que el Chef lo entienda
        return toDomain(savedEntity);
    }

    // --- Métodos Mappers (Traductores) ---
    // En producción usamos MapStruct, pero a mano se ve más claro cómo funciona
    private Student toDomain(StudentJpaEntity entity) {
        return new Student(
                entity.getId(), entity.getEmail(), entity.getPassword(),
                entity.getSemester(), entity.getOrigin(), entity.getSleepHours(), entity.getPrivacyAccepted()
        );
    }

    private StudentJpaEntity toEntity(Student student) {
        return StudentJpaEntity.builder()
                .id(student.id())
                .email(student.email())
                .password(student.password())
                .semester(student.semester())
                .origin(student.origin())
                .sleepHours(student.sleepHours())
                .privacyAccepted(student.privacyAccepted())
                .build();
    }
}