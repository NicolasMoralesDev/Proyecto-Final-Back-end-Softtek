package TecnoTienda.tienda.service;

import TecnoTienda.tienda.entity.AuthenticationResponse;
import TecnoTienda.tienda.entity.LoginRequest;
import TecnoTienda.tienda.entity.RegisterRequest;

public interface AuthService {

    /**
     * Method for login a user in the system. This method validate the user credentials and generate a token.
     * Then, the token is returned in a AuthenticationResponse object.
     * @param request LoginRequest, contains the user data.
     * @return AuthenticationResponse, contains the token or an error.
     */
    AuthenticationResponse login(LoginRequest request);

    /**
     * Method for register a new user in the system. This method validate the user data and generate a token.
     * Then, the token is returned in a AuthenticationResponse object.
     * @param request RegisterRequest, contains the user data.
     * @return AuthenticationResponse, contains the token or an error.
     */
    AuthenticationResponse register(RegisterRequest request);
}
