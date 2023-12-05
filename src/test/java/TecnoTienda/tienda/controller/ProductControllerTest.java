package TecnoTienda.tienda.controller;

import TecnoTienda.tienda.dto.ProductDTO;
import TecnoTienda.tienda.entity.Product;
import TecnoTienda.tienda.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    public void testGetAllProduct() {
        // Configuración del servicio simulado
        int page = 1;
        List<Product> products = Collections.singletonList(new Product());

        // Crear un Page paginado con al menos una página
        Page<Product> pageResult = new PageImpl<>(products, PageRequest.of(0, 10), 1);

        Mockito.when(productService.getAllProducts(page)).thenReturn(pageResult);

        // Llamada al controlador y verificación de resultados
        ResponseEntity<?> responseEntity = productController.getAllProduct(page);
        // Verificar el código de estado
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());

        // Verificar el tipo de cuerpo
        assertTrue(responseEntity.getBody() instanceof ProductDTO);


        ProductDTO productDTO = (ProductDTO) responseEntity.getBody();

        assertTrue(productDTO.getProductos() != null && !productDTO.getProductos().isEmpty());
    }

    @Test
    public void testGetProductById() {
        // Configuración del servicio simulado
        int productId = 1;
        Product product = new Product();
        Mockito.when(productService.findById(productId)).thenReturn(Optional.of(product));

        // Llamada al controlador y verificación de resultados
        ResponseEntity<Product> responseEntity = productController.getProductById(productId);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());

    }

    @Test
    public void testGetProductByCategory() {
        // Configuración del servicio simulado
        String category = "gpu";
        int page = 1;
        Page<Product> products = new PageImpl<>(Collections.singletonList(new Product()));

        Mockito.when(productService.findByCategory(category, page)).thenReturn(products);

        // Llamada al controlador y verificación de resultados
        ResponseEntity<?> responseEntity = productController.getProductByCategory(category, page);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof ProductDTO);

        ProductDTO productDTO = (ProductDTO) responseEntity.getBody();
        assertTrue(productDTO.getProductos() != null && !productDTO.getProductos().isEmpty());

    }
}
