package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.Component;
import com.atilaBiosystems.InventoryManagementSystem.Entity.RecipeItem;
import com.atilaBiosystems.InventoryManagementSystem.ReturnObject.CustomRecipeItem;

import java.util.List;

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
    List<RecipeItem> getRecipeItemsByComponentId(int componentId);

    Component findById(int componentId);

    List<CustomRecipeItem> getRecipeByComponentId(int componentId, Integer scale);
}
