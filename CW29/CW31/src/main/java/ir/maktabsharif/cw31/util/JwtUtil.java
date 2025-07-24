package ir.maktabsharif.cw31.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private final UserDetailsService userDetailsService;
    private final SecretKey secretKey;

    private static final long EXPIRATION_MS = 24 * 60 * 60 * 1000;

    public JwtUtil(@Value("${jwt.secret}") String base64Secret, UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        byte[] keyBytes = Base64.getDecoder().decode(base64Secret);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public void validateToken(String token, String username, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        boolean valid = username.equals(userDetails.getUsername()) && isTokenExpired(token);
        if (valid) {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities().stream()
                        .findFirst()
                        .map(GrantedAuthority::getAuthority)
                        .orElse("ROLE_USER"))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
