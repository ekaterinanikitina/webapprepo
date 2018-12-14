package notebookwebapp.service;

import notebookwebapp.model.User;

/**
 * ������ <code> ������ ����������� AuthorizeService</code> ������������� 
 * ��������� ������������ ��� �����������
 * @version 1.0
 *
 */
public interface AuthorizeService {
	/**
	 * �������� ������������ �� ������������
	 * @param name ��� ������������
	 * @return ������������
	 */
	User getUser(String name);
}
