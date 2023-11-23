package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.WarehouseStockDTO;
import bg.softuni.grassstore.service.ProductService;
import bg.softuni.grassstore.service.WarehouseStockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WarehouseStockController {

    private final ProductService productService;

    private final WarehouseStockService warehouseStockService;

    public WarehouseStockController(ProductService productService,
                                    WarehouseStockService warehouseStockService) {
        this.productService = productService;
        this.warehouseStockService = warehouseStockService;
    }

    @ModelAttribute("warehouseStockDTO")
    public WarehouseStockDTO initWarehouseStock(){
        return new WarehouseStockDTO();
    }


    @GetMapping("/delivery-add")
    public String getDeliveryAdd(Model model){

        model.addAttribute("productList", productService.getAllProducts());

        return "/delivery-add";
    }

    @PostMapping("/delivery-add")
    public String postWarehouseStock(WarehouseStockDTO warehouseStockDTO){

        warehouseStockService.addDelivery(warehouseStockDTO);

        return "redirect:/home";
    }

}
