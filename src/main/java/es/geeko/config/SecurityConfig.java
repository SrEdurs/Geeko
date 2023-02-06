package es.geeko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsService uds;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.authorizeHttpRequests()
                .requestMatchers("/**","/registerUser","/usuarios/crearcuenta","/saveUser","/","/css/**", "/js/**","/logo/**","/imagenes/**","/iconos/**").permitAll()
                .requestMatchers("/reportes/panelreportes").hasAuthority("Admin")
                .requestMatchers("/usr").hasAuthority("User")
                .requestMatchers("/ano").hasAuthority("Annonymous")
                .requestMatchers("/common").hasAnyAuthority("Annonymous,User,Admin")
                .anyRequest().authenticated()

                .and()
                .csrf().disable()
                .formLogin().loginPage("/usuarios/login").permitAll()
                .defaultSuccessUrl("/perfil",true)

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied")

                .and()
                .authenticationProvider(authenticationProvider());

        return http.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(uds);
        authenticationProvider.setPasswordEncoder(encoder);
        return authenticationProvider;
    }
}