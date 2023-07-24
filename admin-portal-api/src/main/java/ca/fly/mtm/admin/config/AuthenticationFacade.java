package ca.fly.mtm.admin.config;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
	public Authentication getAuthentication();

	public String retrieveRoleName(Authentication authentication);
}

