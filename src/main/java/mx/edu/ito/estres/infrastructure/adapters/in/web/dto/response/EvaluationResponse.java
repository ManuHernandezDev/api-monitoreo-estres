package mx.edu.ito.estres.infrastructure.adapters.in.web.dto.response;

import lombok.Builder;
import lombok.Getter;
import mx.edu.ito.estres.domain.model.StressLevel;

@Getter
@Builder
public class EvaluationResponse {

    private Integer siscoScore;
    private Integer mbiScore;
    private StressLevel stressLevel;
    private String recommendation;
}