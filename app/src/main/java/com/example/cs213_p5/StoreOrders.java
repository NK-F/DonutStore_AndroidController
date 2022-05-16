package com.example.cs213_p5;

import java.util.ArrayList;
import java.util.List;

/**
 * Model Class representing the storeOrders object.
 *
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */

public class StoreOrders implements Customizable {

    private List<Order> orders;

    public StoreOrders() {
        orders = new ArrayList<>();
    }

    /**
     * Implements Customizeable
     *
     * @param obj Object as argument
     * @return true if customization was added to storeOrders. False otherwise.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Order) {
            Order order_to_add = (Order) obj;
            int index = -1;
            for (int i = 0; i < orders.size(); i++) {
                if ((orders.get(i).getOrderNumber()) == (order_to_add.getOrderNumber())) {
                    index = i;
                    break;
                } else {
                }
            }
            if (index == -1) {
                orders.add(order_to_add);
                return true;
            }
        }
        return false;
    }

    /**
     * Implements Customizeable
     *
     * @param obj Object as argument
     * @return true if customization was removed from storeOrders. False otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Order) {
            Order order_to_remove = (Order) obj;
            int index = -1;
            for (int i = 0; i < orders.size(); i++) {
                if ((orders.get(i).getOrderNumber()) == (order_to_remove.getOrderNumber())) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                orders.remove(index);
                return true;
            }
        }
        return false;
    }

    /**
     * @return returns collection of the String representations of all orders in the current store.
     */
    public String[] getOrderString() {
        String[] toReturn = new String[orders.size()];
        for (int i = 0; i < orders.size(); i++) {
            if (!(i == (orders.size() - 1))) {
                toReturn[i] = orders.get(i).toString() + "\n";
            } else {
                toReturn[i] = orders.get(i).toString();
            }
        }
        return toReturn;
    }

    /**
     * @return returns collection of all orders in the current store.
     */
    public Order[] getOrders() {
        Order[] toReturn = new Order[orders.size()];
        for (int i = 0; i < orders.size(); i++) {
            toReturn[i] = orders.get(i);
        }
        return toReturn;
    }
}
