package mx.edu.ito.estres.application.ports.out;

import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.response.ExportRowDTO;
import mx.edu.ito.estres.infrastructure.adapters.out.persistence.entity.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EvaluationJpaRepository
        extends JpaRepository<EvaluationEntity, Long> {
    @Query("""
SELECT new mx.edu.ito.estres.infrastructure.adapters.in.web.dto.response.ExportRowDTO(
    s.id,
    s.semester,
    s.origin,
    s.sleepHours,
    e.siscoScore,
    e.mbiScore,
    e.stressLevel,
    e.createdAt
)
FROM EvaluationEntity e
JOIN StudentJpaEntity s
ON s.id = e.studentId
""")
    List<ExportRowDTO> exportData();
}
