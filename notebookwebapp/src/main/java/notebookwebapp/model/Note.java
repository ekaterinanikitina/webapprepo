package notebookwebapp.model;

import java.util.Objects;

/**
 * ������ <code> ������ Note</code> ������������� 
 * ���������������� �������
 * @version 1.0
 *
 */
public class Note {
	// ���� ������
    // ���������� �������������
    private int number;
    // ��������� �������
    private String title;
    // ����� �������
    private String text;
    // �������� � �������
    private String identIcon;

    // ������ ������� � ����� ������
    public int getNumber() {
        return number;
    }
    public void setId(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    
    public String getIdentIcon() {
    	return identIcon;
    }
    
    public void setIdentIcon(String identIcon) {
    	this.identIcon = identIcon;
    }

    // ������������
    /**
     * ��� ����������
     */
    public Note() {

    }
    
    /**
     * � �����������
     * @param title ��������� �������
     * @param text ����� �������
     */
    public Note(String title, String text) {
    	this.title = title;
        this.text = text;
    }

    /**
     * � �����������
     * @param title ��������� �������
     * @param text ����� �������
     * @param identIcon �������� � �������
     */
    public Note(String title, String text, String identIcon) {
        this(title, text);
        this.identIcon = identIcon;
    }
    
    /**
     * � �����������
     * @param number ���������� �������������
     * @param title ��������� �������
     * @param text ����� �������
     * @param identIcon �������� � �������
     */
    public Note(int number, String title, String text, String identIcon) {
    	this(title, text, identIcon);
    	this.number = number;
    }
    // ������, ����������� �� Object
    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        Note note = (Note) object;
        return number == note.getNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text, identIcon);
    }
}
