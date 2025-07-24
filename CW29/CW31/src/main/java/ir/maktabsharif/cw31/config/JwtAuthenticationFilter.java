package ir.maktabsharif.cw31.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.maktabsharif.cw31.dto.author.AuthorLoginRequest;
import ir.maktabsharif.cw31.model.UserDetailsImpl;
import ir.maktabsharif.cw31.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

import java.io.IOException;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final PathPatternRequestMatcher loginPath = PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST, "/api/authors/login");
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JwtUtil jwtUtil;
    private final UserDetailsService  userDetailsService;

    protected JwtAuthenticationFilter(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        super(loginPath, authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    public Authentication authenticationMapper(HttpServletRequest httpServletRequest) throws IOException {
        AuthorLoginRequest authorLoginRequest = objectMapper.readValue(httpServletRequest.getInputStream(), AuthorLoginRequest.class);
        UserDetails userDetails = userDetailsService.loadUserByUsername(authorLoginRequest.getUsername());
        return new UsernamePasswordAuthenticationToken(userDetails, authorLoginRequest.getPassword());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, ServletException, IOException {
        Authentication authentication = authenticationMapper(request);
        if (authentication == null) {
            return null;
        }
        Authentication authenticate = getAuthenticationManager().authenticate(authentication);
        if (authenticate == null) {
            throw new ServletException("Authentication Failed");
        }
        return authenticate;
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetailsImpl principal = (UserDetailsImpl) authResult.getPrincipal();
        String token = jwtUtil.generateToken(principal);
        response.addHeader("Authorization", "Bearer " + token);
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
