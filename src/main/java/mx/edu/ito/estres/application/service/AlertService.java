package mx.edu.ito.estres.application.service;

import mx.edu.ito.estres.application.ports.out.EmergencyContactRepositoryPort;
import mx.edu.ito.estres.domain.model.EmergencyContact;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    private final JavaMailSender mailSender;
    private final EmergencyContactRepositoryPort repositoryPort;

    public AlertService(
            JavaMailSender mailSender,
            EmergencyContactRepositoryPort repositoryPort) {
        this.mailSender = mailSender;
        this.repositoryPort = repositoryPort;
    }

    public void sendEmergencyAlert(Long studentId) {

        List<EmergencyContact> contacts = repositoryPort.findByStudentId(studentId);

        for (EmergencyContact contact : contacts) {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(contact.email());

            message.setSubject(
                    "ALERTA - Estudiante en situación crítica");

            message.setText("""
                    El estudiante registrado ha activado
                    una alerta de emergencia emocional.

                    Se recomienda contactar inmediatamente.
                    """);

            try {
                mailSender.send(message);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}