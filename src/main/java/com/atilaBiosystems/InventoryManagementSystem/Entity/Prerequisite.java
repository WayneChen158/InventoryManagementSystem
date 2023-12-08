package com.atilaBiosystems.InventoryManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "prerequisite")
public class Prerequisite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prerequisite_id")
    private Integer prerequisiteId;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="component_id")
    private Component component;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="intermediate_component_id")
    private Component intermediateComponent;

    @Column(name = "amount_per_rxn")
    private Double amountPerRxn;

    public Prerequisite(){}

    public Prerequisite(Double amountPerRxn) {
        this.amountPerRxn = amountPerRxn;
    }

    public Integer getPrerequisiteId() {
        return prerequisiteId;
    }

    public void setPrerequisiteId(Integer prerequisiteId) {
        this.prerequisiteId = prerequisiteId;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Component getIntermediateComponent() {
        return intermediateComponent;
    }

    public void setIntermediateComponent(Component intermediateComponent) {
        this.intermediateComponent = intermediateComponent;
    }

    public Double getAmountPerRxn() {
        return amountPerRxn;
    }

    public void setAmountPerRxn(Double amountPerRxn) {
        this.amountPerRxn = amountPerRxn;
    }

    @Override
    public String toString() {
        return "Prerequisite{" +
                "component=" + component +
                ", amountPerRxn=" + amountPerRxn +
                '}';
    }
}
