package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.*;
import com.atilaBiosystems.InventoryManagementSystem.Repository.*;
import com.atilaBiosystems.InventoryManagementSystem.ReturnObject.CustomRecipeItem;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

@Service
public class ComponentServiceImpl implements ComponentService{

    private final ComponentRepository componentRepository;
    private final ComponentRecordRepository componentRecordRepository;
    private final ManufactureRecordRepository manufactureRecordRepository;
    private final ManufactureRecordDetailRepository manufactureRecordDetailRepository;

    public ComponentServiceImpl(ComponentRepository componentRepository,
                                ComponentRecordRepository componentRecordRepository,
                                ManufactureRecordRepository manufactureRecordRepository,
                                ManufactureRecordDetailRepository manufactureRecordDetailRepository) {
        this.componentRepository = componentRepository;
        this.componentRecordRepository = componentRecordRepository;
        this.manufactureRecordRepository = manufactureRecordRepository;
        this.manufactureRecordDetailRepository = manufactureRecordDetailRepository;
    }

    @Override
    public List<Component> findAll() {
        return componentRepository.findAll();
    }

    @Override
    public List<ComponentRecord> findByAmountInStockGreaterThan() {
        return componentRecordRepository.findByAmountInStockGreaterThan(0);
    }

    @Override
    public List<RecipeItem> getRecipeItemsByComponentId(int componentId) {
        Component component = componentRepository.findById(componentId).orElse(null);
        if (component != null) {
            return component.getRecipeItems();
        }
        return null;
    }

    @Override
    public Component findById(int componentId) {
        Component component = componentRepository.findById(componentId).orElse(null);
        return component;
    }

    @Override
    public List<CustomRecipeItem> getRecipeByComponentId(int componentId, Integer scale) {
        Component component = this.findById(componentId);
        List<CustomRecipeItem> recipe = new ArrayList<>();

        List<Prerequisite> prerequisites = component.getPrerequisites();
        for (Prerequisite pre: prerequisites){
            Component i_component = pre.getIntermediateComponent();
            Double vol = Double.valueOf(pre.getTestPerRxn());
            Double liquidVolume = Double.valueOf(pre.getVolPerRxn());
            List<ComponentRecord> crs = i_component.getComponentRecords();
            int amount = 0;
            for (ComponentRecord tempCR: crs){
                amount = Math.max(tempCR.getAmountInStock(), amount);
            }
            CustomRecipeItem currItem = new CustomRecipeItem(i_component.getComponentId(), i_component.getComponentName(),"component", liquidVolume,
                    liquidVolume*scale, amount >= vol*scale);
            recipe.add(currItem);
        }

        List<RecipeItem> recipeItems = this.getRecipeItemsByComponentId(componentId);
        for (RecipeItem item: recipeItems) {
            CustomRecipeItem currItem = new CustomRecipeItem(item.getMaterial().getMaterialId(), item.getName(),"raw material", item.getAmountPerRxn(),
                    item.getAmountPerRxn()*scale,
                    item.getMaterial().getAmountInStock() >= item.getAmountPerRxn()*scale);
            recipe.add(currItem);
        }
        return recipe;
    }

