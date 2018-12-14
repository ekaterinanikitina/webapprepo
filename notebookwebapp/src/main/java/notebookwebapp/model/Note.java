package notebookwebapp.model;

import java.util.Objects;

/**
 * Объект <code> класса Note</code> предоставляет 
 * пользовательскую заметку
 * @version 1.0
 *
 */
public class Note {
	// Поля класса
    // Уникальный идентификатор
    private int number;
    // Заголовок заметки
    private String title;
    // Текст заметки
    private String text;
    // Картинка к заметке
    private String identIcon;

    // Методы доступа к полям класса
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

    // Конструкторы
    /**
     * без параметров
     */
    public Note() {

    }
    
    /**
     * С параметрами
     * @param title заголовок заметки
     * @param text текст заметки
     */
    public Note(String title, String text) {
    	this.title = title;
        this.text = text;
    }

    /**
     * С параметрами
     * @param title заголовок заметки
     * @param text текст заметки
     * @param identIcon картинка к заметке
     */
    public Note(String title, String text, String identIcon) {
        this(title, text);
        this.identIcon = identIcon;
    }
    
    /**
     * С параметрами
     * @param number уникальный идентификатор
     * @param title заголовок заметки
     * @param text текст заметки
     * @param identIcon картинка к заметке
     */
    public Note(int number, String title, String text, String identIcon) {
    	this(title, text, identIcon);
    	this.number = number;
    }
    // Методы, наследуемые от Object
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
