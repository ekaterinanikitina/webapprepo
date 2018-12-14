package notebookwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import notebookwebapp.dao.NoteDao;

@Controller
@RequestMapping("/confirm")
public class ConfirmController {
	
	// Репозиторий для взаимодействия с данными бд Note
	@Autowired
	private NoteDao noteDao;
	
	/**
	 * Обрабатывает результат запроса "get" и
	 * отображает страницу подтверждения или отмены удаления заметки
	 * @param number уникальный идентификатор заметки
	 * @param modelMap модель для отправки на страницу 
	 *                 подтверждения или отмены удаления заметки
	 * @return страница подтверждения или отмены удаления заметки
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String confirmPage(@RequestParam("number")int number, ModelMap modelMap) {
		modelMap.addAttribute("number", number);
		return "confirm";
	}
	
	/**
	 * Удаляет заметку из списка заметок
	 * Обрабатывает результат запроса "post" и
	 * отображает страницу заметок пользователя
	 * @param number уникальный идентификатор заметки
	 * @return переход на страницу заметок пользователя
	 */
	@RequestMapping(params = "remove", method = RequestMethod.POST)
	public String removeNote(@RequestParam("number")int number) {
		noteDao.remove(number);
		return "redirect:/notebook";
	}
	
	/**
	 * Отменяет удаление заметки из списка заметок
	 * @return переход на страницу заметок пользователя
	 */
	@RequestMapping(params = "noRemove", method = RequestMethod.POST)
	public String noRemoveNote() {
		return "redirect:/notebook";
	}

}
