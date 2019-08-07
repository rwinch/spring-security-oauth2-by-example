package sample.message;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Rob Winch
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests(requests ->
				requests
					.anyRequest().hasAuthority("SCOPE_message:read")
			)
			.oauth2ResourceServer(resourceServer ->
				resourceServer.jwt()
			);
	}
}
