package mx.edu.ito.estres.infrastructure.adapters.out.persistence.repository;

import mx.edu.ito.estres.infrastructure.adapters.out.persistence.entity.EmergencyContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmergencyContactJpaRepository
        extends JpaRepository<EmergencyContactEntity, Long> {

    List<EmergencyContactEntity> findByStudentId(Long studentId);
}