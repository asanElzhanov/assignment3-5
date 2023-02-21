package foodService.japanCuisine;

import foodService.Food;

import java.util.ArrayList;
import java.util.List;

public class Yakitorya extends JapanRestaurant {
    public Yakitorya(String name, String time, float rating, List<Food> food, boolean setInMenu) {
        super(name, time, rating, food);
        setInMenu = true;
    }
    @Override
    public void orderSet(ArrayList<String> choice) {
        boolean enoughIngredients = false;
        String missing = "";
        for (String order : choice) {
            for (Food checkAvailability : this.food) {
                if (checkAvailability.getName().equalsIgnoreCase(order)) {
                    enoughIngredients = true;
                    break;
                }
            }
            if (!enoughIngredients) {
                missing = order;
                break;
            }
        }
        if (enoughIngredients) {
            for (int i = 0; i < choice.size(); i++) {
                for (int j = 0; j < this.food.size(); j++) {
                    if (choice.get(i).equals(this.food.get(j).getName())) {
                        this.food.remove(j);
                        break;
                    }
                }
            }
            System.out.println("Here is your set:");
            for (String s : choice) {
                System.out.print(s + ", ");
            }
        } else {
            System.out.println("We don't have" + missing + " right now, please come back later");
        }
        createOrRefreshMenu();
    }
}

