package com.digitalojt.web.authentication;														
														
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.digitalojt.web.entity.AdminInfo;
import com.digitalojt.web.repository.AdminInfoRepository;

import lombok.RequiredArgsConstructor;														
														
/**														
 * ユーザー情報生成														
 *														
 * @author haruka matano														
 * 														
 */														
@Component														
@RequiredArgsConstructor														
public class UserDetailsServiceImpl implements UserDetailsService {														
														
	// 管理者情報リポジトリー													
	private final AdminInfoRepository adminRepository;													
														
	/**													
	 * ユーザー情報生成													
	 * 													
	 * @param ログインID													
	 * @throws UsernameNotFoundException													
	 */													
	@Override													
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{													
		System.out.println("Loading user by username: " + username); // デバッグ用ログ												
		AdminInfo adminInfo = adminRepository.findByAdminId(username)												
				.orElseThrow(() -> new UsernameNotFoundException(username));										
		
		System.out.println("Admin info: " + adminInfo); // デバッグ用ログ
		return User.withUsername(adminInfo.getAdminId())												
				.password(adminInfo.getPassword())										
				.roles("ADMIN")				// ロールの設定(今回はADMINのみ)						
				.build();										
	}													
														
}														
