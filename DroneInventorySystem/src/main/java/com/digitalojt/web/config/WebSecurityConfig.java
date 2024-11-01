package com.digitalojt.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.digitalojt.web.consts.UrlConsts;
import com.digitalojt.web.service.UserDetailsServiceImpl;

/**
 * WebSecurityConfig
 * 
 * Spring Security の設定を行うクラス
 * SecurityFilterChain と AuthenticationManager を設定
 * 
 * @author haruka matano
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * SecurityFilterChainを設定
     * 
     * @param http HttpSecurityオブジェクト
     * @return SecurityFilterChainインスタンス
     * @throws Exception 設定中の例外
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(ahr -> ahr
                .requestMatchers(UrlConsts.NO_AUTHENTICATION).permitAll()  // 認証不要のURLを指定
                .anyRequest().authenticated()  // その他は認証が必要
            )
            .formLogin(login -> login
                .loginPage(UrlConsts.LOGIN)  // ログインページの指定
                .loginProcessingUrl(UrlConsts.AUTHENTICATE)  // ログインフォームの送信先URLを指定
                .defaultSuccessUrl(UrlConsts.STOCK_LIST, true) // ログイン成功時の遷移先
                .failureUrl(UrlConsts.LOGIN + "?error=true") // ログイン失敗時のリダイレクト先
                .permitAll()  // 認証不要
            )
            .logout(logout -> logout
                .permitAll()  // 認証不要
            );

        return http.build();
    }

    /**
     * AuthenticationManagerを設定
     * 
     * @param http HttpSecurityオブジェクト
     * @return AuthenticationManagerインスタンス
     * @throws Exception 設定中の例外
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return auth.build();
    }
}
