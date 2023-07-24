package ca.fly.mtm.admin.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {
	
	@Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
	
	public String retrieveRoleName(Authentication authentication) {
		Collection<? extends GrantedAuthority> userRoles = authentication.getAuthorities();
    	ArrayList<GrantedAuthority> userRoleLst = new ArrayList(userRoles); 
    	
    	if(userRoleLst == null || userRoleLst.size() == 0) {
    		return "";
    	}
    	
    	if(userRoleLst.get(0).getAuthority() == null) {
    		return "";
    	}
    	
    	String roleName = userRoleLst.get(0).getAuthority().toString();
    	
    	return roleName;

	}
}
