package es.geeko.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public String appName() {
        return "Geeko";
    }

    @Bean
    public String appVersion() {
        return "1.0";
    }

    @Bean
    public String appDescription() {
        return "Geeko es una aplicación web que permite a los usuarios compartir sus opiniones sobre productos y servicios, así como consultar las opiniones de otros usuarios.";
    }

    @Bean
    public String appAuthor() {
        return "Geeko";
    }


}