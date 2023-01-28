package Gicha.Exceptions;

public class CharacterNotFoundException extends RuntimeException {
    private String message;

    public CharacterNotFoundException() {
        super();
        this.message = "Character not found!";
    }
    public CharacterNotFoundException(String mg) {
        super();
        this.message = mg;
    }
}
