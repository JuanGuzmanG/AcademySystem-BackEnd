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

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "rol")
    private Set<userRol> userRols = new HashSet<>();

    public Rol() {
    }

    public Rol(Long idRol, String nameRol, Set<userRol> userRols) {
        this.idRol = idRol;
        this.nameRol = nameRol;
        this.userRols = userRols;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "idRol=" + idRol +
                ", nameRol='" + nameRol + '\'' +
                ", userRols=" + userRols +
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

    public Set<userRol> getUserRols() {
        return userRols;
    }

    public void setUserRols(Set<userRol> userRols) {
        this.userRols = userRols;
    }
}
