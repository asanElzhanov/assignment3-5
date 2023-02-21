package foodService.fastFood;

import foodService.Food;
import foodService.FoodService;

import java.util.List;

public abstract class FastFood extends FoodService {
    protected String preparingTime;
    protected FastFood(String name, String time, float rating, List<Food> food) {
        super(name, time, rating, food);
    }
    protected void createCombo(String choice){
    }
    @Override
    public void getInfoAboutFood(){
        System.out.println("Here's the menu:");
        for(Food fd : this.menu){
            System.out.println(fd.getInfo());
        }
    }
    @Override
    public void simpleOrder(String choice){
        for(int i = 0; i<this.food.size();i++){
            if(choice.equalsIgnoreCase(this.food.get(i).getName())){
                this.food.remove(i);
                System.out.println("Here is your " + choice + ". Come again!");
                break;
            }
        }
        createOrRefreshMenu();
    }
}
