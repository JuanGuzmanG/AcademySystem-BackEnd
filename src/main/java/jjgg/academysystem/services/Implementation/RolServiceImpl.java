package jjgg.academysystem.services.Implementation;

import jjgg.academysystem.entities.Rol;
import jjgg.academysystem.repositories.RolRepository;
import jjgg.academysystem.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol findById(Long idRol) {
        return rolRepository.findById(idRol).get();
    }

    @Override
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public void delete(Rol rol) {
        rolRepository.delete(rol);
    }

    @Override
    public Set<Rol> findAll() {
        return new HashSet<>(rolRepository.findAll());
    }
}
