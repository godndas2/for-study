package org.bootvue.jwt.model;

import org.bootvue.jwt.auth.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    static List<JwtUserDetails> inMemoryUsers = new ArrayList<>();

    static {
        inMemoryUsers.add(new JwtUserDetails(1L,
                "bootvue",
                "1234",
                "ROLE_USER"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JwtUserDetails> findFirst = inMemoryUsers.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
        if (!findFirst.isPresent()) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }
        return findFirst.get();
    }
}
