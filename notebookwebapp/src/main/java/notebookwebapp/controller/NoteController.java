package notebookwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import notebookwebapp.dao.NoteDao;
import notebookwebapp.model.Note;

@Controller
@RequestMapping("/note")
public class NoteController {
	
	// Репозиторий для взаимодействия с данными бд Note
	@Autowired
	private NoteDao noteDao;

	/**
	 * Показывает выбранную в списке заметку
	 * Обрабатывает результат запроса "get" и
	 * отображает страницу с заметкой пользователя
	 * @param number уникальный идентификатор заметки
	 * @param modelMap модель для отправки на страницу с 
	 *                 заметкой пользователя
	 *                 Содержит уникальный идентификатор, 
	 *                 заголовок, текст, картинку заметки
	 * @return наименование страницы с заметкой
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String notePage(@RequestParam("number")int number, ModelMap modelMap) {
		Note note = noteDao.getByNumber(number);
		if (note != null) {
			modelMap.addAttribute("number", number);
			modelMap.addAttribute("title", note.getTitle());
			modelMap.addAttribute("text", note.getText());
			modelMap.addAttribute("identIcon", note.getIdentIcon());
		}
		return "note";
	}
	
	/**
	 * Переводит на страницу подтверждения или отмены удаления заметки
	 * Обрабатывает результат запроса "post" и
	 * отображает страницу с подтверждения
	 * @param number уникальный идентификатор заметки
	 * @return наименование страницы подтверждения или отмены удаления заметки
	 */
	@RequestMapping(method = RequestMethod.POST) 
	public String removeNote(@RequestParam("number")int number) {
		return "redirect:/confirm?number=" + number;
		//noteDao.remove(number);
		//return "redirect:/notebook";
	}
}
