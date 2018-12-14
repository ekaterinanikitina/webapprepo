package notebookwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Объект <code> класса HomeController</code> предоставляет 
 * контроллер для работы со страницей приветствия
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/")
public class HomeController {
	
	/**
	 * Обрабатывает результат запроса "get" и 
	 * отображает страницу приветствия
	 * @return страница приветствия
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String homePage() {
		return "home";
	}

}
