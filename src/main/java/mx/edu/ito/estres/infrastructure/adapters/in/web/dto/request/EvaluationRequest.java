package mx.edu.ito.estres.infrastructure.adapters.in.web.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EvaluationRequest {

    private List<Integer> siscoAnswers;
    private List<Integer> mbiAnswers;
}
