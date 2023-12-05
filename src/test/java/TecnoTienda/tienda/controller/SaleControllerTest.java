package TecnoTienda.tienda.controller;

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
        // Configuración del servicio simulado
        int userId = 1;
        List<Item> itemList = new ArrayList<>();

        // Crear un producto y un ítem asociado
        Product product = new Product();
        product.setId(1);

        Item item = new Item();
        item.setProduct(product);
        item.setAmount(1);
        itemList.add(item);

        // Crear un usuario y configurar el servicio simulado
        User user = new User();
        when(userService.findById(userId)).thenReturn(user);

        // Configurar el servicio simulado para devolver el producto cuando se llame al método findById
        when(productService.findById(anyInt())).thenReturn(Optional.of(product));

        // Llamada al controlador y verificación de resultados
        ResponseEntity<?> responseEntity = saleController.saveSale(itemList, userId);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Verificar que los métodos del servicio se hayan llamado según lo esperado
        verify(userService, times(1)).findById(userId);
        verify(productService, times(itemList.size())).findById(anyInt());
        verify(saleService, times(1)).saveSale(any(Sale.class));
    }
}
