package mx.edu.ito.estres.application.ports.out;

import mx.edu.ito.estres.domain.model.EmergencyContact;

import java.util.List;

public interface EmergencyContactRepositoryPort {

    EmergencyContact save(EmergencyContact contact);

    List<EmergencyContact> findByStudentId(Long studentId);
}