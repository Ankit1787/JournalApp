package net.engineeringdigest.journalApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


// old way by extending websecurityConfiguerAdapter
//@Configuration
//@EnableWebSecurity
//public class SpringSecurity extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected  void configure(HttpSecurity http)throws Exception{
//        http.authorizeRequests()
//                        .antMatchers("/journal/**").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .httpBasic();
//
//
//    }
//}

//new way
@Configuration
public  class SpringSecurity{

    @Autowired
   private UserDetailServiceImpl userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder; // ✅ injected from AppConfig

    @Bean
    public AuthenticationProvider authenticationProvider (){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/journal/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // Enables BasicAuthenticationFilter

        return http.build();
    }
}