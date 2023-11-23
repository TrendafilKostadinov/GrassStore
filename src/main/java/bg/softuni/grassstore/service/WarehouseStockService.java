package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.WarehouseStockDTO;
import bg.softuni.grassstore.model.entity.WarehouseStock;
import bg.softuni.grassstore.repository.WarehouseStockRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
