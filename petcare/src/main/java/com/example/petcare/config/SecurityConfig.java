package com.example.petcare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration//환경설정
@EnableWebSecurity//security선언
@EnableGlobalMethodSecurity(prePostEnabled=true,securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public BCryptPasswordEncoder encoderPwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable();//post방식은 모두 csrf를 전달해야 함 그래서 disable로 함
	http.authorizeRequests()//권한을 가진 사람만 접근하도록 
	.antMatchers("/user/*").authenticated()//user로 넘어오면 security적용되게 하는 것
	.anyRequest()//나머지들에게
	.permitAll()//모든 권한 부여(security 적용은 아닌듯)
	
	.and()
	.formLogin()
	.loginPage("/login")
	//.loginProcessingUrl("/loginPro")//action이름을 다르게 하는 것 안해도 됨
	.defaultSuccessUrl("/")
	
	.and()
	.logout()
	.logoutUrl("/logout")
	.logoutSuccessUrl("/")
	.invalidateHttpSession(true);
	}
}