package notebookwebapp.dao;

import java.util.List;

import notebookwebapp.model.Note;
import notebookwebapp.model.User;

/**
 * Объект <code> класса реализующего NoteDao</code> предоставляет
 * операции, которые будут выполняться с объектом Note в бд
 */
public interface NoteDao {
	/**
     * Получает список всех заметок созданных пользователем
     * @param user пользователь
     * @return список всех заметок
     */
    List<Note> getList(User user);

    /**
     * Получает заметку по уникальному идентификатору
     * @param number уникальный идентификатор
     * @return заметка
     */
    Note getByNumber(int number);

    /**
     * Ищет заметки по указанному фрагменту текста
     * @param text фрагмент текста
     * @param user пользователь
     * @return список заметок, соответствующих критерию поиска
     */
    List<Note> find(String text, User user);

    /**
     * Добавляет новую заметку
     * @param note заметка, которую необходимо добавить
     * @param user пользователь
     */
    void add(String title, String text, String identIcon, User user);

    /**
     * Удаляет существующую заметку
     * @param note заметка, которую требуется удалить
     */
    void remove(int number);
}
