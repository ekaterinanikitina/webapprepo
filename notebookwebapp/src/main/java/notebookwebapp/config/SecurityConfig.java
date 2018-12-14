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
 * Объект <code> класса SecurityConfig</code> предоставляет базовую
 * конфигурацию Spring Security
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
	 * Помещает в память авторизованного пользователя
	 * @param builder объект класса AuthenticationManagerBuilder для загрузки 
	 * пользователя в хранилище
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
			// страницы, не требующие авторизации, доступ предоставить всем
			httpSecurity.authorizeRequests().antMatchers("/", "/home", "/register").permitAll().anyRequest().authenticated();
			// при запросе страницы, требующей авторизации, пользователь будет отправлен на 
			// страницу авторизации и при успешном логине перенаправлен на предыдущую 
			// запрашиваемую страницу
			// при неуспешном логине пользователь будет напрвлен на login?error=true
			httpSecurity.formLogin().loginPage("/login").failureUrl("/login?error=true").permitAll().and()
			// логаут, при успешном выходе переходить на login?logout=true, логаут должен быть разрешен всем пользователям
			.logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll();
			// включить защиту от csrf атак
			httpSecurity.csrf().disable(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
