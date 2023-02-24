package foodService.fastFood;

import foodService.Food;
import foodService.ShoppingBag;

import java.util.List;

public class Dodo extends FastFood {
    private static Dodo instance = null;
    private Dodo(String name, String time, float rating, List<Food> food) {
        super(name, time, rating, food);
        this.preparingTime = "10-15 minutes";
    }
    public static synchronized Dodo getInstance(String name, String time, float rating, List<Food> food){
        if(instance == null){
            instance = new Dodo(name, time, rating, food);
        }
        return instance;
    }
    public static synchronized Dodo getInstance(){
        return instance;
    }
    @Override
    public void createCombo(String choice, ShoppingBag bag) {
        for (int i = 0; i < this.food.size(); i++) {
            if(this.food.get(i).getName().equalsIgnoreCase(choice)){
                bag.addFoodToFoodList(this.food.get(i));
                this.food.remove(i);
                System.out.println("Here is your " + choice + " combo with drink. Come again!\n");
                break;
            }
        }
        createOrRefreshMenu();
    }
}
