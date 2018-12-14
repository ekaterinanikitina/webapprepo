package notebookwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import notebookwebapp.model.User;

/**
 * ќбъект <code> класса RegisterController</code> предоставл€ет
 * контроллер дл€ работы со страницей регистрации пользовател€
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/")
public class RegisterController {
	
	/**
	 * ќбрабатывает результат запроса "post" и 
	 * отображает страницу регистрации пользовател€
	 * @param error параметр запроса error
	 *              содержит сообщение об ошибке
	 * @param modelMap модель дл€ отправки на 
	 *                 страницу регистрации
	 *                 содержит пользовател€
	 * @return страница регистрации пользовател€
	 */
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String registerErrorPage(@RequestParam(value = "error", required = false)String error, 
			@RequestParam(value = "emptyName", required = false)String emptyName, 
			@RequestParam(value = "emptyPass", required = false)String emptyPass, 
			@RequestParam(value = "name", required = false)String userName, 
			@RequestParam(value = "pass", required = false)String userPass, 
			ModelMap modelMap) {
		if (error != null) {
			//modelMap.addAttribute("errorMessage", "User is already exist !!");
			modelMap.addAttribute("errorMessage", "ѕользовательуже существует");
		}
		if (emptyName != null) {
			//modelMap.addAttribute("errorMessage", "Name is empty !!");
			modelMap.addAttribute("errorMessage", "«аполните им€ пользовател€");
			modelMap.addAttribute("pass", userPass);
		}
		if (emptyPass != null) {
			//modelMap.addAttribute("errorMessage", "Password is empty !!");
			modelMap.addAttribute("errorMessage", "«аполните пароль");
			modelMap.addAttribute("name", userName);
		}
		modelMap.addAttribute("user", new User());
		return "register";
	}
}
