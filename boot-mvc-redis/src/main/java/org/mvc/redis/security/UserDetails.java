package org.mvc.redis.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class UserDetails extends User {

    /*
    * AuthorityUtils.createAuthorityList : Security 사용 중 권한을 부여할 수 있다.
    * 사실, Security 에서 제공하는 UserDetails 의 Interface 를 사용하는 것이 더 간단하다.
    * */
    public UserDetails(org.mvc.redis.model.User user) {
        super(user.getId(), user.getPassword(), AuthorityUtils.createAuthorityList("user"));
    }

}
