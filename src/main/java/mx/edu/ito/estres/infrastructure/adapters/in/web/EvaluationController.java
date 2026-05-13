package mx.edu.ito.estres.infrastructure.adapters.in.web;


import mx.edu.ito.estres.application.ports.out.StudentRepositoryPort;
import mx.edu.ito.estres.application.usecases.SubmitEvaluationUseCase;
import mx.edu.ito.estres.domain.exception.StudentNotFoundException;
import mx.edu.ito.estres.domain.model.Student;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.request.EvaluationRequest;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.response.EvaluationResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    private final SubmitEvaluationUseCase submitEvaluationUseCase;
    private final StudentRepositoryPort studentRepositoryPort;

    public EvaluationController(SubmitEvaluationUseCase submitEvaluationUseCase,
            StudentRepositoryPort studentRepositoryPort
    ) {
        this.submitEvaluationUseCase = submitEvaluationUseCase;
        this.studentRepositoryPort = studentRepositoryPort;
    }

    @PostMapping
    public EvaluationResponse submit(
            @RequestBody EvaluationRequest request,
            Authentication authentication
    ) {

        String email = authentication.getName();

        Student student = studentRepositoryPort
                .findByEmail(email)
                .orElseThrow(StudentNotFoundException::new);

        return submitEvaluationUseCase.submit(
                student.id(),
                request
        );
    }
}