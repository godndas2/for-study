package com.forstudy.oauth2.security;

import com.forstudy.oauth2.model.entity.User;
import com.forstudy.oauth2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
        }
        return new UserRepositoryUserDetails(user);
    }


    private final static class UserRepositoryUserDetails extends User implements UserDetails {

        private static final long serialVersionUID = 1L;

        private UserRepositoryUserDetails(User user) {
            super(); // user
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return getRoles();
        }

        @Override
        public String getUsername() {
            return getLogin();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

    }
}
