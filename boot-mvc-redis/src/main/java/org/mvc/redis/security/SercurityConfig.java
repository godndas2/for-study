package org.mvc.redis.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SercurityConfig extends WebSecurityConfigurerAdapter {

    private final static String REMEMBER_ME_KEY = "remember-me";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll();

        http
                .rememberMe()
                .key(REMEMBER_ME_KEY)
                .tokenValiditySeconds(6048000);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/resources/**",
                "/h2-console/**");
    }
}
