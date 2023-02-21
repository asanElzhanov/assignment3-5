package foodService.fastFood;

import foodService.Food;

import java.util.List;

public class Dodo extends FastFood {
    public Dodo(String name, String time, float rating, List<Food> food) {
        super(name, time, rating, food);
        this.preparingTime = "10-15 minutes";
    }

    @Override
    public void createCombo(String choice) {
        for (int i = 0; i < this.food.size(); i++) {
            if(this.food.get(i).getName().equals(choice)){
                this.food.remove(i);
                System.out.println("Here is your " + choice + " combo with drink. Come again!\n");
                break;
            }
        }
        createOrRefreshMenu();
    }
}
