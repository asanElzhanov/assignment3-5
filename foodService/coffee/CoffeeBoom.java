package foodService.coffee;

import foodService.Food;
import foodService.ShoppingBag;
import foodService.fastFood.BurgerKing;

import java.util.List;

public class CoffeeBoom extends Coffee{
    private static CoffeeBoom instance = null;
    private CoffeeBoom(String name, String time, float rating, List<Food> food, boolean bakery) {
        super(name, time, rating, food,bakery);
    }
    public static synchronized CoffeeBoom getInstance(String name, String time, float rating, List<Food> food, boolean bakery){
        if(instance == null){
            instance = new CoffeeBoom(name, time, rating, food, bakery);
        }
        return instance;
    }
    public static synchronized CoffeeBoom getInstance(){
        return instance;
    }
    @Override
    public void orderCoffeeWithBakery(String choiceCoffee, String choiceBakery, ShoppingBag bag){
        for(int i = 0;i<this.food.size();i++){
            if(choiceCoffee.equalsIgnoreCase(this.food.get(i).getName())){
                bag.addFoodToFoodList(this.food.get(i));
                this.food.remove(i);
                System.out.println("Here is your " + choiceCoffee + " coffee with "+choiceBakery + ". Come again!");
                break;
            }
        }
        createOrRefreshMenu();
    }

}
