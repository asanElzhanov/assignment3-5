package foodService;

public class Food {
    protected boolean taken = false;
    protected String name;
    protected float price;
    public Food(String name, float price){
        this.name = name;
        this.price = price;
    }
    public String getInfo(){
        return this.name + ", price: " + this.price;
    }
    public String getName(){
        return this.name;
    }
}
