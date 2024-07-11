package com.model;

import java.util.Date;

public class DeliveryDetails {
    
    private String deliveryDate;
    private String deliveryAddress;
    private String deliveryStatus;

    // Default constructor
    public DeliveryDetails() {
    }

    // Parameterized constructor
    public DeliveryDetails( String deliveryDate, String deliveryAddress, String deliveryStatus) {
        
        this.deliveryDate = deliveryDate;
        this.deliveryAddress = deliveryAddress;
        this.deliveryStatus = deliveryStatus;
    }

    // Getter and Setter methods
   
    

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
