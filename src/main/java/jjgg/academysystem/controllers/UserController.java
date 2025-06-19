package jjgg.academysystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jjgg.academysystem.DTO.UserResponseDTO;
import jjgg.academysystem.DTO.UserUpdateDTO;
import jjgg.academysystem.entities.User;
import jjgg.academysystem.mappers.UserMapper;
import jjgg.academysystem.services.StorageService;
import jjgg.academysystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StorageService storageService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/get_user_{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("all_users")
    public ResponseEntity<Set<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getallusers());
    }

    @GetMapping("/userlogged")
    public ResponseEntity<UserResponseDTO> getLoggedUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = this.userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(userMapper.toUserResponseDTO(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestParam("user") String userJson,
            @RequestParam(value = "file", required = false) MultipartFile multipartFile) throws Exception {

            UserUpdateDTO userUpdateDTO = objectMapper.readValue(userJson, UserUpdateDTO.class);

            // 2. Delegar TODA la lógica al servicio, pasándole los datos recibidos
            UserResponseDTO updatedUserDto = userService.updateUser(id, userUpdateDTO, multipartFile);

            // 3. Devolver la respuesta.
            // Ya no necesitas try-catch aquí porque el GlobalExceptionHandler se encarga.
            return ResponseEntity.ok(updatedUserDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
