package mx.edu.ito.estres.application.usecases;

import mx.edu.ito.estres.application.ports.out.EvaluationRepositoryPort;
import mx.edu.ito.estres.domain.model.StressLevel;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.request.EvaluationRequest;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.response.EvaluationResponse;

import mx.edu.ito.estres.domain.model.EvaluationResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SubmitEvaluationUseCase {

    private final EvaluationRepositoryPort evaluationRepositoryPort;

    public SubmitEvaluationUseCase(
            EvaluationRepositoryPort evaluationRepositoryPort
    ) {
        this.evaluationRepositoryPort = evaluationRepositoryPort;
    }

    public EvaluationResponse submit(
            Long studentId,
            EvaluationRequest request
    ) {

        int siscoScore = request.getSiscoAnswers()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        int mbiScore = request.getMbiAnswers()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        int total = siscoScore + mbiScore;

        StressLevel level;

        if(total < 40) {
            level = StressLevel.LOW;
        } else if(total < 70) {
            level = StressLevel.MODERATE;
        } else {
            level = StressLevel.CRITICAL;
        }

        EvaluationResult result = new EvaluationResult(
                null,
                studentId,
                siscoScore,
                mbiScore,
                level,
                LocalDateTime.now()
        );

        evaluationRepositoryPort.save(result);

        return EvaluationResponse.builder()
                .siscoScore(siscoScore)
                .mbiScore(mbiScore)
                .stressLevel(level)
                .recommendation(getRecommendation(level))
                .build();
    }

    private String getRecommendation(StressLevel level) {

        return switch (level) {
            case LOW -> "Estrés bajo. Continúa con hábitos saludables.";
            case MODERATE -> "Estrés moderado. Considera descansar y organizar tus actividades.";
            case CRITICAL -> "Estrés crítico. Busca apoyo psicológico y utiliza herramientas de ayuda.";
        };
    }
}