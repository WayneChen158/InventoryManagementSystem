package com.atilaBiosystems.InventoryManagementSystem.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "prerequisite")
public class Prerequisite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prerequisite_id")
    private Integer prerequisiteId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="component_id")
    private Component component;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="intermediate_component_id")
    private Component intermediateComponent;

    @Column(name = "amount_per_rxn")
    private Integer amountPerRxn;

    public Prerequisite(){}

    public Prerequisite(Integer amountPerRxn) {
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

    public Integer getAmountPerRxn() {
        return amountPerRxn;
    }

    public void setAmountPerRxn(Integer amountPerRxn) {
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
