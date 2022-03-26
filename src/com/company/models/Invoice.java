package com.company.models;

import java.util.ArrayList;
import java.util.Date;

public class Invoice {
    public static int invoicesNumber;

    public Invoice(){
        invoicesNumber++;
    }

    private int id;
    private String date;
    private String customer;
    private float totalPrice;
    private ArrayList<Item> items;

// Set Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //    Set Date
    public String  getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

// Set Customer
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
