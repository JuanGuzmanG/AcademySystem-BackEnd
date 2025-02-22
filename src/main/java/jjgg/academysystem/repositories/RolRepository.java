package jjgg.academysystem.repositories;

import jjgg.academysystem.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol,Long> {

    public Rol findByIdRol(Long idRol);
}
