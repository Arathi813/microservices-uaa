package hr.mhercog.usermanagementservice.config;

import java.util.Map;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class WebSecurityConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll().anyRequest()
				.authenticated().and().cors().disable().csrf().disable();
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