    @Override
    @Transactional
    public ManufactureRecord putInManufactureLine(int componentId, Integer scale) {

        Component component = this.findById(componentId);

        LocalDate localDate = LocalDate.now();
        Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String lotNum = sdf.format(currentDate);

        ComponentRecord latestComponentRecord = new ComponentRecord(component.getComponentCatalog(),component.getComponentName(),
                lotNum, currentDate, 0, component.getUnit()); // Scale should be 0 before done the manufacture

        latestComponentRecord.setComponent(component);
        componentRecordRepository.save(latestComponentRecord);

        ManufactureRecord currManufactureRecord = new ManufactureRecord(component.getComponentName(),
                currentDate, scale, "YC", component.getUnit(), 1);
        currManufactureRecord.setComponentRecord(latestComponentRecord);

        List<ManufactureRecordDetail> details = new ArrayList<>();
        List<Prerequisite> prerequisites = component.getPrerequisites();
        for (Prerequisite pre: prerequisites){
            Component i_component = pre.getIntermediateComponent();
            Double vol = Double.valueOf(pre.getTestPerRxn());
            List<ComponentRecord> crs = i_component.getComponentRecords();
            int amount = 0;

            ComponentRecord cr = null;
            for (ComponentRecord tempCR: crs){
                amount = Math.max(tempCR.getAmountInStock(), amount);
                cr = tempCR;
            }

            ManufactureRecordDetail currTuple = new ManufactureRecordDetail(i_component.getComponentName(),
                    vol, vol * scale, currManufactureRecord);
            currTuple.setComponentRecord(cr);
            manufactureRecordDetailRepository.save(currTuple);
            details.add(currTuple);
        }

        List<RecipeItem> recipeItems = this.getRecipeItemsByComponentId(componentId);
        for (RecipeItem item: recipeItems) {
            ManufactureRecordDetail currTuple = new ManufactureRecordDetail(item.getName(), item.getAmountPerRxn(),
                    item.getAmountPerRxn()*scale, currManufactureRecord);
            currTuple.setRawMaterial(item.getMaterial());
            manufactureRecordDetailRepository.save(currTuple);
            details.add(currTuple);
        }

        currManufactureRecord.setRecordDetails(details);
        manufactureRecordRepository.save(currManufactureRecord);
        return currManufactureRecord;
    }

    @Override
    @Transactional
    public void updateManufactureRecipe(int manufactureRecordId, List<Map<String, Double>> updates) {

        ManufactureRecord manufactureRecord = manufactureRecordRepository.findById(manufactureRecordId).orElse(null);

        if (manufactureRecord != null) {
            for (Map<String, Double> update : updates) {
                int recordDetailId = update.get("recordDetailId").intValue();
                double amountPerRxn = update.get("amountPerRxn");
                double totalVol = update.get("totalVol");

                for (ManufactureRecordDetail detail : manufactureRecord.getRecordDetails()) {
                    if (detail.getRecordDetailId() == recordDetailId) {
                        if (detail.getRawMaterial() != null) {
                            if (detail.getRawMaterial().getAmountInStock() < totalVol){
                                throw new RuntimeException("Not enough " + detail.getRawMaterial() + " in stock");
                            }
                        }
                        if (detail.getComponentRecord() != null) {
                            if (detail.getComponentRecord().getAmountInStock() < totalVol){
                                throw new RuntimeException("Not enough " + detail.getComponentRecord() + " in stock");
                            }
                        }
                        detail.setAmountPerRxn(amountPerRxn);
                        detail.setTotalVol(totalVol);
                    }
                }
            }

            manufactureRecordRepository.save(manufactureRecord);
        } else {
            throw new EntityNotFoundException("ManufactureRecord not found");
        }
    }

    @Override
    @Transactional
    public List<RawMaterial> finishManufacture(int manufactureRecordId, int updateScale) {
        ManufactureRecord manufactureRecord = manufactureRecordRepository.findById(manufactureRecordId).orElse(null);
        List<RawMaterial> rawMaterials = new ArrayList<>();

        if (manufactureRecord != null) {
            for (ManufactureRecordDetail detail : manufactureRecord.getRecordDetails()) {
                if (detail.getComponentRecord() != null) {
                    int beforeManufacture = detail.getComponentRecord().getAmountInStock();
                    detail.getComponentRecord().setAmountInStock((int)(beforeManufacture - detail.getTotalVol()));
                    continue;
                }
                if (detail.getRawMaterial() != null) {
                    RawMaterial currRawMateiral = detail.getRawMaterial();
                    int beforeManufacture = currRawMateiral.getAmountInStock();
                    currRawMateiral.setAmountInStock((int) (beforeManufacture - detail.getTotalVol()));
                    rawMaterials.add(currRawMateiral);
                }
            }
            manufactureRecord.setStatus(2);
            if (manufactureRecord.getComponentRecord() != null){
                manufactureRecord.getComponentRecord().setAmountInStock(updateScale);
            } else {
                manufactureRecord.getProductRecord().setAmountInStock(updateScale);
            }
            return rawMaterials;
        } else {
            throw new EntityNotFoundException("ManufactureRecord not found");
        }
    }

}
