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
import jjgg.academysystem.services.FileSystemStorageService;
import jjgg.academysystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileSystemStorageService fileStorageService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolRepository rolRepository;

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

    @Override
    @Transactional
    public UserResponseDTO saveUser(UserCreateDTO userCreateDTO, MultipartFile multipartFile) throws UserFoundException {

        User user = objectMapper.convertValue(userCreateDTO, User.class);
        User savedUserEntity = userRepository.save(user);
        UserResponseDTO savedUserResponse = objectMapper.convertValue(savedUserEntity, UserResponseDTO.class);

        String photoUrl;
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String path = fileStorageService.store(multipartFile);
            photoUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/media/")
                    .path(path)
                    .toUriString();
        } else {
            photoUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/media/")
                    .path("default.jpg")
                    .toUriString();
        }

        //role assignment
        Set<Rol> roles = new HashSet<>();
        Rol defaultRol = rolRepository.findById(2L).orElseThrow(() ->
                new RuntimeException("default role (user) not found"));
        roles.add(defaultRol);
        savedUserEntity.setRols(roles);

        savedUserEntity.setPhoto(photoUrl);
        savedUserEntity.setPassword(passwordEncoder.encode(savedUserEntity.getPassword()));
        savedUserEntity.setUsername(user.getDocument().toString());
        userRepository.save(savedUserEntity);
        savedUserResponse.setPhoto(photoUrl);
        return savedUserResponse;
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserUpdateDTO userUpdateDTO, MultipartFile photoFile) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User","id",id));

        userMapper.updateUserFromDto(userUpdateDTO, existingUser);

        if(photoFile != null && !photoFile.isEmpty()){
            String oldPhotoUrl = existingUser.getPhoto();
            if (oldPhotoUrl != null && !oldPhotoUrl.isEmpty() && !isDefaultPhoto(oldPhotoUrl)) {
                try {
                    String oldPhotoFilename = oldPhotoUrl.substring(oldPhotoUrl.lastIndexOf('/') + 1);
                    fileStorageService.delete(oldPhotoFilename);
                } catch (Exception e) {
                    System.err.println("Could not delete old photo file: " + e.getMessage());
                }
            }

            String newPhotoFilename = fileStorageService.store(photoFile);

            String newPhotoUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/media/")
                    .path(newPhotoFilename)
                    .toUriString();

            existingUser.setPhoto(newPhotoUrl);
        }
        existingUser.setUsername(userUpdateDTO.getUsername());
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
        return photoUrl.endsWith("/media/default.jpg");
    }
}


