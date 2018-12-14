package notebookwebapp.model;

import java.util.Objects;

/**
 * Объект <code> класса User</code> предоставляет 
 * пользователя приложением notebookwebapp
 * @version 1.0
 *
 */
public class User {
	// Поля класса
    // Уникальный идентификатор
    private int number;
    // Имя пользователя
    private String name;
    // Пароль пользователя
    private String password;

    // Методы дорступа к полям класса
    public int getNumber() {
        return number;
    }
    public void setNumber(int id) {
        this.number = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // Конструкторы
    /**
     * Без параметров
     */
    public User() {

    }

    /**
     * С параметрами
     * @param name имя
     * @param password пароль
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    
    /**
     * С параметрами
     * @param number уникальный идентификатор
     * @param name имя 
     * @param password пароль
     */
    public User(int number, String name, String password) {
    	this(name, password);
    	this.number = number;
    }

    // Методы, наследуемые от Object
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User user = (User) obj;
        return number == user.getNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
