package com.atilaBiosystems.InventoryManagementSystem.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "intermediate_components")
public class IntermediateComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "i_component_id")
    private int interComponentId;

    @Column(name = "i_component_name")
    private String interComponentName;

    @Column(name="version_description")
    private String versionDescription;

    @OneToMany(mappedBy = "intermediateComponent")
    private List<IntermediateComponentRecord> intermediateComponentRecords;

    @OneToMany(mappedBy = "intermediateComponent")
    private List<RecipeItem> recipeItems;

    @ManyToMany(fetch = FetchType.LAZY,
                cascade = {CascadeType.DETACH, CascadeType.REFRESH,
                           CascadeType.PERSIST, CascadeType.PERSIST})
    @JoinTable(
            name = "components_intermediate_components",
            joinColumns = @JoinColumn(name = "i_component_id"),
            inverseJoinColumns = @JoinColumn(name = "component_id")
    )
    private List<Component> components;

    public IntermediateComponent() {}

    public IntermediateComponent(String interComponentName, String versionDescription) {
        this.interComponentName = interComponentName;
        this.versionDescription = versionDescription;
    }

    public int getInterComponentId() {
        return interComponentId;
    }

    public void setInterComponentId(int interComponentsId) {
        this.interComponentId = interComponentsId;
    }

    public String getInterComponentName() {
        return interComponentName;
    }

    public void setInterComponentName(String interComponentName) {
        this.interComponentName = interComponentName;
    }

    public String getVersionDescription() {
        return versionDescription;
    }

    public void setVersionDescription(String versionDescription) {
        this.versionDescription = versionDescription;
    }

    public List<IntermediateComponentRecord> getIntermediateComponentRecords() {
        return intermediateComponentRecords;
    }

    public void setIntermediateComponentRecords(List<IntermediateComponentRecord> intermediateComponentRecords) {
        this.intermediateComponentRecords = intermediateComponentRecords;
    }

    public List<RecipeItem> getRecipeItems() {
        return recipeItems;
    }

    public void setRecipeItems(List<RecipeItem> recipeItems) {
        this.recipeItems = recipeItems;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "IntermediateComponent{" +
                "interComponentName='" + interComponentName + '\'' +
                ", versionDescription='" + versionDescription + '\'' +
                '}';
    }
}
