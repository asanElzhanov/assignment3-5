package foodService.fastFood;

import foodService.Food;

import java.util.List;

public class BurgerKing extends FastFood{
    public BurgerKing(String name, String time, float rating, List<Food> food) {
        super(name, time, rating, food);
        this.preparingTime = "3-7 minutes";
    }
    @Override
    public void createCombo(String choice) {
        for (int i = 0; i < this.food.size(); i++) {
            if(this.food.get(i).getName().equals(choice)){
                this.food.remove(i);
                System.out.println("Here is your " + choice + " burger combo with drink and nuggets. Come again!\n");
                break;
            }
        }
        createOrRefreshMenu();
    }
}
