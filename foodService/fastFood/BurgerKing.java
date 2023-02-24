package foodService.fastFood;

import foodService.Food;
import foodService.ShoppingBag;

import java.util.List;

public class BurgerKing extends FastFood{
    private static BurgerKing instance = null;
    private BurgerKing(String name, String time, float rating, List<Food> food) {
        super(name, time, rating, food);
        this.preparingTime = "3-7 minutes";
    }
    public static synchronized BurgerKing getInstance(String name, String time, float rating, List<Food> food){
        if(instance == null){
            instance = new BurgerKing(name, time, rating, food);
        }
        return instance;
    }
    public static synchronized BurgerKing getInstance(){
        return instance;
    }
    @Override
    public void createCombo(String choice, ShoppingBag bag) {
        for (int i = 0; i < this.food.size(); i++) {
            if(this.food.get(i).getName().equalsIgnoreCase(choice)){
                bag.addFoodToFoodList(this.food.get(i));
                this.food.remove(i);
                System.out.println("Here is your " + choice + " burger combo with drink and nuggets. Come again!\n");
                break;
            }
        }
        createOrRefreshMenu();
    }
}
