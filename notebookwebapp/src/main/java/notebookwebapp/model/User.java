package notebookwebapp.model;

import java.util.Objects;

/**
 * ������ <code> ������ User</code> ������������� 
 * ������������ ����������� notebookwebapp
 * @version 1.0
 *
 */
public class User {
	// ���� ������
    // ���������� �������������
    private int number;
    // ��� ������������
    private String name;
    // ������ ������������
    private String password;

    // ������ �������� � ����� ������
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

    // ������������
    /**
     * ��� ����������
     */
    public User() {

    }

    /**
     * � �����������
     * @param name ���
     * @param password ������
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    
    /**
     * � �����������
     * @param number ���������� �������������
     * @param name ��� 
     * @param password ������
     */
    public User(int number, String name, String password) {
    	this(name, password);
    	this.number = number;
    }

    // ������, ����������� �� Object
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
