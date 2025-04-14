package jjgg.academysystem.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    private Long idRol;
    private String nameRol;

    @ManyToMany(mappedBy = "rols")
    private Set<User> users = new HashSet<>();

    public Rol() {
    }

    public Rol(Long idRol, String nameRol, Set<User> users) {
        this.idRol = idRol;
        this.nameRol = nameRol;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "idRol=" + idRol +
                ", nameRol='" + nameRol + '\'' +
                '}';
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNameRol() {
        return nameRol;
    }

    public void setNameRol(String nameRol) {
        this.nameRol = nameRol;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> userRols) {
        this.users = userRols;
    }
}
