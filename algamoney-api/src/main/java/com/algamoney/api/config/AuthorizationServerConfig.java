package com.algamoney.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		//configura em memoria (poderia ser banco de dados
		clients.inMemory()
			//cliente
			.withClient("angular")
			//secret
			.secret("@ngul@r0")
			//escopos conseguindo limitar o acesso desse cliente (dessa aplicação angular que estamos liberando)
			.scopes("read", "write")
			//fluxo que iremos utilizar (angular recebe usuario e senha e o angular chama aqui para pegar o token)
			.authorizedGrantTypes("password")
			//quantos segundos o token fica ativo
			.accessTokenValiditySeconds(1800);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
		//armazena o token em um tokenStory, preciso armazenar por que criarei e depois receberei
			.tokenStore(tokenStore())
			.authenticationManager(authenticationManager);
	}
	
	//o token story que o metodo de cima vai utilizar (poderia colocar em banco de dados)
	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
	
}
