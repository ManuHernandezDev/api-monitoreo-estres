package mx.edu.ito.estres.infrastructure.adapters.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import mx.edu.ito.estres.application.ports.out.StudentRepositoryPort;
import mx.edu.ito.estres.domain.model.Student;
import mx.edu.ito.estres.infrastructure.adapters.out.persistence.entity.StudentJpaEntity;
import mx.edu.ito.estres.infrastructure.adapters.out.persistence.repository.StudentSpringRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StudentPersistenceAdapter implements StudentRepositoryPort {

    private final StudentSpringRepository studentSpringRepository;

    @Override
    public Optional<Student> findByEmail(String email) {
        Optional<StudentJpaEntity> entityOptional = studentSpringRepository.findByEmail(email);
        return entityOptional.map(this::toDomain);
    }

    @Override
    public Student save(Student studentDomain) {
        StudentJpaEntity jpaEntity = new StudentJpaEntity();
        jpaEntity.setEmail(studentDomain.email());
        jpaEntity.setPassword(studentDomain.password());
        jpaEntity.setOrigin(studentDomain.origin());
        jpaEntity.setPrivacyAccepted(studentDomain.privacyAccepted());
        jpaEntity.setSemester(studentDomain.semester());
        jpaEntity.setSleepHours(studentDomain.sleepHours());
        jpaEntity.setRole(studentDomain.role());

        StudentJpaEntity savedEntity = studentSpringRepository.save(jpaEntity);
        return toDomain(savedEntity);
    }

    private Student toDomain(StudentJpaEntity entity) {
        return new Student(
                entity.getId(), entity.getEmail(), entity.getPassword(),
                entity.getSemester(), entity.getOrigin(), entity.getSleepHours(), entity.getPrivacyAccepted(),
                entity.getRole()
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