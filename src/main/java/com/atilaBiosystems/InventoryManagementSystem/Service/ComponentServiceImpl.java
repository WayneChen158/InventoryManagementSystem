package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.Component;
import com.atilaBiosystems.InventoryManagementSystem.Entity.ComponentRecord;
import com.atilaBiosystems.InventoryManagementSystem.Entity.Prerequisite;
import com.atilaBiosystems.InventoryManagementSystem.Entity.RecipeItem;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ComponentRepository;
import com.atilaBiosystems.InventoryManagementSystem.Repository.RecipeItemRepository;
import com.atilaBiosystems.InventoryManagementSystem.ReturnObject.CustomRecipeItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComponentServiceImpl implements ComponentService{

    private final RecipeItemRepository recipeItemRepository;
    private final ComponentRepository componentRepository;

    public ComponentServiceImpl(RecipeItemRepository recipeItemRepository, ComponentRepository componentRepository) {
        this.recipeItemRepository = recipeItemRepository;
        this.componentRepository = componentRepository;
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
}
