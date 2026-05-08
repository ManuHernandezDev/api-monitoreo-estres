package mx.edu.ito.estres.infrastructure.adapters.in.web;

import mx.edu.ito.estres.application.ports.out.StudentRepositoryPort;
import mx.edu.ito.estres.domain.model.Student;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.AuthRequest;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.AuthResponse;
import mx.edu.ito.estres.infrastructure.config.CustomUserDetailsService;
import mx.edu.ito.estres.infrastructure.config.JwtService;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final StudentRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthService(StudentRepositoryPort userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, CustomUserDetailsService customUserDetailsService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    public AuthResponse login(AuthRequest authRequest){
        Student user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(authRequest.getPassword(), user.email())){
            throw new RuntimeException("Invalid credentials");
        }


        String token = jwtService.generateToken(customUserDetailsService.loadUserByUsername(user.email()));
        return new AuthResponse(token);

    }
}
