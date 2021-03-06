package br.edu.ufcg.ccc.pharma.user;

import br.edu.ufcg.ccc.pharma.model.AbstractEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@SuppressWarnings({"unused"})
@Entity
@Table(name = "table_user")
public class User extends AbstractEntity {

    @Email(message = "'email' field not valid")
    @NotEmpty(message = "'email' field may not be empty")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "'password' field may not be empty")
    @Length(min = 6, message = "your password must have at least 6 characters")
    @Column(name = "password", nullable = false)
    private String password;

    private String role;

    public User() {}

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return this.getRole().toUpperCase().equals("ADMIN");
    }
}
