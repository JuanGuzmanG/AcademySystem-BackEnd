package jjgg.academysystem.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("The user with that document does not exists in the Database");
    }
    public UserNotFoundException(String message) {
        super(message);
    }
}
