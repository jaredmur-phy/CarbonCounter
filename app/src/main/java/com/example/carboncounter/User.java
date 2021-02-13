public class User{
    float beef;
    float pork;
    float lamb;
    float chicken;
    float gas;
    float dairy;
    float water;
    public User(float beef, float pork, float lamb, float chicken, float gas, float dairy, float water){
        this.beef = beef;
        this.pork = pork;
        this.lamb = lamb;
        this.chicken = chicken;
        this.gas = gas;
        this.dairy = dairy;
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