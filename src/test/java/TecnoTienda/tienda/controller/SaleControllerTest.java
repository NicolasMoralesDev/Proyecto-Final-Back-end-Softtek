package TecnoTienda.tienda.controller;

import TecnoTienda.tienda.dto.CreateSaleRequestDTO;
import TecnoTienda.tienda.dto.SaleDTO;
import TecnoTienda.tienda.entity.Item;
import TecnoTienda.tienda.entity.Product;
import TecnoTienda.tienda.entity.Sale;
import TecnoTienda.tienda.entity.User;
import TecnoTienda.tienda.service.ProductService;
import TecnoTienda.tienda.service.SaleService;
import TecnoTienda.tienda.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SaleControllerTest {
    @InjectMocks
    private SaleController saleController;

    @Mock
    private SaleService saleService;

    @Mock
    private ProductService productService;

    @Mock
    private UserService userService;

    @Test
    public void testSaveSale() {
        // configurar dto solucitud
        CreateSaleRequestDTO requestDTO = new CreateSaleRequestDTO();


        SaleDTO expectedSaleDTO = new SaleDTO();
        when(saleService.saveSale(requestDTO)).thenReturn(expectedSaleDTO);

        // Llamada al metodo del controlador
        ResponseEntity<?> responseEntity = saleController.saveSale(requestDTO);

        // Verificación de resultados
        verify(saleService, times(1)).saveSale(requestDTO);

        // Asegúrate de el estado de la respuesta
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody()); // Asegura de que la respuesta no sea nula

    }
}
