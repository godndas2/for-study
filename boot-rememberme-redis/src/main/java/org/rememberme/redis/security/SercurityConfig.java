package org.rememberme.redis.security;

import lombok.RequiredArgsConstructor;
import org.rememberme.redis.model.TokenRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SercurityConfig extends WebSecurityConfigurerAdapter {

    private final static String REMEMBER_ME_KEY = "remember-me";

    private final UserDetailsService userDetailsService;
    private final FindByIndexNameSessionRepository sessionRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll();

        http
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/signinok")
                .failureUrl("/error")
                .defaultSuccessUrl("/main", true)
                .usernameParameter("id")
                .passwordParameter("password");

        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
                .logoutSuccessUrl("/signin")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", REMEMBER_ME_KEY);

        http
                .rememberMe()
                .key(REMEMBER_ME_KEY)
                .rememberMeServices(persistentTokenBasedRememberMeServices())
                .tokenValiditySeconds(6048000);

        http
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false) // 중복로그인 방지, 기존 사용자 session 을 종료시킨다.
                .expiredUrl("/alreadyUser") // 중복 로그인 시 보여줄 page
                .sessionRegistry(sessionRegistry());
    }


    @Configuration
    @RequiredArgsConstructor
    public static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

        private final UserDetailsService userDetailsService;

        @Bean
        public PasswordEncoder passwordEncoder() {
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }
    }

    @Bean
    public PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices() {
        PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices =
                new PersistentTokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService, persistentTokenRepository());
        return persistentTokenBasedRememberMeServices;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        TokenRepositoryImpl tokenRepository = new TokenRepositoryImpl();
        return tokenRepository;
    }

    @Bean
    SpringSessionBackedSessionRegistry sessionRegistry() {
        return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/resources/**",
                "/h2-console/**");
    }
}
