package notebookwebapp.dao;

import java.util.List;

import notebookwebapp.model.Note;
import notebookwebapp.model.User;

/**
 * ������ <code> ������ ������������ NoteDao</code> �������������
 * ��������, ������� ����� ����������� � �������� Note � ��
 */
public interface NoteDao {
	/**
     * �������� ������ ���� ������� ��������� �������������
     * @param user ������������
     * @return ������ ���� �������
     */
    List<Note> getList(User user);

    /**
     * �������� ������� �� ����������� ��������������
     * @param number ���������� �������������
     * @return �������
     */
    Note getByNumber(int number);

    /**
     * ���� ������� �� ���������� ��������� ������
     * @param text �������� ������
     * @param user ������������
     * @return ������ �������, ��������������� �������� ������
     */
    List<Note> find(String text, User user);

    /**
     * ��������� ����� �������
     * @param note �������, ������� ���������� ��������
     * @param user ������������
     */
    void add(String title, String text, String identIcon, User user);

    /**
     * ������� ������������ �������
     * @param note �������, ������� ��������� �������
     */
    void remove(int number);
}
