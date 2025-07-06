package jjgg.academysystem.services.Implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import jjgg.academysystem.DTO.UserCreateDTO;
import jjgg.academysystem.DTO.UserResponseDTO;
import jjgg.academysystem.DTO.UserUpdateDTO;
import jjgg.academysystem.entities.Rol;
import jjgg.academysystem.entities.User;
import jjgg.academysystem.exceptions.ResourceNotFoundException;
import jjgg.academysystem.exceptions.UserFoundException;
import jjgg.academysystem.mappers.UserMapper;
import jjgg.academysystem.repositories.RolRepository;
import jjgg.academysystem.repositories.UserRepository;
import jjgg.academysystem.services.StorageService;
import jjgg.academysystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private StorageService storageService;

    @Override
    public Set<UserResponseDTO> getallusers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponseDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public UserResponseDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user","id", id));
        return userMapper.toUserResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO saveUser(UserCreateDTO userCreateDTO, MultipartFile multipartFile) throws UserFoundException {
        User user = objectMapper.convertValue(userCreateDTO, User.class);
        User savedUserEntity = userRepository.save(user);

        String photoUrl;
        if (multipartFile != null && !multipartFile.isEmpty()) {
            // La lógica de construcción de URL se va. El método store() ya devuelve la URL completa.
            photoUrl = storageService.store(multipartFile);
        } else {
            // Opcional: puedes tener una imagen por defecto subida a tu contenedor de Azure
            // y poner aquí su URL fija. Por ahora, lo dejamos como null o vacío.
            photoUrl = "https://academysystemstorage.blob.core.windows.net/profile-pictures/default.jpg"; // ¡Ejemplo! Cambia esto por tu URL real.
        }

        // Asignación de rol
        Set<Rol> roles = new HashSet<>();
        Rol defaultRol = rolRepository.findById(2L).orElseThrow(() ->
                new RuntimeException("default role (user) not found"));
        roles.add(defaultRol);
        savedUserEntity.setRols(roles);

        savedUserEntity.setPhoto(photoUrl);
        savedUserEntity.setPassword(passwordEncoder.encode(savedUserEntity.getPassword()));
        savedUserEntity.setUsername(user.getDocument().toString());

        userRepository.save(savedUserEntity);

        // Mapeamos a DTO al final para tener todos los datos actualizados
        return userMapper.toUserResponseDTO(savedUserEntity);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserUpdateDTO userUpdateDTO, MultipartFile photoFile) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userMapper.updateUserFromDto(userUpdateDTO, existingUser);

        if (photoFile != null && !photoFile.isEmpty()) {
            String oldPhotoUrl = existingUser.getPhoto();

            // Borrar la foto antigua si no es la de por defecto
            if (oldPhotoUrl != null && !oldPhotoUrl.isBlank() && !isDefaultPhoto(oldPhotoUrl)) {
                try {
                    storageService.delete(oldPhotoUrl);
                } catch (IOException e) {
                    System.err.println("Could not delete old photo file: " + e.getMessage());
                }
            }

            // Subir la nueva foto y obtener su URL
            String newPhotoUrl = storageService.store(photoFile);
            existingUser.setPhoto(newPhotoUrl);
        }

        existingUser.setUsername(userUpdateDTO.getUsername()); // Mantienes esta lógica
        User updatedUser = userRepository.save(existingUser);

        return userMapper.toUserResponseDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private boolean isDefaultPhoto(String photoUrl) {
        return photoUrl != null && photoUrl.endsWith("/default.jpg");
    }
}


