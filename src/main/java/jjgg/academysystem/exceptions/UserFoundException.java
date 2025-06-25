package jjgg.academysystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class UserFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public UserFoundException(Long id) {
        super(String.format("%s already exists with %s: '%s'", id));
    }
}
