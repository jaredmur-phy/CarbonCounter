package com.example.carboncounter;

public class User{
    float beef, pork, lamb, chicken, gas, dairy, water;

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
        return (float) (this.gas * 2.4);
    }
    public float calcMeat(){
        return (float) (this.beef * 27 + this.pork * 12.1 + this.lamb * 39.2 + this.chicken * 6.9);
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
    public float calcTransport(){
        return this.gas * 2.4;
    }
    public float calcMeat(){
        return (float) (this.beef * 27 + this.pork * 12.1 + this.lamb * 39.2 + this.chicken * 6.9);
    }
    public float calcDairy(){
        return (float) (this.dairy * 0.93);
    }
    public float calcWater(){
        return (float) (this.water * 0.38);
    }
    public float calcTotal(){
        return this.calcTransport() + this.calcDairy() + this.calcWater() + this.calcMeat();
    }

}