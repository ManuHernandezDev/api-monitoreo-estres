package mx.edu.ito.estres.infrastructure.adapters.in.web.dto;

import mx.edu.ito.estres.domain.model.Role;

public record StudentRequestDTO(
        String email,
        String password,
        int semester,
        String origin,
        int sleepHours,
        boolean privacyAccepted,
        Role role) {}