package hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import hello.model.Role;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true )
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
		http.authorizeHttpRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().permitAll()
		.and().formLogin().loginPage("/login").defaultSuccessUrl("/")
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true);
        return http.build();
    }

	@Bean
	public BCryptPasswordEncoder encPwd() {
		return new BCryptPasswordEncoder();
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.authorizeHttpRequests()
//		.anyRequest().permitAll()
//		.and().formLogin().loginPage("/login").defaultSuccessUrl("/")
//		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true);
//	}
}
