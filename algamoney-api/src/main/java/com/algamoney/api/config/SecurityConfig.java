package com.algamoney.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//cria um usuario com uma permissão em memória (usuario admin senha admin)
		auth.inMemoryAuthentication()
			.withUser("admin").password("admin").roles("ROLE");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				//permite acessar '/categorias' sem credenciais
				.antMatchers("/categorias").permitAll()
				//pede autenticação para qualquer request (requisição)
				.anyRequest().authenticated()
				.and()
				//autenticação do tipo basic
				.httpBasic().and()
				//para deixar sem sessão no servidor (não mantém estado de nada)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				//desabilita o cross site cross site request forgery (javascript injection)
				.csrf().disable();
	}
}
