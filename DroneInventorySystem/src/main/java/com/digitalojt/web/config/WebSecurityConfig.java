package com.digitalojt.web.config;														
														
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.digitalojt.web.consts.UrlConsts;

import lombok.RequiredArgsConstructor;														
														
/**														
 * WebSecurityConfig														
 *														
 * @author haruka matano														
 * 														
 */														
@Configuration														
@EnableWebSecurity														
@RequiredArgsConstructor														
public class WebSecurityConfig {														
														
	/** パスワードエンコーダー */													
	private final PasswordEncoder passwordEncoder;													
														
	/** ユーザー情報取得Service */													
	private final UserDetailsService userDetailsService;													
														
	// 管理者IDのname属性													
	private final String ADMIN_ID = "adminId";													
														
	/**													
	 * Spring Securityカスタマイズ用													
	 * 													
	 * @param http													
	 * @return カスタマイズ結果													
	 * @throws Exception													
	 */													
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
	{
	    http
	        .authorizeHttpRequests(
	            authorize -> authorize.requestMatchers(UrlConsts.NO_AUTHENTICATION).permitAll()    
	                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // 静的リソースを許可
	                .anyRequest().authenticated())
	        .formLogin(
	            login -> login.loginPage(UrlConsts.LOGIN)   
	            	.loginProcessingUrl(UrlConsts.AUTHENTICATE) // ログイン処理のURLを指定
	                .usernameParameter(ADMIN_ID)             
	                .passwordParameter("password") // パスワードフィールドのname属性を指定
	                .defaultSuccessUrl(UrlConsts.STOCK_LIST) // 認証成功時のリダイレクト先
	                .failureUrl(UrlConsts.LOGIN + "?error=true")) // 認証失敗時のリダイレクト先を追加
	        .logout(logout -> logout.logoutSuccessUrl(UrlConsts.LOGIN));                                        

	    return http.build();                                                  
	}
												
														
	/**													
	 * Provider定義													
	 * 													
	 * @return カスタマイズProvider情報													
	 */													
	@Bean													
	AuthenticationProvider daoAuthenticationProvider() 
	{													
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();												
		provider.setUserDetailsService(userDetailsService);												
		provider.setPasswordEncoder(passwordEncoder);																								
		return provider;												
	}													
}														
