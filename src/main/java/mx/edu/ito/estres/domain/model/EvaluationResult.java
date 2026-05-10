package mx.edu.ito.estres.domain.model;

import java.time.LocalDateTime;

public record EvaluationResult(
        Long id,
        Long studentId,
        Integer siscoScore,
        Integer mbiScore,
        StressLevel stressLevel,
        LocalDateTime createdAt
) {
}
