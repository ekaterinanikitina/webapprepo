package notebookwebapp.service;

import notebookwebapp.model.User;

/**
 * Объект <code> класса реализующий AuthorizeService</code> предоставляет 
 * получение пользователя при авторизации
 * @version 1.0
 *
 */
public interface AuthorizeService {
	/**
	 * Получает пользователя по наименованию
	 * @param name имя пользователя
	 * @return пользователь
	 */
	User getUser(String name);
}
