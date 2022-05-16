package com.example.cs213_p5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * View class representing single order.
 *
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */
public class ViewOrderActivity extends AppCompatActivity {

    private String SelectedItem;
    private int SelectionIndex;

    /**
     * Void responsible for above the fold content loading
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        ListView list = (ListView) findViewById(R.id.singleOrderListView);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                for (int a = 0; a < adapterView.getChildCount(); a++) {
                    adapterView.getChildAt(a).setBackgroundColor(Color.TRANSPARENT);
                }
                view.setBackgroundColor(Color.GRAY);
                SelectedItem = list.getItemAtPosition(i).toString();
                SelectionIndex = i;

            }
        });
        this.update_listView();
    }

    /**
     * void responsible for submitting order
     *
     * @param v
     */
    public void action_submit_order(View v) {
        Context context = getApplicationContext();
        CharSequence text = "";

        if (!(MainActivity.currentOrder.isEmpty())) {
            MainActivity.allOrders.add(MainActivity.currentOrder);
            MainActivity.currentOrder = new Order();
            text = "Order Submitted!";
        } else {
            text = "Order Aborted. Order Is Empty.";
        }

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        finish();

    }

    /**
     * Void responsible for removing selected item from order in listView
     *
     * @param v
     */
    public void action_remove_item(View v) {
        if (SelectionIndex != -1) {
            if (SelectedItem != null) {
                MenuItem[] order_items = MainActivity.currentOrder.getOrderItems();
                for (int i = 0; i < order_items.length; i++) {
                    if (order_items[i].toString().equals(SelectedItem)) {
                        MainActivity.currentOrder.remove(order_items[i]);
                        this.update_listView();
                        break;
                    }
                }
            }

        }
    }

    /**
     * void responsible for population and update of the listView
     */
    private void update_listView() {
        SelectedItem = "";
        SelectionIndex = -1;

        ListView list = (ListView) findViewById(R.id.singleOrderListView);

        MenuItem[] order_items = MainActivity.currentOrder.getOrderItems();

        ArrayList<String> arrayList = new ArrayList<String>();

        double subtotal = 0.00;

        for (int i = 0; i < order_items.length; i++) {
            arrayList.add(order_items[i].toString());
            subtotal += order_items[i].itemPrice();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        list.setAdapter(adapter);

        double tax = subtotal * 0.06625;
        double total = subtotal + tax;

        TextView orderSubtotal = (TextView) findViewById(R.id.textOrderSubtotal);
        orderSubtotal.setText("Subtotal: $" + (Math.round(subtotal * 100.0) / 100.0));

        TextView orderTax = (TextView) findViewById(R.id.textOrderTax);
        orderTax.setText("Tax: $" + (Math.round(tax * 100.0) / 100.0));

        TextView orderTotal = (TextView) findViewById(R.id.textOrderTotal);
        orderTotal.setText("Total: $" + (Math.round(total * 100.0) / 100.0));
    }
}