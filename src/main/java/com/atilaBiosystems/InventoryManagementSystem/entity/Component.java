package com.atilaBiosystems.InventoryManagementSystem.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "components")
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Long componentId;

    @Column(name = "component_name")
    private String componentName;

    @Column(name="version_description")
    private String versionDescription;

    @OneToMany(mappedBy = "component")
    private List<ComponentRecord> ComponentRecords;

    @OneToMany(mappedBy = "component")
    private List<RecipeItem> recipeItems;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH,
                    CascadeType.PERSIST, CascadeType.PERSIST})
    @JoinTable(
            name = "components_intermediate_components",
            joinColumns = @JoinColumn(name = "component_id"),
            inverseJoinColumns = @JoinColumn(name = "i_component_id")
    )
    private List<IntermediateComponent> interComponents;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH,
                    CascadeType.PERSIST, CascadeType.PERSIST})
    @JoinTable(
            name = "assembly_by",
            joinColumns = @JoinColumn(name = "component_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Component() {};

    public Component(String componentName, String versionDescription) {
        this.componentName = componentName;
        this.versionDescription = versionDescription;
    }

    public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getVersionDescription() {
        return versionDescription;
    }

    public void setVersionDescription(String versionDescription) {
        this.versionDescription = versionDescription;
    }

    public List<ComponentRecord> getComponentRecords() {
        return ComponentRecords;
    }

    public void setComponentRecords(List<ComponentRecord> componentRecords) {
        ComponentRecords = componentRecords;
    }

    public List<RecipeItem> getRecipeItems() {
        return recipeItems;
    }

    public void setRecipeItems(List<RecipeItem> recipeItems) {
        this.recipeItems = recipeItems;
    }

    public List<IntermediateComponent> getInterComponents() {
        return interComponents;
    }

    public void setInterComponents(List<IntermediateComponent> interComponents) {
        this.interComponents = interComponents;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Component{" +
                "componentName='" + componentName + '\'' +
                ", versionDescription='" + versionDescription + '\'' +
                '}';
    }
}