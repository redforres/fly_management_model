package ca.fly.mtm.admin.config;

import java.io.Serializable;
import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    //public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 60;
    public static final long JWT_TOKEN_VALIDITY = 20 * 60;

    // this secret need to save some where safe
    //private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
    //private static final byte[] secretBytes = secret.getEncoded();
    //private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);
    private Key key;

    //private String base64SecretBytes="VUtwb3lXa2prNTdJYkFHMDYxaCsyT2NFNkozSW8wMFJ4S1R3M2Z0U3g5c25Nd0tvTDdjMVV2TldPdHYwbXhaWmFTS2lacE9nV2JhbzNLWWNBaGdZU1E9PQ==";
    private String base64SecretBytes;

    public void setSecretKey(String secretKey) {
        base64SecretBytes = secretKey;
    }

    //
    public Key getSecretFromToken(String token) {
        byte[] keyBytes = Decoders.BASE64.decode(base64SecretBytes);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        return this.key;

    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(base64SecretBytes).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, base64SecretBytes).compact();
    }

    // 180000 3 minutes
    public String generateToken(UserDetails userDetails, Long milliSeconds) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + milliSeconds))
                .signWith(SignatureAlgorithm.HS256, base64SecretBytes).compact();
    }

    public String generateToken(String name, Date today) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder().setClaims(claims).setSubject(name).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(today).signWith(SignatureAlgorithm.HS256, base64SecretBytes).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token) && validateSecret(token));
    }

    public Boolean validateToken(String token, String name) {
        final String username = getUsernameFromToken(token);

        return (username.equals(name) && !isTokenExpired(token)) && validateSecret(token);

    }

    public Boolean validateSecret(String token) {


        try {
            Jwts.parser().setSigningKey(getSecretFromToken(token)).parseClaimsJws(token);

            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.error("validateToken - JWT token Invalid signature. token:{}", token);
        } catch (ExpiredJwtException e) {
            logger.warn("validateToken - JWT token Expired. token:{}", token);
        } catch (UnsupportedJwtException e) {
            logger.error("validateToken - JWT token Unsupported. token:{}", token);
        } catch (IllegalArgumentException e) {
            logger.error("validateToken - JWT token compact of handler are invalid. token:{}", token);
        }
        return false;
    }

    public static void main(String args[]) {

        JwtTokenUtil sh = new JwtTokenUtil();
        Date today = new Date(System.currentTimeMillis() + 60 * 60 * 1000);

        System.out.println("base64SecretBytes : " + sh.base64SecretBytes);
        String name = "Ann dong";

        System.out.println("-----------call generateToken method");
        String jwtToken = sh.generateToken(name, today);

        System.out.println("jwtToken : " + jwtToken);

        System.out.println("--------call validateToken method");
        Boolean result = sh.validateToken(jwtToken, name);

        System.out.println("validateToken: " + result);

    }

}