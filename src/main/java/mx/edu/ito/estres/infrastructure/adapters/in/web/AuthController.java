package mx.edu.ito.estres.infrastructure.adapters.in.web;

import jakarta.validation.Valid;
import mx.edu.ito.estres.application.service.AuthService;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.request.AuthRequest;
import mx.edu.ito.estres.infrastructure.adapters.in.web.dto.response.AuthResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest authRequest) {
        System.out.println("Controller");
        return authService.login(authRequest);
    }
}