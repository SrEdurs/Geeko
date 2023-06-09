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
                .requestMatchers("/","","/registerUser","/crearcuenta","/saveUser","/css/**", "/js/**","/logo/**","/imagenes/**","/iconos/**","/bienvenida","/index").permitAll()
                .requestMatchers("/panelreportes","/cambiareporte/**","/suspender/**").hasRole("ADMIN")
                .requestMatchers("/perfil","/perfil/**","/cuestionario","/usuarios/edit","/social").hasRole("USER")
                .requestMatchers("/productos/**","/crearcomentario/**","/borrar/**").hasRole("USER")
                .requestMatchers("/productos/libros","/crearcomentario/**","/borrar/**").hasRole("USER")
                .requestMatchers("/common").hasAnyAuthority("Annonymous,User,Admin")
                .anyRequest().authenticated()

                .and()
                .csrf().disable()
                .formLogin().loginPage("/usuarios/login").permitAll()
                .defaultSuccessUrl("/home",true)

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessdenied")

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