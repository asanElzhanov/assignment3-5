package foodService.coffee;

import foodService.Food;

import java.util.List;

public class CoffeeBoom extends Coffee{
    public CoffeeBoom(String name, String time, float rating, List<Food> food, boolean bakery) {
        super(name, time, rating, food,bakery);
    }
    @Override
    public void orderCoffeeWithBakery(String choiceCoffee,String choiceBakery){
        for(int i = 0;i<this.food.size();i++){
            if(choiceCoffee.equals(this.food.get(i).getName())){
                this.food.remove(i);
                System.out.println("Here is your " + choiceCoffee + " coffee with "+choiceBakery + ". Come again!");
                break;
            }
        }
        createOrRefreshMenu();
    }

}
