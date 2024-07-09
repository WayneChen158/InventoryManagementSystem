package com.atilaBiosystems.InventoryManagementSystem.Controller;

import com.atilaBiosystems.InventoryManagementSystem.DAO.RawMaterialDAO;
import com.atilaBiosystems.InventoryManagementSystem.DAO.UpdateRawMaterialForm;
import com.atilaBiosystems.InventoryManagementSystem.Entity.*;
import com.atilaBiosystems.InventoryManagementSystem.Service.ComponentService;
import com.atilaBiosystems.InventoryManagementSystem.Service.ProductService;
import com.atilaBiosystems.InventoryManagementSystem.Service.RawMaterialService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;
    private final ComponentService componentService;
    private final ProductService productService;

    @Autowired
    public RawMaterialController(RawMaterialService rawMaterialService,
                                 ComponentService componentService,
                                 ProductService productService) {
        this.rawMaterialService = rawMaterialService;
        this.componentService = componentService;
        this.productService = productService;
    }

    private Date parseDateString(String dateString) {
        String datePattern = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Date parsing failed");
            System.out.println(dateString);
            e.printStackTrace();;
        }
        return date;
    }

    @GetMapping("/rawMaterials")
    public List<RawMaterial> getRawMaterials(){
        return rawMaterialService.findAll();
    }

    @GetMapping("/rawMaterials/{rawMaterialId}")
    public RawMaterial getRawMaterialById(@PathVariable int rawMaterialId) {
        return this.rawMaterialService.findById(rawMaterialId);
    }

    @GetMapping("/consumables")
    public List<RawMaterial> getConsumables(){
        return rawMaterialService.findByCategory(1);
    }

    // Additional custom endpoints, if needed
    // For example, you can define custom search or business logic endpoints here.
    @GetMapping("/filter")
    public List<RawMaterial> filterRawMaterials(
            @RequestParam(value = "search", required = false) String searchKeyword,
            @RequestParam(value = "manufacturer", required = false) String manufacturer,
            @RequestParam(value = "groupName", required = false) Integer groupName) {
        // Implement filtering and searching logic in the service
        return rawMaterialService.filterRawMaterials(searchKeyword, manufacturer, groupName);
    }

    // Add a new RawMaterial entry to the database
    @PostMapping("/rawMaterials/add")
    public RawMaterial addNewMaterial(@RequestBody RawMaterialDAO rawMaterialDAO){
        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setGroupName(rawMaterialDAO.getRawMaterialType());
        rawMaterial.setCatalogNumber(rawMaterialDAO.getCatlogNumber());
        rawMaterial.setDescription(rawMaterialDAO.getItemName());
        rawMaterial.setManufacturer(rawMaterialDAO.getVendor());
        rawMaterial.setConcentration(null);
        rawMaterial.setReceiveDate(null);
        rawMaterial.setThreshold(rawMaterialDAO.getAlertAmount());
        rawMaterial.setAmountInStock(rawMaterialDAO.getAmount());
        rawMaterial.setOwner(rawMaterialDAO.getOwner());
        rawMaterial.setLocation(rawMaterialDAO.getLocation());
        rawMaterial.setWebsite(rawMaterialDAO.getWebsite());
        return this.rawMaterialService.createRawMaterial(rawMaterial);
    }

    @DeleteMapping("/rawMaterials/delete/{rawMaterialId}")
    public ResponseEntity<String> deleteRawMaterialById(@PathVariable int rawMaterialId) {
        boolean success = this.rawMaterialService.deleteRawMaterialById(rawMaterialId);
        if (success) {
            return ResponseEntity.ok().body(String.format("Raw material ID %d has been successfully deleted", rawMaterialId));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Failed to delete raw material ID %d...", rawMaterialId));
        }
    }

    @PatchMapping("/rawMaterials/update")
    public ResponseEntity<String> updateRawMaterialById(@RequestBody UpdateRawMaterialForm form) {
        int materialId = form.getMaterialId();

        RawMaterial material = this.rawMaterialService.findById(materialId);
        if (material == null) {
            String responseString = String.format("Failed to update raw material ID %d...", materialId);
            responseString += "Provided ID is not valid.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseString);
        }

        if (form.getCategory().equals(1) || form.getCategory().equals(2)) {
            material.setCategory(form.getCategory());
        } else {
            material.setCategory(null);
        }

        material.setGroupName(form.getGroupName());
        material.setCatalogNumber(form.getCatalogNumber());
        material.setDescription(form.getDescription());
        material.setManufacturer(form.getManufacturer());

        try {
            material.setConcentration(Double.valueOf(form.getConcentration()));
        } catch (NumberFormatException e) {
            material.setConcentration(null);
        }

        if (material.getLocation() == null && form.getLocation().equals("")) {
            material.setLocation(null);
        } else {
            material.setLocation(form.getLocation());
        }

        if (material.getOwner() == null && form.getOwner().equals("")) {
            material.setOwner(null);
        } else {
            material.setOwner(form.getOwner());
        }

        if (material.getWebsite() == null && form.getWebsite().equals("")) {
            material.setWebsite(null);
        } else {
            material.setWebsite(form.getWebsite());
        }
        
        material.setThreshold(form.getThreshold());
        material.setAmountInStock(form.getAmountInStock());

        if (material.getUnit() == null && form.getUnit().equals("")) {
            material.setUnit(null);
        } else {
            material.setUnit(form.getUnit());
        }

        Date receiveDate = this.parseDateString(form.getReceiveDate());
        if (receiveDate != null) {
            material.setReceiveDate(receiveDate);
        }

        RawMaterial updatedMaterial = this.rawMaterialService.updateRawMaterial(material);
        if (updatedMaterial != null) {
            return ResponseEntity.ok().body(String.format("Successfully updated raw material %d", materialId));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Failed to update raw material %d...", materialId));
        }
    }

    @GetMapping("/rawMaterials/gen-inventory-report")
    public Map<NetSuiteMaterial, Integer> genInventoryReport(){
        Map<Component, Integer> componentInventory = new HashMap<>();
        // Back calculate inventory Items by saving in a temporary local list/dict
        List<ComponentRecord> inStockComponents = componentService.findByAmountInStockGreaterThan();
        for (ComponentRecord cr: inStockComponents){
            Component component = cr.getComponent();
            componentInventory.put(component,
                    componentInventory.getOrDefault(component, 0)+cr.getAmountInStock());
        }

        List<ProductRecord> inStockProducts = productService.findByAmountInStockGreaterThan();
        for (ProductRecord pr: inStockProducts){
            Integer amountInStock = pr.getAmountInStock();
            Product product = pr.getProduct();
            List<AssemblyBy> assemblyItems = product.getAssemblyItems();
            for (AssemblyBy assemblyItem: assemblyItems){
                Component component = assemblyItem.getComponent();
                Double amountPerAssay = assemblyItem.getAmountPerAssay();
                componentInventory.put(component,
                        (int) (componentInventory.getOrDefault(component, 0)
                                + amountPerAssay * amountInStock));
            }
        }

        // Process all components to include intermediate components using a queue
        Queue<Map.Entry<Component, Integer>> queue = new LinkedList<>(componentInventory.entrySet());

        while (!queue.isEmpty()) {
            Map.Entry<Component, Integer> entry = queue.poll();
            Component component = entry.getKey();
            Integer amount = entry.getValue();
            addIntermediateComponents(componentInventory, component, amount, queue);
        }

        // copy all raw materials in a dict
        List<RawMaterial> rawMaterials = rawMaterialService.findAll();
        Map<Integer, Integer> rawMaterialMap = new HashMap<>();
        for(RawMaterial rawMaterial: rawMaterials){
            rawMaterialMap.put(rawMaterial.getMaterialId(), rawMaterial.getAmountInStock());
        }

        // Back accumulate on raw materials, 1.2 as coefficient
        // Cares ony about recipe items
        for (Map.Entry<Component, Integer> entrySet: componentInventory.entrySet()){
            Component component = entrySet.getKey();
            Integer amount = entrySet.getValue();

            List<RecipeItem> recipeItems = component.getRecipeItems();
            for(RecipeItem ri: recipeItems){
                Integer mId = ri.getMaterial().getMaterialId();
                rawMaterialMap.put(mId, (int) (rawMaterialMap.get(mId) + ri.getAmountPerRxn() * amount * 120));
            }
        }

        Map<NetSuiteMaterial, Integer> inventoryReport = new TreeMap<>(
                (o1, o2) -> o1.getNetSuiteMaterialId().compareTo(o2.getNetSuiteMaterialId())
        );

        // Check NetSuiteMaterial items, if it foreign link to raw_material, save in the list directly
        //  If it foreign link to raw_material, calculate the maximum amount we can manufacture now
        List<NetSuiteMaterial> netSuiteMaterials = rawMaterialService.findAll_netSuite();
        for(NetSuiteMaterial nm: netSuiteMaterials){
            if (nm.getRawMaterial() == null && nm.getComponent() == null){
                inventoryReport.put(nm, -1);
            } else if(nm.getRawMaterial() != null) {
                inventoryReport.put(nm, rawMaterialMap.get(nm.getRawMaterial().getMaterialId()));
            } else {
                inventoryReport.put(nm, componentService.getLargestScale(nm.getComponent()));
            }
        }

        return inventoryReport;
    }

    public static void addIntermediateComponents(Map<Component, Integer> componentInventory, Component component, Integer amount, Queue<Map.Entry<Component, Integer>> queue) {
        List<Prerequisite> prerequisites = component.getPrerequisites();
        if (prerequisites == null || prerequisites.isEmpty()) {
            return;
        }

        for (Prerequisite pre : prerequisites) {
            Component intermediateComponent = pre.getIntermediateComponent();
            int requiredAmount = (int) (amount * pre.getTestPerRxn());

            int newAmount = componentInventory.getOrDefault(intermediateComponent, 0) + requiredAmount;
            componentInventory.put(intermediateComponent, newAmount);

            // Add the intermediate component to the queue for further processing
            queue.offer(new AbstractMap.SimpleEntry<>(intermediateComponent, requiredAmount));
        }
    }

    // GET /api/rawMaterials : get all the RawMaterials
    // PUT /api/rawMaterials/{materialId} Body should include all the information, frontend should keep this in mind
}

