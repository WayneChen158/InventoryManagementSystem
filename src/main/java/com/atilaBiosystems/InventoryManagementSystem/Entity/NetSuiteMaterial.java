package com.atilaBiosystems.InventoryManagementSystem.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "netsuite_materials")
public class NetSuiteMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "netsuite_material_id")
    private Integer netSuiteMaterialId;

    @Column(name = "catalog_num")
    private String catalogNum;

    @Column(name = "description")
    private String description;

    @Column(name = "unit")
    private String unit;

    @OneToOne
    @JoinColumn(name="material_id")
    private RawMaterial rawMaterial;

    @OneToOne
    @JoinColumn(name="component_id")
    private Component component;
    public NetSuiteMaterial(){}

    public NetSuiteMaterial(Integer netSuiteMaterialId,
                            String catalogNum,
                            String description,
                            String unit,
                            RawMaterial rawMaterial,
                            Component component)
    {
        this.netSuiteMaterialId = netSuiteMaterialId;
        this.catalogNum = catalogNum;
        this.description = description;
        this.unit = unit;
        this.rawMaterial = rawMaterial;
        this.component = component;
    }

    public Integer getNetSuiteMaterialId() {
        return netSuiteMaterialId;
    }

    public void setNetSuiteMaterialId(Integer netSuiteMaterialId) {
        this.netSuiteMaterialId = netSuiteMaterialId;
    }

    public String getCatalogNum() {
        return catalogNum;
    }

    public void setCatalogNum(String catalogNum) {
        this.catalogNum = catalogNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}
