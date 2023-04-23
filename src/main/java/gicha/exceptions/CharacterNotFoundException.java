package gicha.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception when no Character exists - {@link HttpStatus#GONE}.
 */

@ResponseStatus(value = HttpStatus.GONE)
public class CharacterNotFoundException extends RuntimeException {

}
