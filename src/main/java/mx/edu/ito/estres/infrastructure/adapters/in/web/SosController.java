package mx.edu.ito.estres.infrastructure.adapters.in.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import mx.edu.ito.estres.application.ports.out.StudentRepositoryPort;
import mx.edu.ito.estres.application.service.AlertService;
import mx.edu.ito.estres.application.usecases.RegisterEmergencyContactUseCase;
import mx.edu.ito.estres.domain.exception.StudentNotFoundException;
import mx.edu.ito.estres.domain.model.Student;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.request.EmergencyContactRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "SOS")
@RestController
@RequestMapping("/sos")
public class SosController {

    private final RegisterEmergencyContactUseCase registerUseCase;
    private final AlertService alertService;
    private final StudentRepositoryPort studentRepositoryPort;

    public SosController(
            RegisterEmergencyContactUseCase registerUseCase,
            AlertService alertService,
            StudentRepositoryPort studentRepositoryPort) {
        this.registerUseCase = registerUseCase;
        this.alertService = alertService;
        this.studentRepositoryPort = studentRepositoryPort;
    }

    @PostMapping("/contacts")
    public String registerContact(
            @Valid @RequestBody EmergencyContactRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        Student student = studentRepositoryPort
                .findByEmail(email)
                .orElseThrow(StudentNotFoundException::new);

        registerUseCase.register(
                student.id(),
                request);

        return "Emergency contact registered";
    }

    @PostMapping("/panic")
    public String panic(Authentication authentication) {

        String email = authentication.getName();

        Student student = studentRepositoryPort
                .findByEmail(email)
                .orElseThrow(StudentNotFoundException::new);

        alertService.sendEmergencyAlert(student.id());

        return "Emergency alert sent";
    }
}