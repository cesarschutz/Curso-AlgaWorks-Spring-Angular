package com.algamoney.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

@Configuration
@EnableWebSecurity
@EnableResourceServer
//ativa a segurança em todos os métodos da apalicação
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Esse metodo configura o autenticador (o de baixo da parte de resource da aplicação)
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				//permite acessar '/categorias' sem credenciais
				.antMatchers("/categorias").permitAll().antMatchers("/h2").permitAll()
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
	
	//Método que adiciona o handler para a segurança dos métodos em oauth 2
	@Bean
	public MethodSecurityExpressionHandler createExpressionHandler() {
		return new OAuth2MethodSecurityExpressionHandler();
	}
}
