package mx.edu.ito.estres.domain.model;

public record EmergencyContact(
        Long id,
        Long studentId,
        String name,
        String phone,
        String email) {
}