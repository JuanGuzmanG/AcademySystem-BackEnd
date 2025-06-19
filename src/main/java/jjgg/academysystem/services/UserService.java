package jjgg.academysystem.services;

import jjgg.academysystem.DTO.UserCreateDTO;
import jjgg.academysystem.DTO.UserResponseDTO;
import jjgg.academysystem.DTO.UserUpdateDTO;
import jjgg.academysystem.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface UserService {

    Set<UserResponseDTO> getallusers();

    UserResponseDTO getUser(Long id);

    UserResponseDTO saveUser(UserCreateDTO userCreateDTO) throws Exception;

    UserResponseDTO updateUser(Long id, UserUpdateDTO userUpdateDTO, MultipartFile photoFile) throws Exception;

    void deleteUser(Long id);

    User getUserByUsername(String username);
}
