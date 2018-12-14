package notebookwebapp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import notebookwebapp.dao.NoteDao;
import notebookwebapp.dao.UserDao;
import notebookwebapp.model.Note;
import notebookwebapp.model.User;

/**
 * Объект <code> класса NotebookController</code> предоставляет
 * контроллер для работы со страницей заметок пользователя
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/notebook")
public class NotebookController {
	
	@Autowired
	private UserDao userDao;
	
	// Репозиторий для взаимодействия с данными бд Note
	@Autowired
	private NoteDao noteDao;
	
	/**
	 * Обрабатывает результат запроса "get" и
	 * отображает страницу с заметками пользователя
	 * @param principal данные авторизованного пользователя
	 * @param modelMap модель для отправки на страницу с 
	 *                 заметками пользователя
	 *                 Содержит имя пользователя, список его
	 *                 заметок
	 * @return наименование страницы с заметками пользователя
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String notebookPage(Principal principal, ModelMap modelMap) {
		User user = userDao.getByName(principal.getName());
		List<Note> notes = noteDao.getList(user);
		modelMap.addAttribute("name", principal.getName());
		modelMap.addAttribute("notes", notes);
		return "notebook";
	}
	
	/**
	 * Добавляет новую заметку в список
	 * Обрабатывает результат запроса "post" и
	 * отображает страницу с заметками пользователя
	 * @param newTitle заголовок новой заметки
	 * @param newText текст новой заметки
	 * @param principal данные авторизованного пользователя
	 * @param modelMap модель для отправки на страницу с 
	 *                 заметками пользователя
	 *                 Содержит имя пользователя, список его
	 *                 заметок
	 * @return наименование страницы с заметками пользователя
	 */
	@RequestMapping(params = "add", method = RequestMethod.POST)
	public String addNote(@RequestParam("newTitle")String newTitle, 
			@RequestParam("newText")String newText, 
			Principal principal, ModelMap modelMap) {
		User user = userDao.getByName(principal.getName());
		if (newText == null || newText.isEmpty()) {
			// modelMap.addAttribute("errorMessage", "Adding error. Note text is empty !!");
			modelMap.addAttribute("errorMessage", "Ошибка добавления. Заполните текст заметки");
		} else {
			noteDao.add(newTitle, newText, null, user);
		}
		List<Note> notes = noteDao.getList(user);
		modelMap.addAttribute("name", principal.getName());
		modelMap.addAttribute("notes", notes);
		return "notebook";
	}
	
	/**
	 * Находит список заметок, соответствующий критерию поиска
	 * Обрабатывает результат запроса "post" и
	 * отображает страницу с заметками пользователя
	 * @param fragment критерий поиска
	 * @param principal данные авторизованного пользователя
	 * @param modelMap модель для отправки на страницу с 
	 *                 заметками пользователя
	 *                 Содержит имя пользователя, критерий поиска, 
	 *                 список его заметок
	 * @return наименование страницы с заметками пользователя
	 */
	@RequestMapping(params = "find", method = RequestMethod.POST)
	public String findNotes(@RequestParam("fragment")String fragment, 
			Principal principal, ModelMap modelMap) {
		User user = userDao.getByName(principal.getName());
		List<Note> notes = noteDao.find(fragment, user);
		modelMap.addAttribute("name", principal.getName());
		modelMap.addAttribute("fragment", fragment);
		modelMap.addAttribute("notes", notes);
		return "notebook";
	}
	

}
