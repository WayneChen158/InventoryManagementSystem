package com.atilaBiosystems.InventoryManagementSystem.Controller;

import com.atilaBiosystems.InventoryManagementSystem.DAO.SellItemDAO;
import com.atilaBiosystems.InventoryManagementSystem.Service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SellController {

    private final SellService sellService;

    @Autowired
    public SellController(SellService sellService) {
        this.sellService = sellService;
    }

    @PutMapping("/sell")
    public ResponseEntity<String> sellCurrentInvoice(@RequestBody List<SellItemDAO> itemList) {
        try {
            sellService.sellCurrentInvoice(itemList);
            return ResponseEntity.ok("Invoice processed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing invoice: " + e.getMessage());
        }
    }

}
