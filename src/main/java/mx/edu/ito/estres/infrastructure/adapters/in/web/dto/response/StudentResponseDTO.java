package mx.edu.ito.estres.infrastructure.adapters.in.web.dto.response;

public record StudentResponseDTO(
        Long id,
        String email,
        Integer semester,
        String origin,
        Integer sleepHours
) {}
