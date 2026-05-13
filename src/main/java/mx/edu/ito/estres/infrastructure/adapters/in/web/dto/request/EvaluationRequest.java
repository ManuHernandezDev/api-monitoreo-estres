package mx.edu.ito.estres.infrastructure.adapters.in.web.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EvaluationRequest {

    @NotEmpty(message = "SISCO answers are required")
    private List<Integer> siscoAnswers;

    @NotEmpty(message = "MBI answers are required")
    private List<Integer> mbiAnswers;
}
