package com.daafdroid.steventory;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Inventory extends ListActivity {
    DBHandler myDBHandler = new DBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SQLiteDatabase database;
        SimpleCursorAdapter cursorAdapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);

        database = myDBHandler.getWritableDatabase();
        Cursor dataCursor = database.rawQuery("SELECT * FROM products", null);

        String[] columns = new String[] {
                myDBHandler.ROW_PRODUCTNAME,
                myDBHandler.ROW_MANUFACTURER,
                myDBHandler.ROW_QUANTITY,
        };

        int[] to = new int[] {
                R.id.item_name,
                R.id.item_manufacturer,
                R.id.item_quantity
        };

        cursorAdapter = new SimpleCursorAdapter(this, R.layout.items, dataCursor, columns, to, 0);
        setListAdapter(cursorAdapter);

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked perform some action...
            }
        });
    }

    public void addNewProduct(View view) {
        Intent i = new Intent(getBaseContext(),AddProduct.class);
        startActivity(i);
    }

}