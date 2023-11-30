package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.WarehouseDetailDTO;
import bg.softuni.grassstore.model.dto.WarehouseStockDTO;
import bg.softuni.grassstore.model.entity.OrderRowEntity;
import bg.softuni.grassstore.model.entity.ProductEntity;
import bg.softuni.grassstore.model.entity.WarehouseStock;
import bg.softuni.grassstore.repository.WarehouseStockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WarehouseStockServiceTests {

    @Mock
    private WarehouseStockRepository warehouseStockRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private WarehouseStockService warehouseStockService;

    @Test
    void testAddDelivery_NewProduct() {
        WarehouseStockDTO warehouseStockDTO = new WarehouseStockDTO();
        warehouseStockDTO.setProductId(1L);
        warehouseStockDTO.setQuantity(100L);

        when(warehouseStockRepository.findById(1L)).thenReturn(Optional.empty());

        warehouseStockService.addDelivery(warehouseStockDTO);

        verify(warehouseStockRepository, times(1)).save(any());
    }

    @Test
    void testAddDelivery_ExistingProduct() {
        WarehouseStockDTO warehouseStockDTO = new WarehouseStockDTO();
        warehouseStockDTO.setProductId(1L);
        warehouseStockDTO.setQuantity(100L);

        WarehouseStock existingStock = new WarehouseStock();
        existingStock.setProduct(new ProductEntity());
        existingStock.setQuantity(50L);

        when(warehouseStockRepository.findById(1L)).thenReturn(Optional.of(existingStock));

        warehouseStockService.addDelivery(warehouseStockDTO);

        verify(warehouseStockRepository, times(1)).save(any());
        assertEquals(150, existingStock.getQuantity());
    }

    @Test
    void testGetWarehouseStock() {
        ProductEntity product = new ProductEntity();
        product.setId(1L);

        WarehouseStock warehouseStock = new WarehouseStock();
        warehouseStock.setProduct(product);
        warehouseStock.setQuantity(50L);

        when(warehouseStockRepository.findByProduct(product)).thenReturn(Optional.of(warehouseStock));

        assertEquals(50L, warehouseStockService.getWarehouseStock(product));
    }

    @Test
    void testGetWarehouseStock_ProductNotFound() {
        ProductEntity product = new ProductEntity();
        product.setId(1L);

        when(warehouseStockRepository.findByProduct(product)).thenReturn(Optional.empty());

        assertEquals(0L, warehouseStockService.getWarehouseStock(product));
    }

    @Test
    void testShipQuantity() {
        OrderRowEntity orderRow = new OrderRowEntity();
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        orderRow.setProduct(product);
        orderRow.setQuantity(10L);

        WarehouseStock warehouseStock = new WarehouseStock();
        warehouseStock.setProduct(product);
        warehouseStock.setQuantity(50L);

        when(warehouseStockRepository.findByProduct(product)).thenReturn(Optional.of(warehouseStock));

        warehouseStockService.shipQuantity(orderRow);

        verify(warehouseStockRepository, times(1)).save(any());
        assertEquals(40, warehouseStock.getQuantity());
    }

    @Test
    void testShipQuantity_ProductNotFound() {
        OrderRowEntity orderRow = new OrderRowEntity();
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        orderRow.setProduct(product);
        orderRow.setQuantity(10L);

        when(warehouseStockRepository.findByProduct(product)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> warehouseStockService.shipQuantity(orderRow));
    }

    @Test
    void testGetAllStock() {
        WarehouseStock stock1 = new WarehouseStock();
        stock1.setProduct(new ProductEntity());
        stock1.setQuantity(20L);

        WarehouseStock stock2 = new WarehouseStock();
        stock2.setProduct(new ProductEntity());
        stock2.setQuantity(30L);

        when(warehouseStockRepository.findAll()).thenReturn(List.of(stock1, stock2));

        List<WarehouseDetailDTO> result = warehouseStockService.getAllStock();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
