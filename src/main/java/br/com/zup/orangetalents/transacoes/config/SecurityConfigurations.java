package br.com.zup.orangetalents.transacoes.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/api/transacoes/**").hasAuthority("SCOPE_transacoes")
			.anyRequest().authenticated()
			.and().oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	}

	
}
