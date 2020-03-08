package com.example.android.myshopapplistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Item> items = new ArrayList<>();
    ItemAdapter adapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                addItem();
            }
        });

        if(items.isEmpty())
            createItems(items);

        //build adapter, give it items
        adapter = new ItemAdapter(this, items);

        //get list from layout and give it the adapter
        list = findViewById(R.id.itemList);
        list.setAdapter(adapter);

        //let layout know it should re-render the list
        adapter.notifyDataSetChanged();

        //click and hold, deletes item from list
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //get selected item
                Item selectedItem = (Item)adapterView.getItemAtPosition(i);
                //get adapter this list is using (cast it to appropriate type)
                ItemAdapter itemAdapter = (ItemAdapter)adapterView.getAdapter();
                //remove item from adapters item list
                itemAdapter.remove(selectedItem);
                //make adapter notify layout needs re-rendering
                itemAdapter.notifyDataSetChanged();

                return false;
            }
        });

    }

    private void createItems(List<Item> items) {
        Item item1 = new Item(new BigDecimal(1.5), "Chewys Chewing Gum", "You should chews it");
        Item item2 = new Item(new BigDecimal(2.75), "Fanta", "Less Fanta, More Serious");
        Item item3 = new Item(new BigDecimal(22.35), "Long Beach Ones", "Who wants to live forever");

        items.add(item1);
        items.add(item2);
        items.add(item3);
    }


    public void addItem() {
        //launch next screen activity
        Intent nextScreen = new Intent(this, NewItemActivity.class);
        startActivityForResult(nextScreen, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //get item sent back from second screen
            Item newItem = (Item) data.getSerializableExtra("itemKey");
            //if item found, add to the list
            if (newItem != null) {
                adapter.add(newItem);
                adapter.notifyDataSetChanged();
            }
        }catch (Exception ex)
        {
            //probably cancelled making a new item
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
