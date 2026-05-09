package mx.edu.ito.estres.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import mx.edu.ito.estres.domain.model.Student;

@RequiredArgsConstructor
public class StudentUserDetails implements UserDetails {

    private final Student student;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(student.role().name()));
    }

    @Override
    public String getPassword() {
        return student.password();
    }

    @Override
    public String getUsername() {
        return student.email();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}