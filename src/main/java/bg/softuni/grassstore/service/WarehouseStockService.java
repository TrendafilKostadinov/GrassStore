package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.WarehouseDetailDTO;
import bg.softuni.grassstore.model.dto.WarehouseStockDTO;
import bg.softuni.grassstore.model.entity.OrderRowEntity;
import bg.softuni.grassstore.model.entity.ProductEntity;
import bg.softuni.grassstore.model.entity.WarehouseStock;
import bg.softuni.grassstore.repository.WarehouseStockRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseStockService {

    private final WarehouseStockRepository warehouseStockRepository;

    private final ModelMapper modelMapper;

    public WarehouseStockService(WarehouseStockRepository warehouseStockRepository,
                                 ModelMapper modelMapper) {
        this.warehouseStockRepository = warehouseStockRepository;
        this.modelMapper = modelMapper;
    }

    public void addDelivery(WarehouseStockDTO warehouseStockDTO) {
        WarehouseStock warehouseStock = warehouseStockRepository.findById(warehouseStockDTO.getProductId()).orElse(null);
        if (warehouseStock == null){
            warehouseStock = modelMapper.map(warehouseStockDTO, WarehouseStock.class);

            warehouseStockRepository.save(warehouseStock);

            return;
        }

        warehouseStock.setQuantity(warehouseStock.getQuantity() + warehouseStockDTO.getQuantity());

        warehouseStockRepository.save(warehouseStock);
    }

    public Long getWarehouseStock(ProductEntity product){
        WarehouseStock warehouseStock = warehouseStockRepository
                .findByProduct(product)
                .orElse(null);
        if (warehouseStock == null){
            return 0L;
        }

        return warehouseStock.getQuantity();
    }

    public void shipQuantity(OrderRowEntity row) {
        WarehouseStock warehouseStock = warehouseStockRepository
                .findByProduct(row.getProduct())
                .orElseThrow();

        warehouseStock.setQuantity(warehouseStock.getQuantity() - row.getQuantity());

        warehouseStockRepository.save(warehouseStock);
    }

    public List<WarehouseDetailDTO> getAllStock() {
        return warehouseStockRepository
                .findAll()
                .stream()
                .map(this::mapWarehouseDetail).toList();
    }

    public WarehouseDetailDTO mapWarehouseDetail(WarehouseStock warehouseStock){
        WarehouseDetailDTO warehouseDetailDTO = new WarehouseDetailDTO();

        warehouseDetailDTO.setProduct(warehouseStock.getProduct().getName());
        warehouseDetailDTO.setQuantity(warehouseStock.getQuantity());

        return warehouseDetailDTO;
    }
}
