package mx.edu.ito.estres.infrastructure.adapters.in.web.dto.sos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmergencyContactRequest {

    private String name;
    private String phone;
    private String email;
}