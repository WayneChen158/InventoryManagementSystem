package com.atilaBiosystems.InventoryManagementSystem.Entity;

import jakarta.persistence.*;

@Entity
@Table(name ="recipe_items")
public class RecipeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipe_item_id")
    private int recipeItemId;
    @Column(name="name")
    private String name;
    @Column(name="amount_per_rxn")
    private double amountPerRxn;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="material_id")
    private RawMaterial material;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "component_id")
    private Component component;

    public RecipeItem() {}

    public RecipeItem(String name, double amountPerRxn, RawMaterial material, Component component) {
        this.name = name;
        this.amountPerRxn = amountPerRxn;
        this.material = material;
        this.component = component;
    }

    public int getRecipeItemId() {
        return recipeItemId;
    }

    public void setRecipeItemId(int recipeItemId) {
        this.recipeItemId = recipeItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmountPerRxn() {
        return amountPerRxn;
    }

    public void setAmountPerRxn(double amountPerRxn) {
        this.amountPerRxn = amountPerRxn;
    }

    public RawMaterial getMaterial() {
        return material;
    }

    public void setMaterial(RawMaterial material) {
        this.material = material;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return "RecipeItem{" +
                "recipeItemId=" + recipeItemId +
                ", name='" + name + '\'' +
                ", amountPerRxn=" + amountPerRxn +
                ", material=" + material +
                ", component=" + component +
                '}';
    }
}
