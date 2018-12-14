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
	
	// ����������� ��� �������������� � ������� �� Note
	@Autowired
	private NoteDao noteDao;

	/**
	 * ���������� ��������� � ������ �������
	 * ������������ ��������� ������� "get" �
	 * ���������� �������� � �������� ������������
	 * @param number ���������� ������������� �������
	 * @param modelMap ������ ��� �������� �� �������� � 
	 *                 �������� ������������
	 *                 �������� ���������� �������������, 
	 *                 ���������, �����, �������� �������
	 * @return ������������ �������� � ��������
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
	 * ��������� �� �������� ������������� ��� ������ �������� �������
	 * ������������ ��������� ������� "post" �
	 * ���������� �������� � �������������
	 * @param number ���������� ������������� �������
	 * @return ������������ �������� ������������� ��� ������ �������� �������
	 */
	@RequestMapping(method = RequestMethod.POST) 
	public String removeNote(@RequestParam("number")int number) {
		return "redirect:/confirm?number=" + number;
		//noteDao.remove(number);
		//return "redirect:/notebook";
	}
}
