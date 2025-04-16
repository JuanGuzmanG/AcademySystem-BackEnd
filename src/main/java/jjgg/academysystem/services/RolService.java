package jjgg.academysystem.services;

import jjgg.academysystem.entities.Rol;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface RolService {
    Rol findById(Long idRol);

    Rol findByNameRol(String nameRol);

    Rol save(Rol rol);

    void deleteById(Long idRol);

    void delete(Rol rol);

    Set<Rol> findAll();

}
