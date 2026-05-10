package mx.edu.ito.estres.infrastructure.adapters.out.persistence;

import mx.edu.ito.estres.application.ports.out.EmergencyContactRepositoryPort;
import mx.edu.ito.estres.domain.model.EmergencyContact;
import mx.edu.ito.estres.infrastructure.adapters.out.persistence.entity.EmergencyContactEntity;
import mx.edu.ito.estres.infrastructure.adapters.out.persistence.repository.EmergencyContactJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmergencyContactPersistenceAdapter
        implements EmergencyContactRepositoryPort {

    private final EmergencyContactJpaRepository repository;

    public EmergencyContactPersistenceAdapter(
            EmergencyContactJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmergencyContact save(EmergencyContact contact) {

        EmergencyContactEntity entity = new EmergencyContactEntity();

        entity.setStudentId(contact.studentId());
        entity.setName(contact.name());
        entity.setPhone(contact.phone());
        entity.setEmail(contact.email());

        EmergencyContactEntity saved = repository.save(entity);

        return new EmergencyContact(
                saved.getId(),
                saved.getStudentId(),
                saved.getName(),
                saved.getPhone(),
                saved.getEmail());
    }

    @Override
    public List<EmergencyContact> findByStudentId(Long studentId) {

        return repository.findByStudentId(studentId)
                .stream()
                .map(entity -> new EmergencyContact(
                        entity.getId(),
                        entity.getStudentId(),
                        entity.getName(),
                        entity.getPhone(),
                        entity.getEmail()))
                .toList();
    }
}