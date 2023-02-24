package foodService.japanCuisine;

import foodService.Food;
import foodService.FoodService;
import foodService.ShoppingBag;

import java.util.ArrayList;
import java.util.List;

public abstract class JapanRestaurant extends FoodService {
    boolean setsInMenu;

    protected JapanRestaurant(String name, String time, float rating, List<Food> food) {
        super(name, time, rating, food);
    }

    protected void orderSet(ArrayList<String> choice, ShoppingBag bag) {
    }
    @Override
    public void getInfoAboutFood(){
        System.out.println("Here's the menu:");
        for(Food fd : this.menu){
            System.out.println(fd.getInfo());
        }
    }
    @Override
    public void simpleOrder(String choice, ShoppingBag bag){
        for(int i = 0; i<this.food.size();i++){
            if(choice.equalsIgnoreCase(this.food.get(i).getName())){
                bag.addFoodToFoodList(this.food.get(i));
                this.food.remove(i);
                System.out.println("Here is your " + choice + ". Come again!");
                break;
            }
        }
        createOrRefreshMenu();
    }
}
