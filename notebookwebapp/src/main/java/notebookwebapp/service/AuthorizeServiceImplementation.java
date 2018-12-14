package notebookwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import notebookwebapp.dao.UserDao;
import notebookwebapp.model.User;

/**
 * ������ <code> ������ AuthorizeServiceImplementation</code> ������������� 
 * ������ ��� ����������� ������������
 * @version 1.0
 *
 */
@Service
public class AuthorizeServiceImplementation implements AuthorizeService {
	
	// ����������� ��� �������������� � ������� �� User
	@Autowired
	private UserDao userDao;

	@Override
	public User getUser(String name) {
		return userDao.getByName(name);
		//return new User("ka", "202cb962ac59075b964b07152d234b70");
	}

}
