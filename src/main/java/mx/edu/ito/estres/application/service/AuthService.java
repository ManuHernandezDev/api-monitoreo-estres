package mx.edu.ito.estres.application.service;

import mx.edu.ito.estres.application.ports.out.StudentRepositoryPort;
import mx.edu.ito.estres.domain.exception.InvalidCredentialsException;
import mx.edu.ito.estres.domain.model.Student;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.request.AuthRequest;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.response.AuthResponse;
import mx.edu.ito.estres.infrastructure.config.CustomUserDetailsService;
import mx.edu.ito.estres.infrastructure.config.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final StudentRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(StudentRepositoryPort userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, CustomUserDetailsService customUserDetailsService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse login(AuthRequest authRequest){
        System.out.println("Entrando a service");
        Student user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(authRequest.getPassword(), user.password())){
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(user.email(), user.role().name());
        System.out.println("Token Generado");
        return new AuthResponse(token);
    }
}
