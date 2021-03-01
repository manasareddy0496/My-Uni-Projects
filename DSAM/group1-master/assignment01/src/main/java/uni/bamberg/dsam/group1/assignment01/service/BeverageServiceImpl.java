package uni.bamberg.dsam.group1.assignment01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import uni.bamberg.dsam.group1.assignment01.model.Bottle;
import uni.bamberg.dsam.group1.assignment01.model.Crate;
import uni.bamberg.dsam.group1.assignment01.model.OrderBeverage;
import uni.bamberg.dsam.group1.assignment01.model.OrderItem;
import uni.bamberg.dsam.group1.assignment01.repository.BottleRepository;
import uni.bamberg.dsam.group1.assignment01.repository.CrateRepository;
import uni.bamberg.dsam.group1.assignment01.repository.OrderBeverageRepository;
import uni.bamberg.dsam.group1.assignment01.repository.OrderItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BeverageServiceImpl implements BeverageService{

    private final BottleRepository bottleRepository;
    private final CrateRepository crateRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderBeverageRepository orderBeverageRepository;

    @Autowired
    public BeverageServiceImpl(BottleRepository bottleRepository,
                               CrateRepository crateRepository,
                               OrderItemRepository orderItemRepository,
                               OrderBeverageRepository orderBeverageRepository){
        this.bottleRepository = bottleRepository;
        this.crateRepository = crateRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderBeverageRepository = orderBeverageRepository;
    }

    @Override
    public List<Bottle> getBottles(Model model) {
        return bottleRepository.findAll();
    }

    @Override
    public List<Crate> getCrates(Model model) {
        return crateRepository.findAll();
    }

    @Override
    public void addOrder(Bottle bottle, Crate crate) {

    }

    @Override
    public List<OrderItem> getShoppingCartItems(Model model) {
        return orderItemRepository.findAll();
    }

    @Override
    public List<OrderBeverage> getExistingOrders(Model model) {
        return orderBeverageRepository.findAll();
    }

    @Override
    public Bottle getBottleById(long id) {
        Bottle bottle = null;
        try {
            Optional<Bottle> optional = bottleRepository.findById(id);
            if (optional.isPresent()) {
                bottle = optional.get();
            } else {
                throw new RuntimeException(" Bottle not found for id :: " + id);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return bottle;
    }

    @Override
    public Crate getCrateById(long id) {
        Crate crate = null;
        try {
            Optional<Crate> optional = crateRepository.findById(id);
            if (optional.isPresent()) {
                crate = optional.get();
            } else {
                throw new RuntimeException(" Crate not found for id :: " + id);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return crate;
    }

    @Override
    public boolean addCrateToCart(long id, int noOfCrates, int price) {
        Optional<Crate> optional = crateRepository.findById(id);
        Crate crate = null;
        boolean itemAdded = false;
        if (optional.isPresent()) {
            crate = optional.get();
            if(crate.getCratesInStock() >= noOfCrates){
                int total_price;
                try {
                    int noOfCratesAfter = crate.getCratesInStock() - noOfCrates;
                    total_price = (noOfCrates * price);
                    //update the crate table
                    crate.setCratesInStock(noOfCratesAfter);
                    crateRepository.save(crate);
                    //add item to OrderItem table
                    List<OrderItem> orderItemList = orderItemRepository.findAll();
                    int position = orderItemList.size()+1;
                    OrderItem orderItem=new OrderItem();
                    //orderItem.setOrderItemId(position+1);
                    orderItem.setPosition(String.valueOf(position));
                    orderItem.setPrice(total_price);
                    orderItemRepository.save(orderItem);
                    itemAdded = true;
                }
                catch (Exception ex)
                {
                    int noOfCratesBefore = crate.getCratesInStock() + noOfCrates;
                    crate.setCratesInStock(noOfCratesBefore);
                    crateRepository.save(crate);
                    itemAdded = false;
                }

            }else{
                itemAdded = false;
            }
        } else {
            throw new RuntimeException(" Crate not found for id :: " + id);
        }
        return  itemAdded;
    }

    @Override
    public boolean addBottleToCart(long id, int noOfBottles, int price) {
        Optional<Bottle> optional = bottleRepository.findById(id);
        Bottle bottle = null;
        boolean itemAdded = false;
        if (optional.isPresent()) {
            bottle = optional.get();
            if(bottle.getInStock() >= noOfBottles){
                int total_price;
                try {
                    int noOfBottlesAfter = bottle.getInStock() - noOfBottles;
                    total_price = noOfBottles * price;
                    //update the crate table
                    bottle.setInStock(noOfBottlesAfter);
                    bottleRepository.save(bottle);
                    //add item to OrderItem table
                    List<OrderItem> orderItemList = orderItemRepository.findAll();
                    int position = orderItemList.size()+1;
                    OrderItem orderItem=new OrderItem();
                    //orderItem.setOrderItemId(position+1);
                    orderItem.setPosition(String.valueOf(position));
                    orderItem.setPrice(total_price);
                    orderItemRepository.save(orderItem);
                    itemAdded = true;
                }
                catch (Exception ex){
                    int noOfBottlesBeforw = bottle.getInStock() + noOfBottles;
                    bottle.setInStock(noOfBottlesBeforw);
                    bottleRepository.save(bottle);
                    itemAdded = false;
                }
            }else{
                itemAdded = false;
            }
        } else {
            throw new RuntimeException(" Bottle not found for id :: " + id);
        }
        return  itemAdded;
    }

    @Override
    public Boolean placeOrder(Model model) {
        Boolean isOrderAddedSuccessfully = false;
        List<OrderItem> orderItem = orderItemRepository.findAll();
        OrderBeverage newOrder = new OrderBeverage();
        long exsitingOrders = orderBeverageRepository.count();
        for (OrderItem oI:orderItem)
        {
            newOrder.setPrice(oI.getPrice());
            orderBeverageRepository.save(newOrder);
        }
        orderItemRepository.deleteAll();

        if(orderBeverageRepository.count() == exsitingOrders + orderItem.size())
            isOrderAddedSuccessfully = true;
        return isOrderAddedSuccessfully;
    }

    @Override
    public OrderItem getOrderItemById(long id) {
        OrderItem orderItem = null;
        try {
            Optional<OrderItem> optional = orderItemRepository.findById(id);
            if (optional.isPresent()) {
                orderItem = optional.get();
            } else {
                throw new RuntimeException(" Cart item not found for id :: " + id);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public boolean removeItemFromCart(OrderItem orderItem) {
        boolean isItemdeleted = false;
        try{
            int  cntBeforeDel =  (int) orderItemRepository.count();
            orderItemRepository.delete(orderItem);
            int  cntAfterDel = (int) orderItemRepository.count();
            int v = cntBeforeDel-cntAfterDel;
            if(v == 1){

                isItemdeleted = true;
            }
            else{
                orderItemRepository.save(orderItem);
                isItemdeleted = false;
            }
        }
        catch(Exception ex){
            orderItemRepository.save(orderItem);
        }
      return isItemdeleted;
    }

    @Override
    public boolean addNewBottles(Bottle bottle) {
        boolean isNewBottleAdded = false;
        try{
            bottleRepository.save(bottle);
            isNewBottleAdded = true;
        }
        catch(Exception ex){

        }
        return isNewBottleAdded;
    }
}
