package org.bootvue.jwt.auth;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter { // OncePerRequestFilter: 매번 Filter 의 내용이 수행되는 것을 방지, extends GenericFilterBean

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired // construct inject Error Resolve
    private UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        logger.debug("Authentication Request For '{}'", httpServletRequest.getRequestURL());

        final String requestHeader = httpServletRequest.getHeader(this.tokenHeader);

        String username = null;
        String jwtToken = null;

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            jwtToken = requestHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.error("JWT_TOKEN_UNABLE_TO_GET_USERNAME", e);
            } catch (ExpiredJwtException e) {
                logger.warn("JWT_TOKEN_EXPIRED", e);
            }
        } else {
            logger.warn("JWT_TOKEN_DOES_NOT_START_WITH_BEARER_STRING");
        }

        logger.debug("JWT_TOKEN_USERNAME_VALUE '{}'", username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); //SecurityContextHolder.getContext() 의 username

            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Bean
    public UserDetailsService userDetailsService() {

        //User Role
        UserDetails theUser = User.withUsername("test")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .password("1234").roles("USER").build();

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(theUser);

        return userDetailsManager;
    }
}
