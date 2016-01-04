package com.daafdroid.steventory;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Inventory extends ListActivity {
    DBHandler myDBHandler = new DBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SQLiteDatabase database;
        SimpleCursorAdapter cursorAdapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);

        database = myDBHandler.getWritableDatabase();

        //Fill cursor with some data
        Cursor dataCursor = database.rawQuery("SELECT * FROM products", null);

        //Define table columns we want to use to populate our ListView, as defined in DBHandler class
        String[] columns = new String[] {
                //myDBHandler.ROW_ID,
                myDBHandler.ROW_PRODUCTNAME,
                myDBHandler.ROW_MANUFACTURER,
                myDBHandler.ROW_QUANTITY,
        };

        //the XML defined views to populate with our data (Defined in entry.xml)
        int[] to = new int[] {
                R.id.item_name,
                R.id.item_manufacturer,
                R.id.item_quantity
        };

        //Create SimpleCursorAdapter to populate ListView with database records
        cursorAdapter = new SimpleCursorAdapter(this, R.layout.items, dataCursor, columns, to, 0);

        setListAdapter(cursorAdapter);

    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);

        new AlertDialog.Builder(this)
                .setTitle("Hello")
                .setMessage("from " + getListView().getItemAtPosition(position))
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {}
                        })
                .show();

        Toast.makeText(Inventory.this,
                "ListView: " + l.toString() + "\n" +
                        "View: " + v.toString() + "\n" +
                        "position: " + String.valueOf(position) + "\n" +
                        "id: " + String.valueOf(id),
                Toast.LENGTH_LONG).show();
    }


    public void addNewProduct(View view) {
    //This method is called when button with id "btn_new_product" is clicked
        Intent i = new Intent(getBaseContext(),AddProduct.class);
        startActivity(i);
        //Toast.makeText(Inventory.this, "BUTTON NEW PRODUCT CLICKED", Toast.LENGTH_SHORT).show();
    }
}