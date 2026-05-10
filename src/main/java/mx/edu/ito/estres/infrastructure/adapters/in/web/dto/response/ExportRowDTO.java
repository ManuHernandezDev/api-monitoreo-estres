package mx.edu.ito.estres.infrastructure.adapters.in.web.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import mx.edu.ito.estres.domain.model.StressLevel;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExportRowDTO {

    private Long studentId;

    private Integer semester;

    private String origin;

    private Integer sleepHours;

    private Integer siscoScore;

    private Integer mbiScore;

    private StressLevel stressLevel;

    private LocalDateTime createdAt;
}
