package notebookwebapp.dao;

import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import notebookwebapp.model.User;

/**
 * Объект <code> класса UserDaoImplementation</code> предоставляет
 * репозиторий для взаимодействия с данными User в бд
 * @version 1.0
 *
 */
@Repository
public class UserDaoImplementation implements UserDao {
	
	// Доступ к данным
	@Autowired
	private DataSource dataSource;
	
	// Объект, выполняющий запросы и изменения в таблицах бд
	private JdbcTemplate jdbcTemplate;
	
	// Объект, выполняющий маппинг результата объекту User
	private RowMapper<User> rowMapper = (ResultSet resultSet, int rowNum) -> {
		return new User(resultSet.getInt("number"), resultSet.getString("name"), resultSet.getString("password"));
	};
	
	/**
	 * Устанавливает объект доступа к данным
	 */
	@Autowired
	public void setDataSource() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}	

	/**
	 * Получает пользователя по имени
	 * @param name имя пользователя
	 * @return пользователь
	 */
	@Override
	public User getByName(String name) {
		try {
			String sql = "SELECT \"number\", name, password FROM \"user\" WHERE name = ?";
			return jdbcTemplate.queryForObject(sql, rowMapper, name);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * Регестрирует пользователя
	 * @param user незарегестрированный пользователь
	 */
	@Override
	public void add(User user) {
		String sql = "INSERT INTO \"user\"(name, password)VALUES(?, ?)";
		jdbcTemplate.update(sql, user.getName(), user.getPassword());
	}

}
