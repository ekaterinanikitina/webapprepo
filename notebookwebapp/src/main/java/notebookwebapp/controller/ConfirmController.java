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
	
	// ����������� ��� �������������� � ������� �� Note
	@Autowired
	private NoteDao noteDao;
	
	/**
	 * ������������ ��������� ������� "get" �
	 * ���������� �������� ������������� ��� ������ �������� �������
	 * @param number ���������� ������������� �������
	 * @param modelMap ������ ��� �������� �� �������� 
	 *                 ������������� ��� ������ �������� �������
	 * @return �������� ������������� ��� ������ �������� �������
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String confirmPage(@RequestParam("number")int number, ModelMap modelMap) {
		modelMap.addAttribute("number", number);
		return "confirm";
	}
	
	/**
	 * ������� ������� �� ������ �������
	 * ������������ ��������� ������� "post" �
	 * ���������� �������� ������� ������������
	 * @param number ���������� ������������� �������
	 * @return ������� �� �������� ������� ������������
	 */
	@RequestMapping(params = "remove", method = RequestMethod.POST)
	public String removeNote(@RequestParam("number")int number) {
		noteDao.remove(number);
		return "redirect:/notebook";
	}
	
	/**
	 * �������� �������� ������� �� ������ �������
	 * @return ������� �� �������� ������� ������������
	 */
	@RequestMapping(params = "noRemove", method = RequestMethod.POST)
	public String noRemoveNote() {
		return "redirect:/notebook";
	}

}
