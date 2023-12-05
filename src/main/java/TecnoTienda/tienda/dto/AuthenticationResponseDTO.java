package TecnoTienda.tienda.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponseDTO {
    private String token;
}
