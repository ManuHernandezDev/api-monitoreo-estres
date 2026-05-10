package mx.edu.ito.estres.infrastructure.adapters.out.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mx.edu.ito.estres.domain.model.StressLevel;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class EvaluationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private Integer siscoScore;

    private Integer mbiScore;

    @Enumerated(EnumType.STRING)
    private StressLevel stressLevel;

    private LocalDateTime createdAt;
}
