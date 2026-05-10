package mx.edu.ito.estres.application.ports.out;

import mx.edu.ito.estres.infrastructure.adapters.out.persistence.entity.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationJpaRepository
        extends JpaRepository<EvaluationEntity, Long> {
}
