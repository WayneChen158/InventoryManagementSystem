package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.*;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ComponentRepository;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ManufactureRecordDetailRepository;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ManufactureRecordRepository;
import com.atilaBiosystems.InventoryManagementSystem.Repository.RecipeItemRepository;
import com.atilaBiosystems.InventoryManagementSystem.ReturnObject.CustomRecipeItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class ComponentServiceImpl implements ComponentService{

    private final RecipeItemRepository recipeItemRepository;
    private final ComponentRepository componentRepository;
    private final ManufactureRecordRepository manufactureRecordRepository;
    private final ManufactureRecordDetailRepository manufactureRecordDetailRepository;

    public ComponentServiceImpl(RecipeItemRepository recipeItemRepository,
                                ComponentRepository componentRepository,
                                ManufactureRecordRepository manufactureRecordRepository,
                                ManufactureRecordDetailRepository manufactureRecordDetailRepository) {
        this.recipeItemRepository = recipeItemRepository;
        this.componentRepository = componentRepository;
        this.manufactureRecordRepository = manufactureRecordRepository;
        this.manufactureRecordDetailRepository = manufactureRecordDetailRepository;
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
            Double vol = Double.valueOf(pre.getAmountPerRxn());
            List<ComponentRecord> crs = i_component.getComponentRecords();
            int amount = 0;
            for (ComponentRecord tempCR: crs){
                amount = Math.max(tempCR.getAmountInStock(), amount);
            }
            CustomRecipeItem currItem = new CustomRecipeItem(i_component.getComponentName(), vol,
                    vol*scale, amount >= vol*scale);
            recipe.add(currItem);
        }

        List<RecipeItem> recipeItems = this.getRecipeItemsByComponentId(componentId);
        for (RecipeItem item: recipeItems) {
            CustomRecipeItem currItem = new CustomRecipeItem(item.getName(), item.getAmountPerRxn(),
                    item.getAmountPerRxn()*scale,
                    item.getMaterial().getAmountInStock() >= item.getAmountPerRxn()*scale);
            recipe.add(currItem);
        }
        return recipe;
    }

    @Override
    public ManufactureRecord putInManufactureLine(int componentId, Integer scale) {

        Component component = this.findById(componentId);

        LocalDate localDate = LocalDate.now();
        Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        ManufactureRecord currManufactureRecord = new ManufactureRecord(component.getComponentName(),
                currentDate, scale, "YC", 1);

        List<ManufactureRecordDetail> details = new ArrayList<>();
        List<Prerequisite> prerequisites = component.getPrerequisites();
        for (Prerequisite pre: prerequisites){
            Component i_component = pre.getIntermediateComponent();
            Double vol = Double.valueOf(pre.getAmountPerRxn());
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

}
