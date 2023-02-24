package foodService;

import java.util.ArrayList;
import java.util.List;

public abstract class FoodService {
    protected String name;
    protected String time;
    protected float rating;
    protected List<Food> food;
    protected List<Food> menu;

    protected FoodService(String name, String time, float rating, List<Food> food) {
        this.name = name;
        this.time = time;
        this.rating = rating;
        this.food = food;
        this.menu = createOrRefreshMenu();
    }
    protected void getInfoAboutFood(){
    }
    public void simpleOrder(String choice,ShoppingBag bag){
        for(int i = 0;i <this.food.size();i++){
            if(choice.equalsIgnoreCase(this.food.get(i).getName())){
                bag.addFoodToFoodList(this.food.get(i));
                this.food.remove(i);
                System.out.println("Here is your " + choice + ". Come again!");
                break;
            }
        }
        createOrRefreshMenu();
    }
    protected List<Food> createOrRefreshMenu(){
        List<Food> set = new ArrayList<>();
        set.addAll(this.food);
        for(int i =0;i<set.size();i++){
            for(int j = i + 1; j < set.size();j++){
                if(set.get(i).getName().equals(set.get(j).getName())){
                    set.remove(j);
                    j--;
                }
            }
        }
        return set;
    }
    public void getMenu(){
        System.out.println("Look at the menu\n");
        for(Food fd : this.menu){
            System.out.println(fd.getInfo());
        }
        System.out.println();
    }
    public void getInfoAboutRestaurant(){
        System.out.println(this.name + ", " + this.time + ", rating: " + this.rating);
    }
    public List<Food> getFood(){
        return this.food;
    }
}