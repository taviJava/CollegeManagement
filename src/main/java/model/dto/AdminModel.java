package model.dto;

import javax.persistence.*;

@Entity
@Table(name="admin")
public class AdminModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;

    public AdminModel() {
        this.username = "username";
        this.password = "password";
    }

    public int getId() {
        return id;
    }

    public AdminModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AdminModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AdminModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
