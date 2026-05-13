package mx.edu.ito.estres.application.usecases;

import mx.edu.ito.estres.application.ports.out.EmergencyContactRepositoryPort;
import mx.edu.ito.estres.domain.model.EmergencyContact;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.request.EmergencyContactRequest;
import org.springframework.stereotype.Service;

@Service
public class RegisterEmergencyContactUseCase {

    private final EmergencyContactRepositoryPort repositoryPort;

    public RegisterEmergencyContactUseCase(
            EmergencyContactRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public void register(
            Long studentId,
            EmergencyContactRequest request) {

        EmergencyContact contact = new EmergencyContact(
                null,
                studentId,
                request.getName(),
                request.getPhone(),
                request.getEmail());

        repositoryPort.save(contact);
    }
}