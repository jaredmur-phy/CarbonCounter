package com.example.carboncounter;

public class LastWeek {
    float transportTotal, meatTotal, dairyTotal, waterTotal, grandTotal;

    private static LastWeek instance;

    private LastWeek() {

    }

    public static LastWeek getInstance() {
        if(instance == null) {
            instance = new LastWeek();
        }

        return instance;
    }

    public float getTransportTotal() {
        return transportTotal;
    }

    public void setTransportTotal(float transportTotal) {
        this.transportTotal = transportTotal;
    }

    public float getMeatTotal() {
        return meatTotal;
    }

    public void setMeatTotal(float meatTotal) {
        this.meatTotal = meatTotal;
    }

    public float getDairyTotal() {
        return dairyTotal;
    }

    public void setDairyTotal(float dairyTotal) {
        this.dairyTotal = dairyTotal;
    }

    public float getWaterTotal() {
        return waterTotal;
    }

    public void setWaterTotal(float waterTotal) {
        this.waterTotal = waterTotal;
    }

    public float getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(float grandTotal) {
        this.grandTotal = grandTotal;
    }
}
