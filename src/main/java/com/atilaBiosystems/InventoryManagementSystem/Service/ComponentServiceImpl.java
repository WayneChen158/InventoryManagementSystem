package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.Component;
import com.atilaBiosystems.InventoryManagementSystem.Entity.RecipeItem;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ComponentRepository;
import com.atilaBiosystems.InventoryManagementSystem.Repository.RecipeItemRepository;
import org.springframework.stereotype.Service;

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
}
