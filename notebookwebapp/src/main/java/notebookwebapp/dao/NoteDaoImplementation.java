package notebookwebapp.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import notebookwebapp.common.Utils;
import notebookwebapp.model.Note;
import notebookwebapp.model.User;

/**
 * ������ <code> ������ NoteDaoImplementation</code> �������������
 * ����������� ��� �������������� � ������� Note � ��
 * @version 1.0
 *
 */
@Repository
public class NoteDaoImplementation implements NoteDao {


	//������ � ������
	@Autowired
	private DataSource dataSource;
	
	// ������ ��� ���������� �������� � ��������� ������
	private JdbcTemplate jdbcTemplate;
	
	// ��������� ������� ���������� ������� Note
	private RowMapper<Note> rowMapper = (ResultSet resultSet, int rowNum) -> {
		return new Note(resultSet.getInt("number"), resultSet.getString("title"), resultSet.getString("text"), resultSet.getString("ident_icon"));
	};
	
	/**
	 * ������������� ������ DataSource
	 */
	@Autowired
	public void setDataSource() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
     * �������� ������ ���� ������� ��������� �������������
     * @param user ������������, ��������� �������
     * @return ������ ���� ������� ��������� �������������
     */
	@Override
	public List<Note> getList(User user) {
		String sql = "SELECT \"number\", title, text, ident_icon FROM \"note\" WHERE \"user\" = ?";
        return jdbcTemplate.query(sql, rowMapper, user.getNumber());
	}

	/**
     * �������� ������� �� ����������� ��������������
     * @param number ���������� �������������
     * @return �������
     */
	@Override
	public Note getByNumber(int number) {
		try {
			String sql = "SELECT \"number\", title, text, ident_icon FROM \"note\" WHERE \"number\" = ?";
			return jdbcTemplate.queryForObject(sql, rowMapper, number);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
     * ������� ������ �������, ��������� �������������, �� ����������
     * ��������� ������
     * @param text �������� ������
     * @param user ������������, ��������� �������
     * @return ������ �������, ��������������� �������� ������
     */
	@Override
	public List<Note> find(String text, User user) {
		List<Note> notes = getList(user);
		if (text == null || text.isEmpty())
			return notes;
		return notes.stream()
				.filter(note -> note.getText().toLowerCase().contains(text.toLowerCase()))
				.collect(Collectors.toList());
		//String sql = "SELECT \"number\", title, text, ident_icon FROM \"note\" WHERE \"user\" = ? AND text iLIKE CONCAT('%', ?, '%')";
        //return jdbcTemplate.query(sql, rowMapper, user.getNumber(), text);
	}

	/**
     * ��������� ����� ������� ������������ � ������
     * @param note �������
     * @param user ������������, ��������� �������
     */
	@Override
	public void add(String title, String text, String identIcon, User user) {
		if (title == null || title.isEmpty())
			title = Utils.getTitle(text);
		if (identIcon == null || identIcon.isEmpty()) 
			identIcon = Utils.getSvg();
		String sql = "INSERT INTO \"note\"(title, text, ident_icon, \"user\")VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, title, text, identIcon, user.getNumber());
	}

	/**
     * ������� ������� �� ������
     * @param note �������
     */
	@Override
	public void remove(int number) {
		String sql = "DELETE FROM \"note\" WHERE \"number\" = ?";
        jdbcTemplate.update(sql, number);
	}

}
