package br.com.beibe.beans;

import java.io.Serializable;

public class Product implements Serializable {

    private long id;
    private String name;
    private String description;
    private double weight;
    private Category category;
    private String utc;
    private String ean;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getUtc() {
        return this.utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }

    public String getEan() {
        return this.ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }
}
