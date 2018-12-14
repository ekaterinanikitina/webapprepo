package notebookwebapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import notebookwebapp.common.Md5PasswordEncoder;
import notebookwebapp.service.UserDetailsServiceImplementation;

/**
 * ������ <code> ������ SecurityConfig</code> ������������� �������
 * ������������ Spring Security
 * @version 1.0
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImplementation userDetailsService;
	
	@Autowired
	private Md5PasswordEncoder passwordEncoder;
	
	/**
	 * �������� � ������ ��������������� ������������
	 * @param builder ������ ������ AuthenticationManagerBuilder ��� �������� 
	 * ������������ � ���������
	 */
	@Autowired
	public void registerGlobalAuthentication(AuthenticationManagerBuilder builder) {
		try {
			builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) {
		try {
			// ��������, �� ��������� �����������, ������ ������������ ����
			httpSecurity.authorizeRequests().antMatchers("/", "/home", "/register").permitAll().anyRequest().authenticated();
			// ��� ������� ��������, ��������� �����������, ������������ ����� ��������� �� 
			// �������� ����������� � ��� �������� ������ ������������� �� ���������� 
			// ������������� ��������
			// ��� ���������� ������ ������������ ����� �������� �� login?error=true
			httpSecurity.formLogin().loginPage("/login").failureUrl("/login?error=true").permitAll().and()
			// ������, ��� �������� ������ ���������� �� login?logout=true, ������ ������ ���� �������� ���� �������������
			.logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll();
			// �������� ������ �� csrf ����
			httpSecurity.csrf().disable(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
