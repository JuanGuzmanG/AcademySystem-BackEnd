package jjgg.academysystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jjgg.academysystem.DTO.UserResponseDTO;
import jjgg.academysystem.DTO.UserUpdateDTO;
import jjgg.academysystem.entities.User;
import jjgg.academysystem.mappers.UserMapper;
import jjgg.academysystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

            UserResponseDTO updatedUserDto = userService.updateUser(id, userUpdateDTO, multipartFile);

            return ResponseEntity.ok(updatedUserDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
