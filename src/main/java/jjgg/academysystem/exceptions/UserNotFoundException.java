package jjgg.academysystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long id) {
        super(String.format("%s Not found user with id: %s: '%s'", id));
    }
    public UserNotFoundException(String username) {
        super(String.format("Not found user with username: '%s'", username));
    }
}
