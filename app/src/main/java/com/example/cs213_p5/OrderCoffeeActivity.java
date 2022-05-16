package com.example.cs213_p5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * View class for ordering coffee.
 *
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */
public class OrderCoffeeActivity extends AppCompatActivity {

    /**
     * Void responsible for above the fold content loading
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_coffee);

        Spinner s = (Spinner) findViewById(R.id.size);
        String[] spinnerOptions = new String[]{
                "Short", "Tall", "Grande", "Venti"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                update_price();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        NumberPicker select_quantity = (NumberPicker) findViewById(R.id.coffeeQuantity);
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
     * void responsible for adding coffee to order
     *
     * @param v
     */
    public void action_submit(View v) {
        Order currentOrder = MainActivity.currentOrder;

        String size = this.getSize();

        Coffee coffee_to_add = new Coffee(size);

        if (this.getCreamSelection()) {
            coffee_to_add.add(new Coffee(size, "Cream"));
        }
        if (this.getSyrupSelection()) {
            coffee_to_add.add(new Coffee(size, "Syrup"));
        }
        if (this.getMilkSelection()) {
            coffee_to_add.add(new Coffee(size, "Milk"));
        }
        if (this.getCaramelSelection()) {
            coffee_to_add.add(new Coffee(size, "Caramel"));
        }
        if (this.getWhippedCreamSelection()) {
            coffee_to_add.add(new Coffee(size, "Whipped Cream"));
        }

        int Quantity = this.getQuantity();

        for (int i = 0; i < Quantity; i++) {
            currentOrder.add(coffee_to_add);
        }

        Context context = getApplicationContext();
        CharSequence text = "Item Added to Order!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        finish();

    }

    /**
     * void responsible for handling add in selection
     *
     * @param v
     */
    public void action_select_add_in(View v) {
        update_price();
    }

    /**
     * void responsible for calculating subtotal price
     */
    private void update_price() {

        double cost = 1.69;

        switch (this.getSize()) {
            case "Tall":
                cost += 0.40;
                break;
            case "Grande":
                cost += 0.80;
                break;
            case "Venti":
                cost += 1.20;
                break;
        }

        if (this.getCreamSelection()) {
            cost += 0.30;
        }
        if (this.getSyrupSelection()) {
            cost += 0.30;
        }
        if (this.getMilkSelection()) {
            cost += 0.30;
        }
        if (this.getCaramelSelection()) {
            cost += 0.30;
        }
        if (this.getWhippedCreamSelection()) {
            cost += 0.30;
        }

        int Quantity = this.getQuantity();
        cost *= Quantity;

        this.setTextPrice(cost);
    }

    /**
     * Helper method to return coffee size
     *
     * @return String representation fo spinner size
     */
    private String getSize() {
        Spinner size = (Spinner) findViewById(R.id.size);
        return size.getSelectedItem().toString();
    }

    /**
     * Helper method to return quantity requested
     *
     * @return Integer quantity
     */
    private int getQuantity() {
        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.coffeeQuantity);
        return numberPicker.getValue();
    }

    /**
     * @return True if cream is selected, false otherwise.
     */
    private boolean getCreamSelection() {
        CheckBox option_add_cream = (CheckBox) findViewById(R.id.option_add_cream);
        return option_add_cream.isChecked();
    }

    /**
     * @return True if syrup is selected, false otherwise.
     */
    private boolean getSyrupSelection() {
        CheckBox option_add_syrup = (CheckBox) findViewById(R.id.option_add_syrup);
        return option_add_syrup.isChecked();
    }

    /**
     * @return True if milk is selected, false otherwise.
     */
    private boolean getMilkSelection() {
        CheckBox option_add_milk = (CheckBox) findViewById(R.id.option_add_milk);
        return option_add_milk.isChecked();
    }

    /**
     * @return True if caramel is selected, false otherwise.
     */
    private boolean getCaramelSelection() {
        CheckBox option_add_caramel = (CheckBox) findViewById(R.id.option_add_caramel);
        return option_add_caramel.isChecked();
    }

    /**
     * @return True if whipped cream is selected, false otherwise.
     */
    private boolean getWhippedCreamSelection() {
        CheckBox option_add_whipped_cream = (CheckBox) findViewById(R.id.option_add_whipped_cream);
        return option_add_whipped_cream.isChecked();
    }

    /**
     * void responsible for updating subtotal text.
     *
     * @param cost
     */
    private void setTextPrice(Double cost) {
        TextView mTextView = (TextView) findViewById(R.id.textCoffeePrice);
        mTextView.setText("Subtotal: $" + (Math.round(cost * 100.0) / 100.0));
    }

}