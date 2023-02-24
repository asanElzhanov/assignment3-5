package foodService;

import foodService.fastFood.BurgerKing;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBag {
    private List<Food> foodList;

    public ShoppingBag() {
        this.foodList = new ArrayList<>();
    }

    public void addFoodToFoodList(Food food) {
        foodList.add(food);
    }
    public void deleteFromFoodList(int ind){
        try{
            foodList.remove(ind-1);
        }
        catch (Exception e){
            System.out.println("There is no food at " + ind + " number");
        }
    }
    public void getShoppingBag() {
        System.out.println("Your shopping bag:\n\n");
        if(foodList.size() == 0){
            System.out.println("Your bag is empty");
        }
        for (int i = 0; i < foodList.size(); i++) {

            System.out.println((i + 1) + " " + foodList.get(i).getInfo());

        }
    }
    public void deleteAll(){
        for(int i = 0;i<foodList.size();i++){
            foodList.remove(i);
        }
        System.out.println("Your bag is empty");
    }
}
