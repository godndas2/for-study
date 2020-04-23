package org.rememberme.redis.security;

import org.rememberme.redis.model.User;

public interface AuthorizationService {
    User signin(final String id, final String password);
    void setCurrentUser(final User user);
    User getCurrentUser();
}
