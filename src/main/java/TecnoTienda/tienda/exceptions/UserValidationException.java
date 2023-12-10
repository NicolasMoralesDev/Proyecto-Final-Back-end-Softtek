package TecnoTienda.tienda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for the validation of the users.
 * This exception is thrown when the user provides invalid data for the registration or login.
 * It set the HTTP status to 409 Conflict.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UserValidationException extends RuntimeException{
    public UserValidationException(String message) {
        super(message);
    }
}
