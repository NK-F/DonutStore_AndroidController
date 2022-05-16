package com.example.cs213_p5;

import java.util.ArrayList;

/**
 * Model Class representing the coffee object.
 *
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */

public class Coffee extends MenuItem implements Customizable {

    private String Size;
    private ArrayList<String> customizations;

    /**
     * @param Size String Representing Size of Coffee Ordered.
     */
    public Coffee(String Size) {
        this.Size = Size;
        customizations = new ArrayList<String>();
    }

    /**
     * @param Size          String Representing Size of Coffee Ordered.
     * @param Customization String Representing a customization to the Coffe object.
     */
    public Coffee(String Size, String Customization) {
        this.Size = Size;
        customizations = new ArrayList<String>();
        this.customizations.add(Customization);
    }

    /**
     * Implements Customizeable
     *
     * @param obj Object as argument
     * @return true if customization was added to coffee object. False otherwise.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Coffee) {
            Coffee customization_to_add = (Coffee) obj;
            if (customization_to_add.customizations.size() == 1) {
                this.customizations.add(customization_to_add.customizations.get(0));
                return true;
            }
        }
        return false;
    }

    /**
     * Implements Customizeable
     *
     * @param obj Object as argument
     * @return true if customization was removed from coffee object. False otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Coffee) {
            Coffee customization_to_remove = (Coffee) obj;
            if (customizations.size() > 0) {
                int index = -1;
                for (int i = 0; i < customizations.size(); i++) {
                    if (customizations.get(i).equals(customization_to_remove.customizations.get(0))) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    customizations.remove(index);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return double calculated item price
     */
    @Override
    public double itemPrice() {
        double cost = 1.69;
        if (this.Size.equals("Tall")) {
            cost += 0.40;
        } else if (this.Size.equals("Grande")) {
            cost += 0.80;
        } else if (this.Size.equals("Venti")) {
            cost += 1.20;
        }
        cost += (customizations.size() * 0.30);
        return cost;
    }

    /**
     * @return String representing coffee object
     */
    @Override
    public String toString() {
        String custom_to_String = "";
        for (int i = 0; i < customizations.size(); i++) {
            if (i == 0) {
                custom_to_String = customizations.get(i);
            } else {
                custom_to_String += "," + customizations.get(i);
            }
        }
        return "Coffee Size: " + this.Size + " Customizations: " + custom_to_String;
    }

}
