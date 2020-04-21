package org.mvc.redis.security;

import org.mvc.redis.model.User;

public interface AuthorizationService {
    User signin(final String id, final String password);
    void setCurrentUser(final User user);
    User getCurrentUser();
}
