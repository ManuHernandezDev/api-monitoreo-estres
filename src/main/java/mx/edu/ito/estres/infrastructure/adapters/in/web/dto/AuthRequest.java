package mx.edu.ito.estres.infrastructure.adapters.in.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {
    String email;
    String password;
}
