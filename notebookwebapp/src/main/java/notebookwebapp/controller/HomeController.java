package notebookwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ������ <code> ������ HomeController</code> ������������� 
 * ���������� ��� ������ �� ��������� �����������
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/")
public class HomeController {
	
	/**
	 * ������������ ��������� ������� "get" � 
	 * ���������� �������� �����������
	 * @return �������� �����������
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String homePage() {
		return "home";
	}

}
