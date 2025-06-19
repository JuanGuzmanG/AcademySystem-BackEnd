package jjgg.academysystem.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResourceAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s already exists with %s: '%s'", resourceName, fieldName, fieldValue));
    }
}
