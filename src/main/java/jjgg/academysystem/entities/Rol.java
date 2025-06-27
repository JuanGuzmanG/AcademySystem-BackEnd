package jjgg.academysystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    private Long rolId;
    private String rolName;

    @ManyToMany(mappedBy = "rols")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public Rol() {
    }

    public Rol(Long rolId, String rolName, Set<User> users) {
        this.rolId = rolId;
        this.rolName = rolName;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "idRol=" + rolId +
                ", nameRol='" + rolName + '\'' +
                '}';
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long idRol) {
        this.rolId = idRol;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String nameRol) {
        this.rolName = nameRol;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> userRols) {
        this.users = userRols;
    }
}
