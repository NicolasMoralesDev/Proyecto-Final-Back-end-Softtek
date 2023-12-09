package TecnoTienda.tienda.service;

import TecnoTienda.tienda.dto.AuthenticationResponseDTO;
import TecnoTienda.tienda.dto.LoginRequestDTO;
import TecnoTienda.tienda.dto.RecoverDTO;
import TecnoTienda.tienda.dto.RegisterRequestDTO;

public interface AuthService {

    /**
     * Method for login a user in the system. This method validate the user credentials and generate a token.
     * Then, the token is returned in a AuthenticationResponse object.
     * @param request LoginRequest, contains the user data.
     * @return AuthenticationResponse, contains the token or an error.
     */
    AuthenticationResponseDTO login(LoginRequestDTO request);

    /**
     * Method for register a new user in the system. This method validate the user data and generate a token.
     * Then, the token is returned in a AuthenticationResponse object.
     * @param request RegisterRequest, contains the user data.
     * @return AuthenticationResponse, contains the token or an error.
     */
    AuthenticationResponseDTO register(RegisterRequestDTO request);
    
    String recover(RecoverDTO request);
}
