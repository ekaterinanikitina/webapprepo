package notebookwebapp.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import notebookwebapp.common.Md5PasswordEncoder;
import notebookwebapp.service.UserDetailsServiceImplementation;

/**
 * ������ <code> ������ WebConfig</code> ������������� 
 * ������� ������������ web ����������
 * @version 1.0
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"notebookwebapp"})
public class WebConfig implements WebMvcConfigurer{

	// ������ ��� ������ ������������� �� ������������
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	// ������ ��� ��������� ������ ������������
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImplementation();
	}
	
	// ������ ���������� ������ � ������������ � ���������� MD5
	@Bean
	public Md5PasswordEncoder getMd5PasswordEncoder() {
		return new Md5PasswordEncoder();
	}
	
	// ������ ������� � ������
	@Bean
	public DataSource dbDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/notebookdb");
		dataSource.setUsername("postgres");
		dataSource.setPassword("123");
		return dataSource;
	}
}
