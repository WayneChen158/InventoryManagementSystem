package com.atilaBiosystems.InventoryManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "components")
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Integer componentId;

    @Column(name = "component_catalog")
    private String componentCatalog;

    @Column(name = "component_name")
    private String componentName;

    @Column(name = "unit")
    private String unit;

    @Column(name="version_description")
    private String versionDescription;

    @JsonManagedReference // Indicates that ComponentRecord is serialized as part of Component
    @OneToMany(mappedBy = "component")
    private List<ComponentRecord> ComponentRecords;

    @OneToMany(mappedBy = "component")
    private List<RecipeItem> recipeItems;

    @OneToMany(mappedBy = "component")
    private List<Prerequisite> prerequisites;

    @OneToMany(mappedBy = "intermediateComponent")
    private List<Prerequisite> intermediateComponents;

//    @JsonBackReference
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {CascadeType.DETACH, CascadeType.REFRESH,
//                    CascadeType.PERSIST, CascadeType.PERSIST})
//    @JoinTable(
//            name = "assembly_by",
//            joinColumns = @JoinColumn(name = "component_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private List<Product> products;

    public Component() {};

    public Component(String componentCatalog, String componentName, String unit, String versionDescription) {
        this.componentCatalog = componentCatalog;
        this.componentName = componentName;
        this.unit = unit;
        this.versionDescription = versionDescription;
    }

    public Integer getComponentId() {
        return componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public String getComponentCatalog() {
        return componentCatalog;
    }

    public void setComponentCatalog(String componentCatalog) {
        this.componentCatalog = componentCatalog;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public List<Prerequisite> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Prerequisite> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public List<Prerequisite> getIntermediateComponents() {
        return intermediateComponents;
    }

    public void setIntermediateComponents(List<Prerequisite> intermediateComponents) {
        this.intermediateComponents = intermediateComponents;
    }

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }

    @Override
    public String toString() {
        return "Component{" +
                "componentName='" + componentName + '\'' +
                ", versionDescription='" + versionDescription + '\'' +
                '}';
    }
}