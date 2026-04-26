package mx.edu.ito.estres.infrastructure.adapters.in.web.mapper;

import mx.edu.ito.estres.domain.model.Student;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.StudentRequestDTO;

public class StudentMapper {
    public static Student toDomain(StudentRequestDTO dto) {
        return new Student(
                null,
                dto.email(),
                dto.password(),
                dto.semester(),
                dto.origin(),
                dto.sleepHours(),
                dto.privacyAccepted()
        );
    }
}
