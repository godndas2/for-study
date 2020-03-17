package org.bootvue.jwt.web;

import lombok.RequiredArgsConstructor;
import org.bootvue.jwt.auth.JwtTokenRequest;
import org.bootvue.jwt.auth.JwtTokenResponse;
import org.bootvue.jwt.auth.JwtTokenUtil;
import org.bootvue.jwt.auth.JwtUserDetails;
import org.bootvue.jwt.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins= { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
public class JwtController {

    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping(value = "${jwt.get.token.uri}")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtTokenRequest tokenRequest) throws AuthenticationException {

        authenticate(tokenRequest.getUsername(), tokenRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(tokenRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtTokenResponse(token));
    }

    @GetMapping(value = "${jwt.refresh.token.uri}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUserDetails user = (JwtUserDetails) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    ////////////////////////////////////////////// NON API ///////////////////////////////////////////////////////
    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private void authenticate(String username, String password) {
        // parameter 의 null 체크, null 이 아닐시 그대로 반환, null 일 경우 NPE
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }
    }
}
