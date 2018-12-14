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
 * ������ <code> ������ AutorizeController</code> �������������
 * ���������� ��� ������ �� ��������� �����������
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/")
public class AutorizeController {
	
	// ������ ���������� ������ � ������������ � ���������� MD5
	@Autowired
	private Md5PasswordEncoder passwordEncoder;
	
	// ����������� ��� �������������� � ������� �� User
	@Autowired
	private UserDao userDao;

	/**
	 * ������������ ��������� ������� "get" � 
	 * ���������� �������� �����������
	 * @param error �������� ������� error
	 * @param logout �������� ������� logout
	 * @param model ������ ��� �������� �� �������� �����������
	 *              �������� ��������� �� ������
	 * @return ������������ �������� �����������
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String authorizePage(@RequestParam(value = "error", required = false)String error, 
			@RequestParam(value = "logout", required = false)String logout, 
			Model model) {
		String errorMessage = null;
		if (error != null) {
			//errorMessage = "Username or Password is incorrect !!";
			errorMessage = "�������� ����� ��� ������. �������� ����� ��� ������, ��� �����������������.";
		}
		if (logout != null) {
			//errorMessage = "You have been successfully logged out !!";
			errorMessage = "�������� �������� ������.";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "authorize";
	}
	
	/**
	 * ������������ ��������� ������� "get" � ���������� 
	 * �������� ����������� ��� �������
	 * @param request
	 * @param response
	 * @return �������� ����������� ��� �������
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
	 * ������������ ��������� ������� "post" � ���������� 
	 * �������� ����������� ����� �������� ����������� ��� 
	 * �������������� �� ��������� �����������
	 * @param user ���������� � ������ � ����� �����������
	 * @param modelMap ������ ��� �������� �� �������� �����������
	 *                 �������� ����� � ������ ������������
	 * @return �������� ����������� ��� �����������
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
