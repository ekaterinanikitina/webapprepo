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
 * ������ <code> ������ NotebookController</code> �������������
 * ���������� ��� ������ �� ��������� ������� ������������
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/notebook")
public class NotebookController {
	
	@Autowired
	private UserDao userDao;
	
	// ����������� ��� �������������� � ������� �� Note
	@Autowired
	private NoteDao noteDao;
	
	/**
	 * ������������ ��������� ������� "get" �
	 * ���������� �������� � ��������� ������������
	 * @param principal ������ ��������������� ������������
	 * @param modelMap ������ ��� �������� �� �������� � 
	 *                 ��������� ������������
	 *                 �������� ��� ������������, ������ ���
	 *                 �������
	 * @return ������������ �������� � ��������� ������������
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
	 * ��������� ����� ������� � ������
	 * ������������ ��������� ������� "post" �
	 * ���������� �������� � ��������� ������������
	 * @param newTitle ��������� ����� �������
	 * @param newText ����� ����� �������
	 * @param principal ������ ��������������� ������������
	 * @param modelMap ������ ��� �������� �� �������� � 
	 *                 ��������� ������������
	 *                 �������� ��� ������������, ������ ���
	 *                 �������
	 * @return ������������ �������� � ��������� ������������
	 */
	@RequestMapping(params = "add", method = RequestMethod.POST)
	public String addNote(@RequestParam("newTitle")String newTitle, 
			@RequestParam("newText")String newText, 
			Principal principal, ModelMap modelMap) {
		User user = userDao.getByName(principal.getName());
		if (newText == null || newText.isEmpty()) {
			// modelMap.addAttribute("errorMessage", "Adding error. Note text is empty !!");
			modelMap.addAttribute("errorMessage", "������ ����������. ��������� ����� �������");
		} else {
			noteDao.add(newTitle, newText, null, user);
		}
		List<Note> notes = noteDao.getList(user);
		modelMap.addAttribute("name", principal.getName());
		modelMap.addAttribute("notes", notes);
		return "notebook";
	}
	
	/**
	 * ������� ������ �������, ��������������� �������� ������
	 * ������������ ��������� ������� "post" �
	 * ���������� �������� � ��������� ������������
	 * @param fragment �������� ������
	 * @param principal ������ ��������������� ������������
	 * @param modelMap ������ ��� �������� �� �������� � 
	 *                 ��������� ������������
	 *                 �������� ��� ������������, �������� ������, 
	 *                 ������ ��� �������
	 * @return ������������ �������� � ��������� ������������
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
