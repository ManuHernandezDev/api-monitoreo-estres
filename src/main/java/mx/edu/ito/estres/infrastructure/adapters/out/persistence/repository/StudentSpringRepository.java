package mx.edu.ito.estres.infrastructure.adapters.out.persistence.repository;

import mx.edu.ito.estres.infrastructure.adapters.out.persistence.entity.StudentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentSpringRepository extends JpaRepository<StudentJpaEntity, Long> {
    // Spring Boot escribe el 'SELECT * FROM students WHERE email = ?' por ti
    Optional<StudentJpaEntity> findByEmail(String email);
}