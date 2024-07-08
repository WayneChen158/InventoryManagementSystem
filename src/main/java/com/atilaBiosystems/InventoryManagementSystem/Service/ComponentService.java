package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.*;
import com.atilaBiosystems.InventoryManagementSystem.ReturnObject.CustomRecipeItem;

import java.util.List;
import java.util.Map;

public interface ComponentService {
    /**
     * Methods	Urls	                    Actions
     * POST	/api/tutorials/:id/comments	    create new Comment for a Tutorial
     * GET	/api/tutorials/:id/comments	    retrieve all Comments of a Tutorial
     * GET	/api/comments/:id	            retrieve a Comment by :id
     * PUT	/api/comments/:id	            update a Comment by :id
     * DELETE	/api/comments/:id	            delete a Comment by :id
     * DELETE	/api/tutorials/:id	            delete a Tutorial (and its Comments) by :id
     * DELETE	/api/tutorials/:id/comments	    delete all Comments of a Tutorial
     */
    List<Component> findAll();

    List<ComponentRecord> findByAmountInStockGreaterThan();

    List<RecipeItem> getRecipeItemsByComponentId(int componentId);

    Component findById(int componentId);

    List<CustomRecipeItem> getRecipeByComponentId(int componentId, Integer scale);

    Integer getLargestScale(Component component);

    ManufactureRecord putInManufactureLine(int componentId, Integer scale);

    void updateManufactureRecipe(int manufactureRecordId, List<Map<String, Double>> updates);

    List<RawMaterial> finishManufacture(int manufactureRecordId, int updateScale);
}
