package TecnoTienda.tienda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserValidationException extends RuntimeException{
    public UserValidationException(String message) {
        super(message);
    }
}
