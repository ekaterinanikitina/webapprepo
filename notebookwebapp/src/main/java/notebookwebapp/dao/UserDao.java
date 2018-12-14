package notebookwebapp.dao;

import notebookwebapp.model.User;

/**
 * ������ <code> ������ ������������ UserDao</code> �������������
 * ��������, ������� ����� ����������� � �������� User � ��
 */
public interface UserDao {
	/**
	 * �������� ������������ �� �����
	 * @param name ��� ������������
	 * @return ������������
	 */
	User getByName(String name);
	
	/**
	 * ������������ ������������
	 * @param user �������������������� ������������
	 */
	void add(User user);
}
