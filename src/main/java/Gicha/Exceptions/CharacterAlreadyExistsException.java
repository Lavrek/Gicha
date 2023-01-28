package Gicha.Exceptions;

public class CharacterAlreadyExistsException extends RuntimeException {
    private String message;

    public CharacterAlreadyExistsException() {
        super();
        this.message = "Character already exists!";
    }
}
