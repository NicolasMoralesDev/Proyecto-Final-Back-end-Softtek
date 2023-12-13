package TecnoTienda.tienda.controller;

import TecnoTienda.tienda.controller.SaleController;
import TecnoTienda.tienda.dto.CreateSaleRequestDTO;
import TecnoTienda.tienda.dto.UserSalesRequestDTO;
import TecnoTienda.tienda.dto.SalePaginationDTO;
import TecnoTienda.tienda.dto.SaleDTO;
import TecnoTienda.tienda.service.SaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

class SaleControllerTest {

    @Mock
    private SaleService saleService;

    @InjectMocks
    private SaleController saleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveSale() {
        // Given
        CreateSaleRequestDTO requestDTO = new CreateSaleRequestDTO();
        SaleDTO saleDTO = new SaleDTO();

        when(saleService.saveSale(requestDTO)).thenReturn(saleDTO);

        // When
        ResponseEntity<?> responseEntity = saleController.saveSale(requestDTO);

        // Then
        verify(saleService, times(1)).saveSale(requestDTO);
        assert responseEntity.getStatusCode() == HttpStatus.CREATED;
        assert responseEntity.getBody() == saleDTO;
    }

    @Test
    void testGetSaleByUser() {
        // Given
        UserSalesRequestDTO requestDTO = new UserSalesRequestDTO();
        int page = 1;
        SalePaginationDTO salePaginationDTO = new SalePaginationDTO();

        when(saleService.saleByUserId(requestDTO, page)).thenReturn(salePaginationDTO);

        // When
        ResponseEntity<?> responseEntity = saleController.getSaleByUser(requestDTO, page);

        // Then
        verify(saleService, times(1)).saleByUserId(requestDTO, page);
        assert responseEntity.getStatusCode() == HttpStatus.ACCEPTED;
        assert responseEntity.getBody() == salePaginationDTO;
    }

    @Test
    void testGetAllSales() {
        // Given
        int page = 1;
        SalePaginationDTO salePaginationDTO = new SalePaginationDTO();

        when(saleService.getAllSales(page)).thenReturn(salePaginationDTO);

        // When
        ResponseEntity<?> responseEntity = saleController.getAllSales(page);

        // Then
        verify(saleService, times(1)).getAllSales(page);
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody() == salePaginationDTO;
    }
}