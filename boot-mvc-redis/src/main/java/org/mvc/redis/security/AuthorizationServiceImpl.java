package org.mvc.redis.security;

import lombok.RequiredArgsConstructor;
import org.mvc.redis.model.User;
import org.mvc.redis.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;

    @Override
    public User signin(final String id, final String password) {
        Optional<User> user = userRepository.findById(id);
        logger.info("id : " + id + " password : " + password);
        if(!user.isPresent()) {
            logger.info("cannot found user");
            return null;
        }
        else {
            if(user.get().getPassword().equals(password)) return user.get();
            else {
                logger.info("wrong password");
                return null;
            }
        }
    }

    @Override
    public void setCurrentUser(User user) {
        ((MyAuthenticaion)
                SecurityContextHolder.getContext().getAuthentication()).setUser(user);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof MyAuthenticaion)
            return ((MyAuthenticaion) authentication).getUser();
        return null;
    }
}
