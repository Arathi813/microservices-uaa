package hr.mhercog.zuulgatewayservice;

import java.util.Map;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableOAuth2Sso
@EnableWebSecurity
@EnableRedisHttpSession
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
				.antMatchers("/login", "/uaa/**").permitAll().anyRequest().authenticated().and().csrf().disable().cors()
				.disable();
	}

	private static final String[] PRINCIPAL_KEYS = new String[] { "user_name", "user", "username", "userid", "user_id",
			"login", "id", "name" };

	@Bean
	public PrincipalExtractor principalExtractor() {
		return new PrincipalExtractor() {

			@Override
			public Object extractPrincipal(Map<String, Object> map) {
				for (String key : PRINCIPAL_KEYS) {
					if (map.containsKey(key)) {
						return map.get(key);
					}
				}
				return null;
			}
		};
	}
}
