package jjgg.academysystem.services.Implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import jjgg.academysystem.DTO.UserCreateDTO;
import jjgg.academysystem.DTO.UserResponseDTO;
import jjgg.academysystem.DTO.UserUpdateDTO;
import jjgg.academysystem.entities.Rol;
import jjgg.academysystem.entities.User;
import jjgg.academysystem.exceptions.ResourceNotFoundException;
import jjgg.academysystem.mappers.UserMapper;
import jjgg.academysystem.repositories.RolRepository;
import jjgg.academysystem.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceImplTest{

    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RolRepository rolRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // auxiliary methods to build DTOs and entities
    private UserCreateDTO buildUserCreateDTO() {
        UserCreateDTO dto = new UserCreateDTO();
        dto.setDocument(12345678L);
        dto.setPassword("pass");
        return dto;
    }

    private User buildUser() {
        User user = new User();
        user.setPassword("pass");
        user.setDocument(12345678L);
        return user;
    }

    private Rol buildRol() {
        Rol rol = new Rol();
        rol.setRolId(2L);
        return rol;
    }

    //-------- tests -------------
    //getallusers
    @Test
    void getAll_shouldReturnAllUsersAsUserResponseDTOs() {
        //Arrange
        User user = new User();
        user.setDocument(12345678L);
        User user2 = new User();
        user2.setDocument(123123L);

        UserResponseDTO dto1 = new UserResponseDTO();
        UserResponseDTO dto2 = new UserResponseDTO();

        List<User> users = List.of(user, user2);

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toUserResponseDTO(user)).thenReturn(dto1);
        when(userMapper.toUserResponseDTO(user2)).thenReturn(dto2);

        //Act
        Set<UserResponseDTO> result = userServiceImpl.getallusers();

        //Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
        verify(userRepository).findAll();
        verify(userMapper).toUserResponseDTO(user);
        verify(userMapper).toUserResponseDTO(user2);
    }
    @Test
    void getAll_shouldReturnEmptySetWhenNoUsersExist(){
        //Arrange
        when(userRepository.findAll()).thenReturn(List.of());

        //Act
        Set<UserResponseDTO> result = userServiceImpl.getallusers();

        //Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(userRepository).findAll();
        verifyNoInteractions(userMapper);
    }
    // getUser
    @Test
    void getUser_shouldThrowResourceNotFoundException_whenUserDoesnotExist() {
        //Arrange
        Long document = 99L;
        when(userRepository.findById(document)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.getUser(document));
        verify(userRepository).findById(document);
    }
    @Test
    void getUser_shouldReturnUserResponseDTO_whenUserExists() {
        //Arrange
        Long document = 12345678L;
        User user = buildUser();
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        when(userRepository.findById(document)).thenReturn(Optional.of(user));
        when(userMapper.toUserResponseDTO(user)).thenReturn(userResponseDTO);

        //Act
        UserResponseDTO result = userServiceImpl.getUser(document);

        //Assert
        assertNotNull(result);
        assertEquals(userResponseDTO, result);
        verify(userRepository).findById(document);
        verify(userMapper).toUserResponseDTO(user);
    }
    //saveUser
    @Test
    void saveUser_shouldSaveUserWithDefaultRoleAndPhoto() throws Exception {
        // Arrange
        UserCreateDTO dto = buildUserCreateDTO();
        User user = buildUser();
        Rol rol = buildRol();

        when(objectMapper.convertValue(dto, User.class)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(rolRepository.findById(2L)).thenReturn(Optional.of(rol));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPass");
        when(userMapper.toUserResponseDTO(any(User.class))).thenReturn(new UserResponseDTO());

        // Act
        UserResponseDTO result = userServiceImpl.saveUser(dto, null);

        // Assert
        assertNotNull(result);
        verify(userRepository, times(2)).save(any(User.class));
        verify(rolRepository).findById(2L);
        verify(passwordEncoder).encode(anyString());
        verify(userMapper).toUserResponseDTO(any(User.class));
    }

    @Test
    void saveUser_shouldThrowRuntimeException_whenDefaultRoleNotFound() {
        //Arrange
        UserCreateDTO dto = buildUserCreateDTO();
        User user = buildUser();

        when(objectMapper.convertValue(dto, User.class)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(rolRepository.findById(2L)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(RuntimeException.class, () -> userServiceImpl.saveUser(dto, null));
        verify(rolRepository).findById(2L);
    }
    //updateUser
    @Test
    void updateUser_shouldThrowResourceNotFoundException_whenUserDoesNotExist() {
        //Arrange
        Long document = 99L;
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        when(userRepository.findById(document)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(ResourceNotFoundException.class, () ->
                userServiceImpl.updateUser(document, userUpdateDTO, null));
        verify(userRepository).findById(document);
    }
    //deleteUser
    @Test
    void deleteUser_shouldThrowResourcenotFoundException_whenUserFoesNotExist() {
        //Arrange
        Long document = 99L;
        when(userRepository.findById(document)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.deleteUser(document));
        verify(userRepository).findById(document);
    }
    //getUserByUsername
    @Test
    void getUserByUsername_shouldThrowRuntimeException_whenUserNotFound() {
        //Arrange
        String username = "noone";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(RuntimeException.class, () -> userServiceImpl.getUserByUsername(username));
        verify(userRepository).findByUsername(username);
    }
}