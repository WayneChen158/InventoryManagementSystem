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

    @Column(name = "test_per_rxn")
    private Double testPerRxn;

    @Column(name = "vol_per_rxn")
    private Double volPerRxn;

    public Prerequisite(){}

    public Prerequisite(Double testPerRxn, Double volPerRxn) {
        this.testPerRxn = testPerRxn;
        this.volPerRxn = volPerRxn;
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

    public Double getTestPerRxn() {
        return testPerRxn;
    }

    public void setTestPerRxn(Double amountPerRxn) {
        this.testPerRxn = testPerRxn;
    }

    public Double getVolPerRxn() {
        return volPerRxn;
    }

    public void setVolPerRxn(Double volPerRxn) {
        this.volPerRxn = volPerRxn;
    }

    @Override
    public String toString() {
        return "Prerequisite{" +
                "component=" + component +
                ", amountPerRxn=" + testPerRxn +
                '}';
    }
}
