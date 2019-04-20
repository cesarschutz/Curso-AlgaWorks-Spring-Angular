package com.algamoney.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableWebSecurity
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//cria um usuario com uma permissão em memória (usuario admin senha admin)
		auth.inMemoryAuthentication()
			.withUser("admin").password("admin").roles("ROLE");
	}
	
	//Esse metodo configura o autenticador (o de baixo da parte de resource da aplicação)
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				//permite acessar '/categorias' sem credenciais
				.antMatchers("/categorias").permitAll()
				//pede autenticação para qualquer request (requisição)
				.anyRequest().authenticated()
				.and()
				//para deixar sem sessão no servidor (não mantém estado de nada)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				//desabilita o cross site cross site request forgery (javascript injection)
				.csrf().disable();
	}
	
	//Esse metodo configura a parte de resource da aplicação (a de cima do autenticador)
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		//define statless no servidor
		resources.stateless(true);
	}
}
