package uni.bamberg.dsam.group1.assignment01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import uni.bamberg.dsam.group1.assignment01.model.Bottle;
import uni.bamberg.dsam.group1.assignment01.model.Crate;
import uni.bamberg.dsam.group1.assignment01.model.OrderItem;
import uni.bamberg.dsam.group1.assignment01.service.BeverageService;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.io.IOException;

@Controller
//@RequestMapping(value = "beverages")
public class BeverageController {

    @Autowired
    private BeverageService beverageService;

    @GetMapping("/beverages")
    public String getBeverages(Model model){
        model.addAttribute("bottles",beverageService.getBottles(model));
        model.addAttribute("crates",beverageService.getCrates(model));
        //model.addAttribute("bottle", new Bottle());
        return  "Homepage";
    }

    @GetMapping("/showCartItems")
    public String getCartItems(Model model){
        model.addAttribute("items",beverageService.getShoppingCartItems(model));
        return "CartPage";
    }

    @GetMapping("/showOrderItems")
    public String getOrderItems(Model model){
        model.addAttribute("orders", beverageService.getExistingOrders(model));
        return "OrdersPage";
    }

    @GetMapping("/showBottleFormForUpdate/{id}")
    public String getBottleById(@PathVariable(value = "id") long id, Model model){
        Bottle bottle = beverageService.getBottleById(id);
        if(bottle != null){
            model.addAttribute("bottle", bottle);
            return "AddBottleToCart";
        }
        else
            return "Messages_Failure";
    }

    @GetMapping("/showCrateFormForUpdate/{id}")
    public String getCrateById(@PathVariable(value = "id") long id, Model model){
        Crate crate = beverageService.getCrateById(id);
        if(crate != null){
            model.addAttribute("crate", crate);
            return "AddCrateToCart";
        }
        else
            return "Messages_Failure";
    }

    @PostMapping("/saveCrateToCart")
    public String addCrateToCart(@Valid Crate crate, BindingResult result, Model model){
        boolean addToCart;
        if (result.hasErrors()) {
            return "AddCrateToCart";
        }else{
           addToCart = beverageService.addCrateToCart(crate.getCrateId(),crate.getNoOfRequiredCrates(),crate.getPrice());
           if(addToCart)
                return "Messages";
           else
                return "Messages_Failure";
        }
    }

    @PostMapping("/saveBottleToCart")
    public String addBottleToCart(@Valid Bottle bottle, Errors errors, Model model){
        boolean addToCart;
        if (errors.hasErrors()) {
            return "AddBottleToCart";
        }else{
            addToCart = beverageService.addBottleToCart(bottle.getBottleId(),bottle.getNoOfRequiredBottles(),bottle.getPrice());
            if(addToCart)
                return "Messages";
            else
                return "Messages_Failure";
        }
    }

    @PostMapping("/saveItemsToOrder")
    public String addCartItemsToOrder(@Valid OrderItem orderitem, Model model) throws IOException {
        System.out.println("Method is : addCartItemsToOrder");
        Boolean isOrderSuccess = beverageService.placeOrder(model);
        if(isOrderSuccess)
            return "redirect:/beverages";
        else
            return "Messages_Failure";
    }

    @GetMapping("/showBottle")
    public String showAddBottle(){
        return "AddBottle";
    }

    @PostMapping("/saveBottle")
    public String addNewBottle(@Valid Bottle bottle, Errors errors, Model model){
        boolean isAdditionSuccess = false;
        if (errors.hasErrors()) {
            return "AddBottle";
        }
        else{
            isAdditionSuccess = beverageService.addNewBottles(bottle);
            if(isAdditionSuccess)
                return "Messages";
            else
                return "Messages_Failure";
        }

    }

    @GetMapping("/deleteOrderItems/{id}")
    public String deleteItemsFromCart(@PathVariable(value = "id") long id, Model model){
        OrderItem orderItem =  beverageService.getOrderItemById(id);
        boolean isItemDeleted;
        if(orderItem != null){
            isItemDeleted = beverageService.removeItemFromCart(orderItem);
        }
        return "redirect:/showCartItems";
    }
}
