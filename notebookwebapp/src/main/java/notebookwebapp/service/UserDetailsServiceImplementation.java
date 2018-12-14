package notebookwebapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import notebookwebapp.model.User;

/**
 * Объект <code> класса UserDetailsServiceImplementation</code> предоставляет 
 * сервис для получения данных пользователя
 * @version 1.0
 *
 */
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

	// Объект для получения пользователя при авторизации
	@Autowired
	private AuthorizeService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = service.getUser(username);
		if (user == null)
			throw new UsernameNotFoundException(username);
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("USER"));
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), roles);
	}

}
