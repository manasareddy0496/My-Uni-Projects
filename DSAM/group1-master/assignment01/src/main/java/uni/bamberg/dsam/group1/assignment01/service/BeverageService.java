package uni.bamberg.dsam.group1.assignment01.service;

import org.springframework.ui.Model;
import uni.bamberg.dsam.group1.assignment01.model.Bottle;
import uni.bamberg.dsam.group1.assignment01.model.Crate;
import uni.bamberg.dsam.group1.assignment01.model.OrderBeverage;
import uni.bamberg.dsam.group1.assignment01.model.OrderItem;

import java.util.List;

public interface BeverageService {

    List<Bottle> getBottles(Model model);
    List<Crate> getCrates(Model model);
    void addOrder(Bottle bottle, Crate crate);
    List<OrderItem> getShoppingCartItems(Model model);
    List<OrderBeverage> getExistingOrders(Model model);
    Bottle getBottleById(long id);
    Crate getCrateById(long id);
    boolean addCrateToCart(long id,int noOfCrates, int price);
    boolean addBottleToCart(long id,int noOfBottles, int price);
    Boolean placeOrder(Model model);
    OrderItem getOrderItemById(long id);
    boolean removeItemFromCart(OrderItem orderItem);
    boolean addNewBottles(Bottle bottle);
}
