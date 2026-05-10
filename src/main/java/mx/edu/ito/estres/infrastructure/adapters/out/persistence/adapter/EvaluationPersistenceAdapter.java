package mx.edu.ito.estres.infrastructure.adapters.out.persistence.adapter;


import mx.edu.ito.estres.application.ports.out.EvaluationJpaRepository;
import mx.edu.ito.estres.application.ports.out.EvaluationRepositoryPort;
import mx.edu.ito.estres.domain.model.EvaluationResult;
import mx.edu.ito.estres.infrastructure.adapters.out.persistence.entity.EvaluationEntity;
import org.springframework.stereotype.Component;

@Component
public class EvaluationPersistenceAdapter implements EvaluationRepositoryPort {

    private final EvaluationJpaRepository evaluationJpaRepository;

    public EvaluationPersistenceAdapter(
            EvaluationJpaRepository evaluationJpaRepository
    ) {
        this.evaluationJpaRepository = evaluationJpaRepository;
    }

    @Override
    public EvaluationResult save(EvaluationResult result) {

        EvaluationEntity entity = new EvaluationEntity();

        entity.setStudentId(result.studentId());
        entity.setSiscoScore(result.siscoScore());
        entity.setMbiScore(result.mbiScore());
        entity.setStressLevel(result.stressLevel());
        entity.setCreatedAt(result.createdAt());

        EvaluationEntity savedEntity =
                evaluationJpaRepository.save(entity);

        return new EvaluationResult(
                savedEntity.getId(),
                savedEntity.getStudentId(),
                savedEntity.getSiscoScore(),
                savedEntity.getMbiScore(),
                savedEntity.getStressLevel(),
                savedEntity.getCreatedAt()
        );
    }


}
