package com.pereira.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;

@Entity
public class Sensor {

    @Id
    private Long id;

    private String sensorType;
    private String status;

    @ManyToOne
    @JoinColumn(name = "node_id", nullable = false)
    private Node node;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL)
    private List<Data> data;

    // Constructors, Getters, Setters
    public Sensor() {}

    public Sensor(Long id, String sensorType, String status, Node node) {
        this.id = id;
        this.sensorType = sensorType;
        this.status = status;
        this.node = node;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
