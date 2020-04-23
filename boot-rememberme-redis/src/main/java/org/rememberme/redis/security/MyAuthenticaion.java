package org.rememberme.redis.security;

import lombok.*;
import org.rememberme.redis.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
public class MyAuthenticaion extends UsernamePasswordAuthenticationToken {

    private  static final long serialVersionUID = 1L;

    User user;

    public MyAuthenticaion(String id, String password, List<GrantedAuthority> grantedAuthorityList, User user) {
        super(id, password, grantedAuthorityList);
        this.user = user;
    }

}
