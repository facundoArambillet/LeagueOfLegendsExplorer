package com.app.LeagueItemExplorer.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class ServerConfig implements WebMvcConfigurer {
    @Value("${email}")
    private String email;
    @Value("${password}")
    private String password;

    @Bean
    public UserDetailsService userDetailService() {

        return new InMemoryUserDetailsManager(
                User.withUsername("test@test.com")
                        .password(passwordEncoder().encode("123456789"))
                        .roles("ADMIN")
                        .build(),

                User.withUsername("test20@test.com")
                        .password(passwordEncoder().encode("123456789"))
                        .roles("BASIC")
                        .build()
        );
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // permite CORS para todas las rutas
                .allowedOrigins("http://localhost:4200") // permite solo solicitudes desde localhost:4200
                .allowedMethods("GET", "POST", "PUT", "DELETE") // permite solo los métodos GET, POST, PUT y DELETE
                .allowedHeaders("*")// permite todos los encabezados
                .allowCredentials(true);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                        //Si quiero autenticar tengo que habilitar el .cors() porque sino me tira error
                        //.anyRequest().authenticated()
                )
                //Si no agrego esto que esta deprecated no funciona y no entiendo porque
//                .cors()
//                .and()
                .csrf(csrf -> csrf.disable())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
