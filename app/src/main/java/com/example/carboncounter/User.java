package com.example.carboncounter;

public class User{
    float beef, pork, lamb, chicken, gas, dairy, water, transport, meat, dairyCalc, waterCalc, total;

    private static User instance;

    private User() {

    }

    public static User getInstance() {
        if(instance == null) {
            instance = new User();
        }
        return instance;
    }

    public float calcTransport(){
        this.transport = (float) (this.gas * 2.4);
        return this.transport;
    }
    public float calcMeat(){
        this.meat = (float) (this.beef * 27 + this.pork * 12.1 + this.lamb * 39.2 + this.chicken * 6.9);
        return this.meat;
    }

    public float calcDairy(){
        this.dairyCalc = (float) (this.dairy * 0.93);
        return this.dairyCalc;
    }
    public float calcWater(){
        this.waterCalc = (float) (this.water * 0.38);
        return this.waterCalc;
    }
    public float calcTotal(){
        this.total = transport + dairyCalc + waterCalc + meat;
        return this.total;
    }

    //Getters and Setters
    public float getBeef() {
        return beef;
    }

    public void setBeef(float beef) {
        this.beef = beef;
    }

    public float getPork() {
        return pork;
    }

    public void setPork(float pork) {
        this.pork = pork;
    }

    public float getLamb() {
        return lamb;
    }

    public void setLamb(float lamb) {
        this.lamb = lamb;
    }

    public float getChicken() {
        return chicken;
    }

    public void setChicken(float chicken) {
        this.chicken = chicken;
    }

    public float getGas() {
        return gas;
    }

    public void setGas(float gas) {
        this.gas = gas;
    }

    public float getDairy() {
        return dairy;
    }

    public void setDairy(float dairy) {
        this.dairy = dairy;
    }

    public float getWater() {
        return water;
    }

    public void setWater(float water) {
        this.water = water;
    }
}