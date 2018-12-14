package notebookwebapp.dao;

import notebookwebapp.model.User;

/**
 * Объект <code> класса реализующего UserDao</code> предоставляет
 * операции, которые будут выполняться с объектом User в бд
 */
public interface UserDao {
	/**
	 * Получает пользователя по имени
	 * @param name имя пользователя
	 * @return пользователь
	 */
	User getByName(String name);
	
	/**
	 * Регестрирует пользователя
	 * @param user незарегестрированный пользователь
	 */
	void add(User user);
}
