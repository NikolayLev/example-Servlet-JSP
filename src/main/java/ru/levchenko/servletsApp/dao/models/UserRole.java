package ru.levchenko.servletsApp.dao.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class UserRole {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> users;

    @Enumerated(EnumType.STRING)
    private UserRolesENUM role;

    public UserRolesENUM getRole() {
        return role;
    }

    public void setRole(UserRolesENUM role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        return role == userRole.role;
    }

    @Override
    public int hashCode() {
        return role != null ? role.hashCode() : 0;
    }

    public UserRole() {
    }

    public UserRole(UserRolesENUM userRolesENUM) {
        this.setRole(userRolesENUM);
    }
}





