package com.example.cs213_p5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * View class representing all store orders.
 *
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */
public class ViewAllOrdersActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_view_all_orders);

        ListView list = (ListView) findViewById(R.id.allOrdersListView);

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
     * Void responsible for canceling selected order in listView
     *
     * @param v
     */
    public void action_cancel_order(View v) {
        if (SelectionIndex != -1) {
            if (SelectedItem != null) {
                Order[] search_orders = MainActivity.allOrders.getOrders();
                for (int i = 0; i < search_orders.length; i++) {
                    if (search_orders[i].toString().equals(SelectedItem)) {
                        MainActivity.allOrders.remove(search_orders[i]);
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

        String[] toPrint = MainActivity.allOrders.getOrderString();

        ListView list = (ListView) findViewById(R.id.allOrdersListView);

        ArrayList<String> arrayList = new ArrayList<String>();

        for (int i = 0; i < toPrint.length; i++) {
            arrayList.add(toPrint[i]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

        list.setAdapter(adapter);
    }
}