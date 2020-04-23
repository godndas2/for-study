package org.rememberme.redis.security;

import lombok.RequiredArgsConstructor;
import org.rememberme.redis.model.User;
import org.rememberme.redis.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);
        if(!user.isPresent()) {
            logger.info("Sign In Failed");
            throw new UsernameNotFoundException("Sign In Failed");
        }
        return new UserDetails(user.get());
    }
}
