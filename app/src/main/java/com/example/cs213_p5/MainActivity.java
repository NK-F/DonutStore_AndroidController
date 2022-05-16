package com.example.cs213_p5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * View Class for activity presented at launch.
 *
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */

public class MainActivity extends AppCompatActivity {

    public static StoreOrders allOrders = new StoreOrders();
    public static Order currentOrder = new Order();

    /**
     * Void responsible for above the fold content loading
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * void responsible for handling order donuts selection
     *
     * @param v
     */
    public void action_order_donuts(View v) {
        Intent intent = new Intent(this, OrderDonutsActivity.class);
        startActivity(intent);
    }

    /**
     * void responsible for handling order coffee selection
     *
     * @param v
     */
    public void action_order_coffee(View v) {
        Intent intent = new Intent(this, OrderCoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * void responsible for handling order view selection
     *
     * @param v
     */
    public void action_view_order(View v) {
        Intent intent = new Intent(this, ViewOrderActivity.class);
        startActivity(intent);
    }

    /**
     * void responsible for handling view all orders selection
     *
     * @param v
     */
    public void action_view_all_orders(View v) {
        Intent intent = new Intent(this, ViewAllOrdersActivity.class);
        startActivity(intent);
    }

    /**
     * void responsible for displaying alert that session storage will be cleared on exit
     */
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Are you sure you want to exit? Exiting will cause session " +
                "storage to be cleared and all data will be lost");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}