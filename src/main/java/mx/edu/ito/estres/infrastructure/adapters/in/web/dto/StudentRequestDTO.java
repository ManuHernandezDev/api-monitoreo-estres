package mx.edu.ito.estres.infrastructure.adapters.in.web.dto;

public record StudentRequestDTO(
        String email,
        String password,
        int semester,
        String origin,
        int sleepHours,
        boolean privacyAccepted) {}