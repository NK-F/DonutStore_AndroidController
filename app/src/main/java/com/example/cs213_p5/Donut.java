package com.example.cs213_p5;

/**
 * Model Class representing the donut object.
 *
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */

public class Donut extends MenuItem {
    private String type;
    private String flavor;

    /**
     *
     * @param type String Representing Type of Donut
     * @param flavor String Representing Donut Flavor
     */
    public Donut(String type, String flavor) {
        this.type = type;
        this.flavor = flavor;
    }

    /**
     *
     * @return double calculated item price
     */
    @Override
    public double itemPrice() {
        if (this.type.equals("Yeast Donut")) {
            return 1.69;
        } else if (this.type.equals("Cake Donut")) {
            return 1.79;
        } else if (this.type.equals("Donut Holes")) {
            return 0.39;
        } else {
            return 0.00;
        }
    }

    /**
     *
     * @param obj Object as argument
     * @return true if objects are logically equivelent. False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Donut) {
            Donut donut_to_compare_to = (Donut) obj;
            if (this.type.equals(donut_to_compare_to.type) && this.flavor.equals(donut_to_compare_to.flavor)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return String representing donut object
     */
    @Override
    public String toString() {
        return "Donut Type: " + this.type + " Flavor: " + this.flavor;
    }
}
