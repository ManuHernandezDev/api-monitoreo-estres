package mx.edu.ito.estres.application.service;
import mx.edu.ito.estres.application.ports.out.EvaluationJpaRepository;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.response.ExportRowDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportService {

    private final EvaluationJpaRepository repository;

    public ExportService(
            EvaluationJpaRepository repository
    ) {
        this.repository = repository;
    }

    public String generateCsv() {

        List<ExportRowDTO> rows =
                repository.exportData();

        StringBuilder csv =
                new StringBuilder();

        csv.append("""
student_id,semester,origin,sleep_hours,sisco_score,mbi_score,stress_level,created_at
""");

        for(ExportRowDTO row : rows) {

            csv.append(row.getStudentId()).append(",");
            csv.append(row.getSemester()).append(",");
            csv.append(row.getOrigin()).append(",");
            csv.append(row.getSleepHours()).append(",");
            csv.append(row.getSiscoScore()).append(",");
            csv.append(row.getMbiScore()).append(",");
            csv.append(row.getStressLevel()).append(",");
            csv.append(row.getCreatedAt()).append("\n");
        }

        return csv.toString();
    }
}