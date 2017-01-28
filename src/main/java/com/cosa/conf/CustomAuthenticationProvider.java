package com.cosa.conf;

/**
 * c
 */
import com.cosa.service.CurrentUsuarioDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Alessandro
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CurrentUsuarioDetalleService currentUsuarioDetalleService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // datos del formulario Principal ( ususario) Credentials(password)
        String principal = authentication.getName();
        String credentials = (String) authentication.getCredentials();

        User user = (User) currentUsuarioDetalleService.loadUserByUsername(principal);
        if (user != null) {
            if (passwordEncoder.matches(credentials, user.getPassword())) {
                System.out.println("login correcto");
                return new UsernamePasswordAuthenticationToken(principal, credentials, user.getAuthorities());
            } else {
                System.out.println("error de login : " + principal);
                throw new BadCredentialsException("error de login");
            }

        } else {
            System.out.println("error de login : " + principal);
            throw new BadCredentialsException("error de login");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        // si ponemos false se rompe la seguridad cuidado!!
        return true;
    }

}