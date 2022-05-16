package com.example.cs213_p5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * View class for ordering donuts.
 *
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */
public class OrderDonutsActivity extends AppCompatActivity {

    /**
     * Void responsible for above the fold content loading
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_donuts);

        set_spinner_type();
        set_spinner_flavor();
        set_quantity_select();
    }

    /**
     * void responsible for adding donut to order
     *
     * @param v
     */
    public void action_submit(View v) {
        Order currentOrder = MainActivity.currentOrder;
        String type = this.getType();
        String flavor = this.getFlavor();
        int Quantity = this.getQuantity();

        Donut donut_to_add = new Donut(type, flavor);

        for (int i = 0; i < Quantity; i++) {
            currentOrder.add(donut_to_add);
        }

        Context context = getApplicationContext();
        CharSequence text = "Item Added to Order!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        finish();
    }

    /**
     * void responsible for updating displayed subtotal
     */
    private void update_price() {

        double cost = 0.00;

        switch (this.getType()) {
            case "Yeast Donut":
                cost = 1.59;
                break;
            case "Cake Donut":
                cost = 1.79;
                break;
            case "Donut Holes":
                cost = 0.39;
                break;
        }

        int Quantity = this.getQuantity();
        cost *= Quantity;

        this.setTextPrice(cost);
    }

    /**
     * helper method to return selected element from spinner type
     *
     * @return String representation of donut type
     */
    private String getType() {
        Spinner spinnerType = (Spinner) findViewById(R.id.spinnerType);
        return spinnerType.getSelectedItem().toString();
    }

    /**
     * helper method to return selected element from spinner flavor
     *
     * @return String representation of donut flavor
     */
    private String getFlavor() {
        Spinner spinnerFlavor = (Spinner) findViewById(R.id.spinnerFlavor);
        return spinnerFlavor.getSelectedItem().toString();
    }

    /**
     * Helper method to return selected quantity
     *
     * @return Integer quantity
     */
    private int getQuantity() {
        NumberPicker donutQuantity = (NumberPicker) findViewById(R.id.donutQuantity);
        return donutQuantity.getValue();
    }

    /**
     * void responsible for populating spinner type
     */
    private void set_spinner_type() {
        Spinner spinnerType = (Spinner) findViewById(R.id.spinnerType);
        String[] spinnerOptions = new String[]{
                "Yeast Donut", "Cake Donut", "Donut Holes"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                update_price();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    /**
     * void responsible for populating spinner flavor
     */
    private void set_spinner_flavor() {
        Spinner spinnerFlavor = (Spinner) findViewById(R.id.spinnerFlavor);
        String[] spinnerOptions = new String[]{
                "Flavor 1", "Flavor 2", "Flavor 3", "Flavor 4", "Flavor 5", "Flavor 6", "Flavor 7", "Flavor 8", "Flavor 9", "Flavor 10", "Flavor 11", "Flavor 12"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFlavor.setAdapter(adapter);
        spinnerFlavor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                update_price();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    /**
     * void responsible for formatting quantity NumberPicker
     */
    private void set_quantity_select() {
        // Defines Quantity Number Picker
        NumberPicker select_quantity = (NumberPicker) findViewById(R.id.donutQuantity);
        select_quantity.setMinValue(1);
        select_quantity.setMaxValue(10);
        select_quantity.setValue(1);
        select_quantity.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                update_price();
            }
        });
    }

    /**
     * void responsible for updating subtotal text.
     *
     * @param cost
     */
    private void setTextPrice(Double cost) {
        TextView mTextView = (TextView) findViewById(R.id.textDonutPrice);
        mTextView.setText("Subtotal: $" + (Math.round(cost * 100.0) / 100.0));
    }

}