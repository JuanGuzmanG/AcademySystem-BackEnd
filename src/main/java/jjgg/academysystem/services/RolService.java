package jjgg.academysystem.services;

import jjgg.academysystem.entities.Rol;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RolService {
    Rol findById(Long idRol);

    Rol save(Rol rol);

    void delete(Rol rol);

    Set<Rol> findAll();

}
