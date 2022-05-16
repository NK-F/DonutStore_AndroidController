package com.example.cs213_p5;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class for Order Object. Implements Customizeable Interface.
 *
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */

public class Order implements Customizable {
    private static int global_orderNumber = 0;
    private int orderNumber;
    private String orderStatus;
    private List<MenuItem> items;

    /**
     * Default Constructor. Accepts No Parameters
     */
    public Order() {
        global_orderNumber++;
        orderNumber = global_orderNumber;
        items = new ArrayList<>();
        orderStatus = "In-Progress";
    }

    /**
     * @param obj Object as argument
     * @return true if MenuItem was added to order. False otherwise.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof MenuItem) {
            MenuItem Item_to_add = (MenuItem) obj;
            items.add(Item_to_add);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param obj Object as argument
     * @return true if MenuItem was removed from order. False otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof MenuItem) {
            MenuItem Item_to_remove = (MenuItem) obj;
            int index = -1;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).equals(Item_to_remove)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                items.remove(index);
                return true;
            }
        }
        return false;
    }

    /**
     * @return integer value corresponding to current Order Number.
     */

    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * @return Array of all MenuItems contained within current order.
     */

    public MenuItem[] getOrderItems() {
        MenuItem[] order_items = new MenuItem[items.size()];
        for (int i = 0; i < items.size(); i++) {
            order_items[i] = items.get(i);
        }
        return order_items;
    }

    /**
     * @return True if order contains no items. Else otherwise.
     */
    public boolean isEmpty() {
        if (items.size() >= 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @return Current Order Status represented as a String
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus String denoting orderStatus. Used to validate current order Status.
     * @return true if status was set correctly. False otherwise.
     */
    public boolean setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return true;
    }

    /**
     * @return Formatted String representing Order Object.
     */
    @Override
    public String toString() {
        String toReturn = "Order Number: " + orderNumber + "\n";
        for (int i = 0; i < items.size(); i++) {
            toReturn += items.get(i).toString() + "\n";
        }
        return toReturn;
    }

}
