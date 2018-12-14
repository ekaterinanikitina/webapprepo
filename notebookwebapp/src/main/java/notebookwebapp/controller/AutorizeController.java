package notebookwebapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import notebookwebapp.common.Md5PasswordEncoder;
import notebookwebapp.dao.UserDao;
import notebookwebapp.model.User;

/**
 * Объект <code> класса AutorizeController</code> предоставляет
 * контроллер для работы со страницей авторизации
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/")
public class AutorizeController {
	
	// Объект шифрования пароля в соответствии с алгоритмом MD5
	@Autowired
	private Md5PasswordEncoder passwordEncoder;
	
	// Репозиторий для взаимодействия с данными бд User
	@Autowired
	private UserDao userDao;

	/**
	 * Обрабатывает результат запроса "get" и 
	 * отображает страницу авторизации
	 * @param error параметр запроса error
	 * @param logout параметр запроса logout
	 * @param model модель для отправки на страницу авторизации
	 *              содержит сообщение об ошибке
	 * @return наименование страницы авторизации
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String authorizePage(@RequestParam(value = "error", required = false)String error, 
			@RequestParam(value = "logout", required = false)String logout, 
			Model model) {
		String errorMessage = null;
		if (error != null) {
			//errorMessage = "Username or Password is incorrect !!";
			errorMessage = "Неверный логин или пароль. Уточните логин или пароль, или зарегистрируйтесь.";
		}
		if (logout != null) {
			//errorMessage = "You have been successfully logged out !!";
			errorMessage = "Выполнен успешный логаут.";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "authorize";
	}
	
	/**
	 * Обрабатывает результат запроса "get" и отображает 
	 * страницу авторизации при логауте
	 * @param request
	 * @param response
	 * @return страница авторизации при логауте
	 */
	@RequestMapping(value ="logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
			
		}
		return "redirect:/login?logout=true";
	}
	
	/**
	 * Обрабатывает результат запроса "post" и отображает 
	 * страницу авторизации после успешной регистрации или 
	 * перенаправляет на повторную регистрацию
	 * @param user помещается в запрос с формы регистрации
	 * @param modelMap модель для отправки на страницу авторизации
	 *                 содержит логин и пароль пользователя
	 * @return страница авторизации или регистрации
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user")User user, ModelMap modelMap) {
		if (user.getName() == null || user.getName().isEmpty())
			return "redirect:/register?emptyName=true&pass=" + user.getPassword();
		if (user.getPassword() == null || user.getPassword().isEmpty())
			return "redirect:/register?emptyPass=true&name=" + user.getName();
		User currentUser = userDao.getByName(user.getName());
		if (currentUser == null) {
			currentUser = new User(user.getName(), passwordEncoder.encode(user.getPassword()));
			userDao.add(currentUser);
			modelMap.addAttribute("currentName", user.getName());
			modelMap.addAttribute("currentPassword", user.getPassword());
			return "authorize";
		} else {
			return "redirect:/register?error=true";
		}
	}
}
