package foodService;

public class Food {
    private String name;
    private float price;
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
