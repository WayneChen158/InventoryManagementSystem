package com.atilaBiosystems.InventoryManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "assembly_by")
public class AssemblyBy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assembly_id")
    private Integer assemblyId;

//    @JsonIgnore
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinColumn(name="product_id")
//    private Product product;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    private Product product;

//    @JsonIgnore
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="component_id")
    private Component component;

    @Column(name = "amount_per_assay")
    private Double amountPerAssay;

    public AssemblyBy(){}

    public AssemblyBy(Double amountPerAssay) {
        this.amountPerAssay = amountPerAssay;
    }

    public Integer getAssemblyId() {
        return assemblyId;
    }

    public void setAssemblyId(Integer assemblyId) {
        this.assemblyId = assemblyId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Double getAmountPerAssay() {
        return amountPerAssay;
    }

    public void setAmountPerAssay(Double amountPerAssay) {
        this.amountPerAssay = amountPerAssay;
    }

    @Override
    public String toString() {
        return "Assembly_by{" +
                "component=" + component +
                ", amountPerAssay=" + amountPerAssay +
                '}';
    }
}
