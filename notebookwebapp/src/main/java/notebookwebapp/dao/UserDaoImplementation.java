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
 * ������ <code> ������ UserDaoImplementation</code> �������������
 * ����������� ��� �������������� � ������� User � ��
 * @version 1.0
 *
 */
@Repository
public class UserDaoImplementation implements UserDao {
	
	// ������ � ������
	@Autowired
	private DataSource dataSource;
	
	// ������, ����������� ������� � ��������� � �������� ��
	private JdbcTemplate jdbcTemplate;
	
	// ������, ����������� ������� ���������� ������� User
	private RowMapper<User> rowMapper = (ResultSet resultSet, int rowNum) -> {
		return new User(resultSet.getInt("number"), resultSet.getString("name"), resultSet.getString("password"));
	};
	
	/**
	 * ������������� ������ ������� � ������
	 */
	@Autowired
	public void setDataSource() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}	

	/**
	 * �������� ������������ �� �����
	 * @param name ��� ������������
	 * @return ������������
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
	 * ������������ ������������
	 * @param user �������������������� ������������
	 */
	@Override
	public void add(User user) {
		String sql = "INSERT INTO \"user\"(name, password)VALUES(?, ?)";
		jdbcTemplate.update(sql, user.getName(), user.getPassword());
	}

}
