package jjgg.academysystem;

import jjgg.academysystem.entities.Rol;
import jjgg.academysystem.entities.User;
import jjgg.academysystem.entities.UserRol;
import jjgg.academysystem.services.Implementation.UserServiceImpl;
import jjgg.academysystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AcademySystemApplication implements CommandLineRunner {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    public static void main(String[] args) {
        SpringApplication.run(AcademySystemApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setDocument(123L);
        user.setUsername(String.valueOf(user.getDocument()));
        user.setFirstName("juan");
        user.setMiddleName("Jose");
        user.setLastName("Guzman");
        user.setSecondLastName("Gomez");
        user.setBirthDate(LocalDate.of(1995, 10, 10));
        user.setPassword("123");
        user.setPhoneNumber(1234562323L);
        user.setPhoto("url");
        user.setEmail("juan@hotmail.com");
        user.setDocumentType("CC");
        user.setCountryBirth("Colombia");

        Rol rol = new Rol();
        rol.setIdRol(1L);
        rol.setNameRol("admin");

        Set<UserRol> userRols  = new HashSet<>();
        UserRol userRol = new UserRol();
        userRol.setRol(rol);
        userRol.setUser(user);
        userRols.add(userRol);

        if(userService.getUser(user.getDocument()) == null){
            userServiceImpl.saveUser(user, userRols);

        }else{
            System.out.println("User already exists");
        }
    }
}
