package mx.edu.ito.estres.application.ports.out;

import mx.edu.ito.estres.domain.model.EvaluationResult;

public interface EvaluationRepositoryPort {

    EvaluationResult save(EvaluationResult result);

}
