import foodService.*;
import foodService.fastFood.*;
import foodService.japanCuisine.*;
import foodService.coffee.*;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Food> distribution(String restaurant, List<Food> foodList) {
        List<Food> foods = new ArrayList<>();
        if (restaurant.equalsIgnoreCase("CoffeeBoom")) {
            for (int i = 0; i < 45; i++) {
                foods.add(foodList.get(i));
            }
        } else if (restaurant.equalsIgnoreCase("Yakitoria")) {
            for (int i = 45; i < 105; i++) {
                foods.add(foodList.get(i));
            }
        } else if (restaurant.equalsIgnoreCase("Dodo")) {
            for (int i = 105; i < 135; i++) {
                foods.add(foodList.get(i));
            }
        } else if (restaurant.equalsIgnoreCase("BurgerKing")) {
            for (int i = 135; i < 165; i++) {
                foods.add(foodList.get(i));
            }
        }
        return foods;
    }

    public static List<Food> getFoodFromDatabase() {
        String connectionUrl = "jdbc:mysql://localhost:3306/foodservice";
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        List<Food> foodList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl, "root", "root");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM food");
            while (rs.next()) {
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                Food food = new Food(name, price);
                foodList.add(food);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return foodList;
    }

    public static void restaurantsInfo(BurgerKing b, Dodo d, CoffeeBoom c, Yakitorya y) {
        System.out.print("1.");b.getInfoAboutRestaurant();
        System.out.print("2.");d.getInfoAboutRestaurant();
        System.out.print("3.");c.getInfoAboutRestaurant();
        System.out.print("4.");y.getInfoAboutRestaurant();
    }

    public static String findFood(List<Food> foodList, String choice) {
        for (Food el : foodList) {
            if (choice.equalsIgnoreCase(el.getName())) {
                return choice;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Food> foodList = getFoodFromDatabase();
        ShoppingBag bag = new ShoppingBag();
        BurgerKing.getInstance("Burger King", "10:00 - 22:00", 83.9f, distribution("BurgerKing", foodList));
        Dodo.getInstance("Dodo", "9:00 - 22:00", 89.1f, distribution("dodo", foodList));
        CoffeeBoom.getInstance("CoffeeBoom", "10:00 - 21:00", 80.4f, distribution("CoffeeBoom", foodList), true);
        Yakitorya.getInstance("Yakitorya", "10:00 - 3:00", 91.5f, distribution("Yakitoria", foodList), true);
        System.out.println("Welcome to our app! What would you prefer to eat today?\n");
        byte choice = -1;
        while (true) {
            System.out.println("\n\nHere is the list of restaurants:");
            restaurantsInfo(BurgerKing.getInstance(), Dodo.getInstance(), CoffeeBoom.getInstance(), Yakitorya.getInstance());
            System.out.println("5.Check shopping bag");
            System.out.println("6.Remove from shopping bag");
            System.out.println("\nFrom where you want to order the food? Write '0' to exit the menu");
            choice = scan.nextByte();
            if (choice == 0) {
                System.out.println("Thank you for using our app");
                break;
            } else if (choice == 1) {
                System.out.println("Welcome to BurgerKing!\n");
                BurgerKing.getInstance().getMenu();
                System.out.println("What you prefer to eat?\n");
                scan.nextLine();
                String foodChoice = scan.nextLine();
                if (findFood(BurgerKing.getInstance().getFood(), foodChoice) == null) {
                    System.out.println("I'm sorry, we don't have it now\n");
                } else {
                    System.out.println("Do you want a combo? 1 - yes, 0 - no\n");
                    byte isCombo = scan.nextByte();
                    while (isCombo != 0 && isCombo != 1) {
                        System.out.println("Incorrect input, try again");
                        isCombo = scan.nextByte();
                    }
                    if (isCombo == 1) {
                        BurgerKing.getInstance().createCombo(foodChoice,bag);
                    } else {
                        BurgerKing.getInstance().simpleOrder(foodChoice,bag);
                    }
                }
            } else if (choice == 2) {
                System.out.println("Welcome to Dodo!\n");
                Dodo.getInstance().getMenu();
                System.out.println("What you prefer to eat?\n");
                scan.nextLine();
                String foodChoice = scan.nextLine();
                if (findFood(Dodo.getInstance().getFood(), foodChoice) == null) {
                    System.out.println("I'm sorry, we don't have it now\n");
                } else {
                    System.out.println("Do you want a combo? 1 - yes, 0 - no\n");
                    byte isCombo = scan.nextByte();
                    while (isCombo != 0 && isCombo != 1) {
                        System.out.println("Incorrect input, try again");
                        isCombo = scan.nextByte();
                    }
                    if (isCombo == 1) {
                        Dodo.getInstance().createCombo(foodChoice,bag);
                    } else {
                        Dodo.getInstance().simpleOrder(foodChoice,bag);
                    }
                }
            } else if (choice == 3) {
                System.out.println("Welcome to CoffeeBoom!\n");
                CoffeeBoom.getInstance().getMenu();
                System.out.println("What would you like?\n");
                scan.nextLine();
                String foodChoice = scan.nextLine();
                if (findFood(CoffeeBoom.getInstance().getFood(), foodChoice) == null) {
                    System.out.println("I'm sorry, we don't have it now\n");
                } else {
                    System.out.println("Do you want a bakery? 1 - yes, 0 - no\n");
                    byte withBakery = scan.nextByte();
                    while (withBakery != 0 && withBakery != 1) {
                        System.out.println("Incorrect input, try again");
                        withBakery = scan.nextByte();
                    }
                    if (withBakery == 1) {
                        System.out.println("Choice bakery:\n");
                        System.out.println("Croissants\n" +
                                "Bagels\n" +
                                "Muffins\n" +
                                "Scones\n" +
                                "Danish pastries\n" +
                                "Cinnamon rolls\n" +
                                "Coffee cake\n" +
                                "Biscotti\n" +
                                "Donuts");
                        scan.nextLine();
                        String choiceBakery = scan.nextLine();
                        CoffeeBoom.getInstance().orderCoffeeWithBakery(foodChoice, choiceBakery,bag);
                    } else {
                        CoffeeBoom.getInstance().simpleOrder(foodChoice,bag);
                    }
                }
            } else if (choice == 4) {
                System.out.println("Welcome to Yakitoria!\n");
                System.out.println("What would you like to eat? Set or not? 1 - set, 2 - not set\n");
                byte isSet = scan.nextByte();
                if(isSet == 1){
                    Yakitorya.getInstance().getMenu();
                    System.out.println("How many do you want to order?\n");
                    byte howMany = scan.nextByte();
                    System.out.println("Ok, choose it from menu\n");
                    scan.nextLine();
                    ArrayList<String> order = new ArrayList<>();
                    for(int i = 0;i<howMany;i++){
                        String s = scan.nextLine();
                        order.add(s);
                    }
                    Yakitorya.getInstance().orderSet(order,bag);
                }
                else{
                    System.out.println("What do you prefer to eat?\n");
                    Yakitorya.getInstance().getMenu();
                    scan.nextLine();
                    String choiceFood = scan.nextLine();
                    Yakitorya.getInstance().simpleOrder(choiceFood,bag);
                }
            }
            else if(choice == 5){
                bag.getShoppingBag();
            }
            else if(choice == 6){
                bag.getShoppingBag();
                System.out.println("What you prefer to remove from your shopping bag? 0 to delete all\n");
                byte choiceInBag = scan.nextByte();
                if(choiceInBag == 0){
                    bag.deleteAll();
                }
                else{
                    bag.deleteFromFoodList(choiceInBag);
                }
            }
        }
        bag.getShoppingBag();
    }
}