package mx.edu.ito.estres.infrastructure.config;

import mx.edu.ito.estres.application.ports.out.StudentRepositoryPort;
import mx.edu.ito.estres.application.usecases.RegisterStudentUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public RegisterStudentUseCase registerStudentUseCase(StudentRepositoryPort repo) {
        return new RegisterStudentUseCase(repo);
    }
}
