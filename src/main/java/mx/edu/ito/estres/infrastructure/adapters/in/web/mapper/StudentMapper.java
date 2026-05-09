package mx.edu.ito.estres.infrastructure.adapters.in.web.mapper;

import mx.edu.ito.estres.domain.model.Student;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.response.StudentResponseDTO;

public class StudentMapper {
    public static StudentResponseDTO toDomain(Student student) {
        return new StudentResponseDTO(
                null,
                student.email(),
                student.semester(),
                student.origin(),
                student.sleepHours()
        );
    }
}
