package com.atilaBiosystems.InventoryManagementSystem.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atilaBiosystems.InventoryManagementSystem.DAO.RequestDAO;
import com.atilaBiosystems.InventoryManagementSystem.Entity.RawMaterial;
import com.atilaBiosystems.InventoryManagementSystem.Entity.Request;
import com.atilaBiosystems.InventoryManagementSystem.Service.RawMaterialService;
import com.atilaBiosystems.InventoryManagementSystem.Service.RequestService;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService requestService;
    private final RawMaterialService rawMaterialService;

    @Autowired
    public RequestController(RequestService requestService, RawMaterialService rawMaterialService) {
        this.requestService = requestService;
        this.rawMaterialService = rawMaterialService;
    }

    @GetMapping()
    public List<Request> getAllRequests() {
        return this.requestService.findAll();
    }

    @DeleteMapping("/delete/{requestId}")
    public ResponseEntity<String> deleteRequestById(@PathVariable int requestId) {
        boolean success = requestService.deleteRequestById(requestId);
        if (success) {
            return ResponseEntity.ok().body(String.format("Request ID %d has been successfully deleted", requestId));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Failed to delete request ID %d...", requestId));
        }

    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewRequest(@RequestBody RequestDAO requestDAO) {
        Request request = new Request();

        request.setItemDescription(requestDAO.getItemDescription());
        request.setRequestCategory(requestDAO.getRequestCategory());
        request.setProject(requestDAO.getProject());
        request.setProjectDescription(null);
        request.setPurpose(requestDAO.getPurpose());
        request.setRequestBy(requestDAO.getRequestBy());
        request.setDoneBy(null);
        request.setRequestAmount(requestDAO.getRequestAmount());
        request.setFulfilledAmount(null);
        request.setPricePerUnit(requestDAO.getPricePerUnit());
        request.setFulfilledDate(null);
        request.setStatus(1);

        // Convert original date string from frontend
        String dateString = requestDAO.getRequestDate();
        String datePattern = "MM-dd-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Date parsing failed");
            System.out.println(dateString);
            e.printStackTrace();;
        }
        request.setRequestDate(date);

        // Try assigning material_id to known item by catalog number
        String catalogNumber = requestDAO.getCatalogNumber();
        String itemDescription = requestDAO.getItemDescription();
        RawMaterial rawMaterial = this.rawMaterialService.findByCatalogNumber(catalogNumber, itemDescription);
        if (rawMaterial != null) {
            request.setMaterialId(rawMaterial.getMaterialId());
        } else {
            // TODO: create a new item in this case
            request.setMaterialId(1);
        }

        Request newRequest = requestService.createRequest(request);

        if (newRequest != null) {
            return ResponseEntity.ok().body(String.format("Request for %s has been recorded", itemDescription));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Failed to create request for %s", itemDescription));
        }
    }
  
}
