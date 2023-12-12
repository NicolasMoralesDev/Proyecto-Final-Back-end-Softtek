package TecnoTienda.tienda.service.ServiceImp;

import TecnoTienda.tienda.dto.MercadoPagoDTO;
import TecnoTienda.tienda.service.IMpService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nico Morales
 */

@Service
public class MpService implements IMpService {

    @Override
    public String OrderMp(MercadoPagoDTO data) {

        // Agrega credenciales
        MercadoPagoConfig.setAccessToken("TEST-1981036742439068-120723-a8af31fd8a97e327259d4dbb49598927-758580456");

        PreferenceItemRequest itemRequest
                = PreferenceItemRequest.builder()
                        .id(data.getId())
                        .title("TecnoCompro")
                        .description("Pago de compra")
                        .pictureUrl("http://picture.com/PS5")
                        .categoryId("Hardward y Equipos informaticos")
                        .quantity(data.getAmount())
                        .currencyId("ARS")
                        .unitPrice(data.getPrice())
                        .build();
        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items).build();
        PreferenceClient client = new PreferenceClient();
        try {
            Preference preference = client.create(preferenceRequest);
            return preference.getInitPoint();
        } catch (MPException ex) {
            Logger.getLogger(MpService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MPApiException ex) {
            Logger.getLogger(MpService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "error";
    }

}
