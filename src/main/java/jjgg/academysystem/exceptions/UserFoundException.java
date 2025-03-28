package jjgg.academysystem.exceptions;

public class UserFoundException extends Exception {
    public UserFoundException() {
        super("The user with that document already exists in the Database");
    }
    public UserFoundException(String message) {
        super(message);
    }
}
