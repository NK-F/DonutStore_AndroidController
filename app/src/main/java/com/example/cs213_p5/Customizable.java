package com.example.cs213_p5;

/**
 * Customizeable Interface
 *
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */

public interface Customizable {
    /**
     * @param obj Object as argument
     * @return true if customization added. False otherwise.
     */
    boolean add(Object obj);

    /**
     * @param obj Object as argument
     * @return true if customization removed. False otherwise.
     */
    boolean remove(Object obj);
}
