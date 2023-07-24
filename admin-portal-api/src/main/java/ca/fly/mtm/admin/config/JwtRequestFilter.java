package ca.fly.mtm.admin.config;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    final static String VE_PATH = "/ve";
    final static String PKI_HEADER = "GOSECURE_SN";
    final static String AUTHORIZATION_HEADER = "Authorization";

   // @Autowired
   // private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AdminConfig adminConfig;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        
        //String veUrl = url.replaceFirst(".*/([^/?]+).*", "$1");
        String veUrl = url.isEmpty() ? "" : url.substring(url.length() - VE_PATH.length());
        final String requestTokenHeader = request.getHeader(AUTHORIZATION_HEADER);
        logger.info("URL : " + url + " doFilterInternal requestTokenHeader========>" + requestTokenHeader);
        String pkiHeader = request.getHeader(PKI_HEADER);
        logger.info("pkiHeader ====> " + pkiHeader);
        String pkiUserName = pkiHeader;



        String username = null;
        String jwtToken = null;
        boolean invalidToken = false;

        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token for validation
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            jwtToken = jwtToken.replaceAll("\"", "");
            jwtToken = jwtToken.replaceAll("\\\\", "");

            logger.info("jwtToken after remove extra quote ======> " + jwtToken);

            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.error("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token has expired");
            }
        } else {
            invalidToken = true;
            logger.warn("URL : " + url + " JWT Token does not begin with Bearer String");
        }

        //pkiUserName = "DSAC57712";
       /* if (invalidToken && pkiUserName != null && pkiUserName.isEmpty() == false && VE_PATH.equalsIgnoreCase(veUrl)) {
            logger.info("=====================pkiUserName " + pkiUserName);
            UserDetails userDetails = this.userService.loadUserByUsername(pkiUserName);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } else {
            try {
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userService.loadUserByUsername(username);
                    if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken
                                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            } catch (Exception ex) {
                logger.error("JwtRequestFilter Exception : " + ex.getStackTrace());
            }
        } */
        chain.doFilter(request, response);
    }

}