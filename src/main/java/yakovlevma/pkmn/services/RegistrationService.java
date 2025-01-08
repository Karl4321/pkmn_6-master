package yakovlevma.pkmn.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import yakovlevma.pkmn.DTO.UserDTO;
import yakovlevma.pkmn.models.LoginRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final JdbcUserDetailsManager jdbcUserDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(LoginRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        UserDTO newUser = new UserDTO(request.getUsername(), encodedPassword, true, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        jdbcUserDetailsManager.createUser(newUser);
    }
}