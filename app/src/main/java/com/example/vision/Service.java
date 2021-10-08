package com.example.vision;

public class Service {
    private String service_name;
    private String description;
    private double price;
    private int icon_id;

    public Service(String service_name, String description, double price) {
        this.service_name = service_name;
        this.description = description;
        this.price = price;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIcon_id() {
        return icon_id;
    }

    public void setIcon_id(int icon_id) {
        this.icon_id = icon_id;
    }
}
