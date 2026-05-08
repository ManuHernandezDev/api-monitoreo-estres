package mx.edu.ito.estres.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mx.edu.ito.estres.application.ports.out.StudentRepositoryPort;
import mx.edu.ito.estres.domain.model.Student;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepositoryPort studentRepositoryPort;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Student student = studentRepositoryPort
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Student not found"));

        return new StudentUserDetails(student);
    }
}