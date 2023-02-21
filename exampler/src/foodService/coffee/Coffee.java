package foodService.coffee;

import foodService.Food;
import foodService.FoodService;

import java.util.List;

public abstract class Coffee extends FoodService {
    protected boolean withBakery;
    protected Coffee(String name, String time, float rating, List<Food> food, boolean bakery) {
        super(name, time, rating, food);
        this.withBakery = bakery;
    }
    protected void orderCoffeeWithBakery(String choiceCoffee,String choiceBakery){
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
