package mx.edu.ito.estres.infrastructure.adapters.in.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mx.edu.ito.estres.domain.model.Role;

public record StudentRequestDTO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotNull
        int semester,
        @NotNull
        String origin,
        @NotNull
        int sleepHours,
        @NotNull
        boolean privacyAccepted,
        Role role) {
}