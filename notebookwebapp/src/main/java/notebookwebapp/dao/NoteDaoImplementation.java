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
 * Объект <code> класса NoteDaoImplementation</code> предоставляет
 * репозиторий для взаимодействия с данными Note в бд
 * @version 1.0
 *
 */
@Repository
public class NoteDaoImplementation implements NoteDao {


	//Доступ к данным
	@Autowired
	private DataSource dataSource;
	
	// Объект для выполнения запросов и изменений таблиц
	private JdbcTemplate jdbcTemplate;
	
	// Выполняет маппинг результата объекту Note
	private RowMapper<Note> rowMapper = (ResultSet resultSet, int rowNum) -> {
		return new Note(resultSet.getInt("number"), resultSet.getString("title"), resultSet.getString("text"), resultSet.getString("ident_icon"));
	};
	
	/**
	 * Устанавливает объект DataSource
	 */
	@Autowired
	public void setDataSource() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
     * Получает список всех заметок созданных пользователем
     * @param user пользователь, создавший заметки
     * @return список всех заметок созданных пользователем
     */
	@Override
	public List<Note> getList(User user) {
		String sql = "SELECT \"number\", title, text, ident_icon FROM \"note\" WHERE \"user\" = ?";
        return jdbcTemplate.query(sql, rowMapper, user.getNumber());
	}

	/**
     * Получает заметку по уникальному идентификатору
     * @param number уникальный идентификатор
     * @return заметка
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
     * Находит список заметок, созданных пользователем, по указанному
     * фрагменту текста
     * @param text фрагмент текста
     * @param user пользователь, создавший заметки
     * @return список заметок, соответствующих критерию поиска
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
     * Добавляет новую заметку пользователя в список
     * @param note заметка
     * @param user пользователь, создавший заметку
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
     * Удаляет заметку из списка
     * @param note заметка
     */
	@Override
	public void remove(int number) {
		String sql = "DELETE FROM \"note\" WHERE \"number\" = ?";
        jdbcTemplate.update(sql, number);
	}

}
