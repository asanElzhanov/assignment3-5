package foodService.japanCuisine;

import foodService.Food;
import foodService.ShoppingBag;
import foodService.fastFood.BurgerKing;
import foodService.fastFood.Dodo;

import java.util.ArrayList;
import java.util.List;

public class Yakitorya extends JapanRestaurant {
    private static Yakitorya instance = null;

    private Yakitorya(String name, String time, float rating, List<Food> food, boolean setInMenu) {
        super(name, time, rating, food);
        setInMenu = true;
    }

    public static synchronized Yakitorya getInstance(String name, String time, float rating, List<Food> food, boolean setInMenu) {
        if (instance == null) {
            instance = new Yakitorya(name, time, rating, food, setInMenu);
        }
        return instance;
    }
    public static synchronized Yakitorya getInstance(){
        return instance;
    }
    @Override
    public void orderSet(ArrayList<String> choice, ShoppingBag bag) {
        boolean enoughIngredients = false;
        String missing = "";
        for (String order : choice) {
            enoughIngredients = false;
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
                    if (choice.get(i).equalsIgnoreCase(this.food.get(j).getName())) {
                        bag.addFoodToFoodList(this.food.get(i));
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

